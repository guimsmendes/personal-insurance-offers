package com.mendes.insuranceoffers.usecase.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferDomain {
	
	private UUID requisitionIntentionCode;
	private UUID insuranceOfferCode;
	private BigDecimal monthlyPaymentValue;
	private BigDecimal totalPaymentValue;
	private int paymentsNumber;
	private LocalDate validityStartDate;
	private BigDecimal interestRate;
	private BigDecimal interestRateValue;
	private BigDecimal personalLoanTotalValue;
	private BigDecimal personalLoanMonthlyPaymentValue;
	private InsuredRiskDomain insuredRisk;
	private ProponentDomain proponent;
	private ProductDomain product;
	
	@Getter
	@Setter
	public static class InsuredRiskDomain {
		
		private String personalLoanOfferId;
		private BigDecimal creditValue;
		private int paymentsNumber;
		private String creditScore;
		private int clientAge;
		private LocalDate firstPaymentPersonalLoanDueDate;
	}

}
