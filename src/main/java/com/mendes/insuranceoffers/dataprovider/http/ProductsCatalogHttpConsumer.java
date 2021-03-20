package com.mendes.insuranceoffers.dataprovider.http;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.mendes.insuranceoffers.dataprovider.http.enums.ProductsCatalogExpand;
import com.mendes.insuranceoffers.dataprovider.http.model.response.ProductsCatalogCoverHttpResponse;
import com.mendes.insuranceoffers.dataprovider.http.model.response.DataHttpResponse;
import com.mendes.insuranceoffers.common.cache.CachingConfig;

@FeignClient(name = "CoverHttpConsumerClient", url = "${mendes.gateway.url}")
public interface ProductsCatalogHttpConsumer {
	
	@Cacheable(CachingConfig.COVERS)
	@GetMapping(
			value = "/branches/{branchId}/products/{productId}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE,
			params = "expand")
	ResponseEntity<DataHttpResponse<ProductsCatalogCoverHttpResponse>> listCovers(
			@PathVariable("branchId") int branchId,
			@PathVariable("productId") int productId,
			@RequestParam("expand") ProductsCatalogExpand... expand);

}
