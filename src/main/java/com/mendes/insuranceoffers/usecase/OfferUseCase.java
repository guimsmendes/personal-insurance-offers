package com.mendes.insuranceoffers.usecase;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.mendes.insuranceoffers.entrypoint.model.request.OfferManagementListenerRequest;
import com.mendes.insuranceoffers.usecase.domain.OfferDomain;
import com.mendes.insuranceoffers.usecase.domain.ProductDomain;
import com.mendes.insuranceoffers.usecase.exception.UseCaseException;
import com.mendes.insuranceoffers.usecase.gateway.OfferGateway;

public class OfferUseCase {

	private OfferGateway offerGateway;

	private ProductUseCase productUseCase;

	@Autowired
	private OfferUseCase(OfferGateway offerGateway, ProductUseCase productUseCase) {
		this.offerGateway = offerGateway;
		this.productUseCase = productUseCase;
	}

	public List<OfferDomain> generateOffers(OfferManagementListenerRequest offerListenerRequest) {
		return offerGateway.searchAvailableOffers(offerListenerRequest).map(this::searchForProductCovers)
				.map(this::addPricing)
				.map(offerDomains -> setPersonalLoanTotalValue(offerDomains, offerListenerRequest.getPersonalLoanTotalValue()))
				.map(offerDomains -> setPersonalLoanMonthlyPaymentValue(offerDomains,
						offerListenerRequest.getPersonalLoanMonthlyPaymentValue()))
				.orElseThrow(() -> new UseCaseException.OfferNotFound(
						"There are no Insurance Offers for the Personal Loan Product code: "
								+ offerListenerRequest.getPersonalLoanProductCode() + "."));
	}

	private List<OfferDomain> setPersonalLoanTotalValue(List<OfferDomain> offerDomains, BigDecimal personalLoanTotalValue) {
		return offerDomains.stream().map(offerDomain -> {
			offerDomain.setPersonalLoanTotalValue(personalLoanTotalValue);
			return offerDomain;
		}).collect(Collectors.toList());
	}

	private List<OfferDomain> setPersonalLoanMonthlyPaymentValue(List<OfferDomain> offerDomains,
			BigDecimal personalLoanMonthlyPaymentValue) {
		return offerDomains.stream().map(offerDomain -> {
			offerDomain.setPersonalLoanMonthlyPaymentValue(personalLoanMonthlyPaymentValue);
			return offerDomain;
		}).collect(Collectors.toList());
	}

	private List<OfferDomain> searchForProductCovers(List<OfferDomain> availableOffers) {
		return availableOffers.stream().map(this::addProduct).collect(Collectors.toList());
	}

	private OfferDomain addProduct(OfferDomain availableOffer) {
		ProductDomain product = productUseCase.searchForCovers(availableOffer.getProduct());
		availableOffer.setProduct(product);
		return availableOffer;
	}

	private List<OfferDomain> addPricing(List<OfferDomain> availableOffers) {
		return availableOffers.stream().map(this::addPricingValues).collect(Collectors.toList());
	}

	private OfferDomain addPricingValues(OfferDomain availableOffer) {
		OfferDomain pricedOffer = offerGateway.price(availableOffer).<UseCaseException.UnableToPriceOffer>orElseThrow(
				() -> new UseCaseException.UnableToPriceOffer("Unable to price this Insurance Offer"));
		availableOffer.setInsuranceOfferCode(pricedOffer.getInsuranceOfferCode());
		availableOffer.setMonthlyPaymentValue(pricedOffer.getMonthlyPaymentValue());
		availableOffer.setTotalPaymentValue(pricedOffer.getTotalPaymentValue());
		availableOffer.setInterestRate(pricedOffer.getInterestRate());
		availableOffer.setInterestRateValue(pricedOffer.getInterestRateValue());
		availableOffer.setPaymentsNumber(pricedOffer.getPaymentsNumber());
		return availableOffer;
	}

}
