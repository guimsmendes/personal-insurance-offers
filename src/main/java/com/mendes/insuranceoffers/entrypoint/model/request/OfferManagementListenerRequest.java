package com.mendes.insuranceoffers.entrypoint.model.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class OfferManagementListenerRequest {
	
	@NotBlank
	private String personalLoanOfferId;
	
	@NotNull
	private UUID requisitionIntentionCode;
	
	@NotBlank
	private String creditScore;
	
	@NotNull
	@Positive
	private BigDecimal personalLoanTotalValue;
	private BigDecimal personalLoanMonthlyPaymentValue;
	private LocalDate firstPaymentPersonalLoanDueDate;
	private int paymentsNumber;
	private int personalLoanProductCode;
	private LocalDate birthDate;
	private int agencyId;
	private int accountId;
	private int accountVerificationDigit;
	private String clientId;
	private int proponentsAge;
	
	private void setProponentsAge() {
		this.proponentsAge = this.birthDate == null ? 0 : Period.between(birthDate, LocalDate.now()).getYears();
	}
	
	
}
