package com.mendes.insuranceoffers.dataprovider.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.mendes.ofertas.dataprovider.http.model.response.ClienteHttpResponse;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public abstract class ClientaDataProviderMapper {
	public abstract ClientDomain toDomain(ClienteHttpResponse clienteHttpResponse);
}
