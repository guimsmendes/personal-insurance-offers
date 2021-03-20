package com.mendes.insuranceoffers.dataprovider.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.mendes.insuranceoffers.dataprovider.http.model.response.ProductsCatalogCoverHttpResponse.Cover;
import com.mendes.insuranceoffers.usecase.domain.ProductDomain.CoverDomain;


@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ProductDataProviderMapper {
	
	public abstract CoverDomain toCoverDomain(Cover cover);

}
