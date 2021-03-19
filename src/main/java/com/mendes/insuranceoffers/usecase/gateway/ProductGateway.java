package com.mendes.insuranceoffers.usecase.gateway;

import java.util.List;
import java.util.Optional;

import com.mendes.insuranceoffers.usecase.domain.ProductDomain;
import com.mendes.insuranceoffers.usecase.domain.ProductDomain.CoverDomain;

public interface ProductGateway {
	
	Optional<List<CoverDomain>> searchForCovers(ProductDomain product);

}
