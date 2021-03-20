package com.mendes.insuranceoffers.dataprovider.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name = "price")
public class Price {
	
	@Id
	@Column(nullable = false, unique = true, updatable = false)
	int productId;
	
	@Column(nullable = false, updatable = false)
	int branchId;
	
	@Column(nullable = false, updatable = false)
	BigDecimal creditInterestRate;
	
	@Column(nullable = false, updatable = false)
	BigDecimal insuranceInterestRate;

}
