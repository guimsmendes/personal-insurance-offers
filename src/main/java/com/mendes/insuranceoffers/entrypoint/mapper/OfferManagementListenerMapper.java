package com.mendes.insuranceoffers.entrypoint.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

import com.mendes.insuranceoffers.entrypoint.model.request.OfferManagementListenerRequest;
import com.mendes.insuranceoffers.usecase.domain.OfferDomain;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public abstract class OfferManagementListenerMapper {
	

	protected String map(CharSequence charSequence) {
		return isEmpty(charSequence) ? null : charSequence.toString();
	}
	
	@Mapping(target="insuredRisk.creditScore", source="creditScore")
	@Mapping(target="proponent.name", source="name")
	@Mapping(target="proponent.birthDate", source="birthDate")
	@Mapping(target="insuredRisk.clientAge", source="proponentsAge")
	@Mapping(target="insuredRisk.creditValue", source="personalLoanTotalValue")
	public abstract OfferDomain toOfferDomain(OfferManagementListenerRequest offerManagementListenerRequest);
}
