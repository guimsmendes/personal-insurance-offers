package com.mendes.insuranceoffers.dataprovider.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mendes.insuranceoffers.dataprovider.model.enums.CreditScoreEnum;

import lombok.Getter;

@Getter
@Entity
@Table(name = "product_catalog")
public class ProductCatalog {
	
	@Id
	@Column(nullable = false, unique = true, updatable = false)
	int productId;
	
	@Column(nullable = false, updatable = false)
	int branchId;
	
	@Column(nullable = false, updatable = false)
	BigDecimal creditRange;
	
	@Column(nullable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	CreditScoreEnum creditScore;
	
	@Column(nullable = false, updatable = false)
	int clientAge;
	

}
