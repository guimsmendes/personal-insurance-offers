package com.mendes.insuranceoffers.dataprovider;

import java.util.List;
import java.util.Optional;

import com.mendes.insuranceoffers.dataprovider.model.Offer;
import com.mendes.insuranceoffers.dataprovider.repository.OfferRepository;
import com.mendes.insuranceoffers.entrypoint.mapper.OfferManagementListenerMapper;
import com.mendes.insuranceoffers.entrypoint.model.request.OfferManagementListenerRequest;
import com.mendes.insuranceoffers.usecase.domain.OfferDomain;
import com.mendes.insuranceoffers.usecase.gateway.OfferGateway;

public class OfertaDataProvider implements OfferGateway{
	
	private OfferRepository offerRepository;
	
	private OfferManagementListenerMapper offerManagementListenerMapper;
	
	
	@Override
	public Optional<List<OfferDomain>> searchAvailableOffers(OfferManagementListenerRequest offerListenerRequest) {
		OfferDomain offerDomain = offerManagementListenerMapper.toOfferDomain(offerListenerRequest);
		return offerRepository.searchAvailableOffers(offerDomain.getInsuredRisk())
				.map(availableOffers -> addData(availableOffers, offerDomain));
	}

	@Override
	public Optional<OfferDomain> price(OfferDomain offerDomain) {
		return offerRepository.searchPricing(offerDomain.getProduct().getProductId(), offerDomain.getProduct().getBranchId())
				.map(procedurePricingResponse -> ofertaDataProviderMapper.toDomain(procedurePricingResponse));
	}
	
	private List<OfertaDomain> addData(List<ProcedureBuscarOfertasDisponiveisResponse> ofertaDisponiveis, OfertaDomain ofertaDomain) {
		List<OfertaDomain> ofertasDisponiveisDomain = ofertaDataProviderMapper.toListaOfertaDomain(ofertasDisponíveis);
		return ofertasDisponiveisDomain
				.stream()
				.map(ofertaDisponivel -> {
					ofertaDomain.setDataInicioVigencia(ofertaDisponivel.getDataInicioVigencia());
					ofertaDomain.setCodigoCombo(ofertaDisponivel.getCodigoCombo());
					ofertaDomain.setProduto.getProduto());
					ofertaDomain.setCodigoOfertaSeguro(ofertaDisponivel.getCodigoOfertaSeguro());
					return ofertaDomain;
				})
				.collect(Collectors.toList());
	}

	@Override
	public void persistOffer(Offer offer) {
		// TODO Auto-generated method stub
		
	}


}
