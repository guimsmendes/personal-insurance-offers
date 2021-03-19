package com.mendes.insuranceoffers.dataprovider.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.mendes.ofertas.dataprovider.http.model.response.CatalogoProdutosCoberturaHttpResponse.Cobertura;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ProdutoDataProviderMapper {
	
	public abstract CoverDomain toCoberturaDomain(Cobertura cobertura);

}
