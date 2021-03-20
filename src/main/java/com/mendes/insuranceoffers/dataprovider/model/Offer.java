package com.mendes.insuranceoffers.dataprovider.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "offer")
public class Offer {
	
	@Id
	@Column(nullable = false, unique = true, updatable = false)
	UUID personalLoanOfferId;
	
	@Id
	@Column(nullable = false, unique = true, updatable = false)
	UUID insuranceOfferId;
	
	String name;
	LocalDate birthDate;	
	BigDecimal monthlyPaymentValue;
	BigDecimal totalPaymentValue;
	int paymentsNumber;
	LocalDate validityStartDate;
	BigDecimal interestRate;
	BigDecimal interestRateValue;
	BigDecimal personalLoanTotalValue;
	BigDecimal personalLoanMonthlyPaymentValue;
	int productId;
	int branchId;
	String productName;
	LocalDate offerDate;
	
	

}
