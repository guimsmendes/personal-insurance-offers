package com.mendes.insuranceoffers.dataprovider.http;

import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.mendes.ofertas.dataprovider.http.enums.CatalogoProdutosExpand;
import com.mendes.ofertas.dataprovider.http.model.response.CatalogoProdutosCoberturaHttpResponse;
import com.mendes.ofertas.dataprovider.http.model.response.DataHttpResponse;
import com.mendes.ofertas.common.cache.CachingConfig;

@FeignClient(name = "CoberturaHttpConsumerClient", url = "${mendes.gateway.interno.url}")
public interface CatalogoProdutosHttpConsumer {
	
	@Cacheable(CachingConfig.COBERTURAS)
	@GetMapping(
			value = "/corretora/produtos/{codigoProduto}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE,
			params = "expand")
	ResponseEntity<DataHttpResponse<CatalogoProdutosCoberturaHttpResponse>> listarCoberturas(
			@PathVariable("codigoProduto") UUID codigoProduto,
			@RequestParam("expand") CatalogoProdutosExpand... expand);

}
