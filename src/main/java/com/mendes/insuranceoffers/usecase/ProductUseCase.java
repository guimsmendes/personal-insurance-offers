package com.mendes.insuranceoffers.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mendes.insuranceoffers.usecase.domain.ProductDomain;
import com.mendes.insuranceoffers.usecase.domain.ProductDomain.CoverDomain;
import com.mendes.insuranceoffers.usecase.exception.UseCaseException;
import com.mendes.insuranceoffers.usecase.gateway.ProductGateway;

@Component
public class ProductUseCase {
	
	@Autowired
	private ProductGateway productGateway;
	
	public ProductDomain searchForCovers(ProductDomain product) {
		List<CoverDomain> covers = productGateway.searchForCovers(product)
				.orElseThrow(() -> new UseCaseException.CoverNotFound("There are no covers for the product Id: "
						+ product.getProductId() + "."));
		product.setCovers(covers);
		return product;
	
	}

}
