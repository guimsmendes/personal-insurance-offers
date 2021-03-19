package com.mendes.insuranceoffers.usecase.gateway;

import java.util.List;

import com.mendes.insuranceoffers.usecase.domain.OfferDomain;



public interface OfferManagementProducerGateway {
	
	void publicOfferTopic(List<OfferDomain> offersDomain);

}
