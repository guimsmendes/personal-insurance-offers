package com.mendes.insuranceoffers.usecase.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferDomain {
	
	private UUID personalLoanOfferId;
	private UUID insuranceOfferId;
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
	private LocalDate firstPaymentPersonalLoanDueDate;
	
	@Getter
	@Setter
	public static class InsuredRiskDomain {
		
		private BigDecimal creditValue;
		private String creditScore;
		private int clientAge;
	}
	
	

}
