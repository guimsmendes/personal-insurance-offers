package com.mendes.insuranceoffers.dataprovider;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mendes.insuranceoffers.dataprovider.http.DataProviderHttp;
import com.mendes.insuranceoffers.dataprovider.http.ProductsCatalogHttpConsumer;
import com.mendes.insuranceoffers.dataprovider.mapper.ProductDataProviderMapper;
import com.mendes.insuranceoffers.usecase.domain.ProductDomain;
import com.mendes.insuranceoffers.usecase.domain.ProductDomain.CoverDomain;
import com.mendes.insuranceoffers.usecase.gateway.ProductGateway;

import com.mendes.insuranceoffers.dataprovider.http.enums.ProductsCatalogExpand;
import com.mendes.insuranceoffers.dataprovider.http.model.response.DataHttpResponse;
import com.mendes.insuranceoffers.dataprovider.http.model.response.ProductsCatalogCoverHttpResponse;
import com.mendes.insuranceoffers.dataprovider.http.model.response.ProductsCatalogCoverHttpResponse.Cover;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Component
public class ProductDataProvider extends DataProviderHttp implements ProductGateway{
	
	@Autowired
	private ProductsCatalogHttpConsumer productsCatalogHttpConsumer;
	
	@Autowired
	private ProductDataProviderMapper productDataProviderMapper;
	
	@Override
	public Optional<List<CoverDomain>> searchForCovers(ProductDomain product) {
		ResponseEntity<DataHttpResponse<ProductsCatalogCoverHttpResponse>> responseListCovers = 
				productsCatalogHttpConsumer.listCovers(product.getBranchId(), product.getProductId(), 
						ProductsCatalogExpand.COVERS,
						ProductsCatalogExpand.SUMMARY);
		
		ProductsCatalogCoverHttpResponse productsCatalogCoverHttpResponse = getData(responseListCovers);
		
		if(isNotEmpty(productsCatalogCoverHttpResponse)) {
			List<Cover> productsCatalogCoverHttpResponses =
					productsCatalogCoverHttpResponse.getCovers();
			
			if(isNotEmpty(productsCatalogCoverHttpResponses)) {
				return Optional.of(productsCatalogCoverHttpResponses
						.stream()
						.map(cover -> productDataProviderMapper.toCoverDomain(cover))
						.collect(Collectors.toList()));
			}
		}
		return Optional.empty();
	}

}
