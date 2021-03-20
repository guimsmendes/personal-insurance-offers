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
	
	@NotNull
	@NotBlank
	private String name;
	@NotNull
	@NotBlank
	private LocalDate birthDate;
	@NotNull
	@NotBlank
	private String creditScore;
	@NotNull
	@Positive
	private BigDecimal personalLoanTotalValue;
	@NotNull
	@NotBlank
	private LocalDate firstPaymentPersonalLoanDueDate;
	@NotNull
	@Positive
	private int paymentsNumber;


	private UUID personalLoanOfferId;
	private int proponentsAge;
	
	private void setProponentsAge() {
		this.proponentsAge = this.birthDate == null ? 0 : Period.between(birthDate, LocalDate.now()).getYears();
	}
	
	private void setPersonalLoanOfferId() {
		this.personalLoanOfferId = UUID.randomUUID();
	}
	
	
}
