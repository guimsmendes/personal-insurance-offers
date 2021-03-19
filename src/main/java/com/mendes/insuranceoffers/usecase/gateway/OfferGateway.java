package com.mendes.insuranceoffers.usecase.gateway;

import java.util.List;
import java.util.Optional;

import com.mendes.insuranceoffers.entrypoint.model.request.OfferManagementListenerRequest;
import com.mendes.insuranceoffers.usecase.domain.OfferDomain;

public interface OfferGateway {
	
	Optional<List<OfferDomain>> searchAvailableOffers(OfferManagementListenerRequest offerListenerRequest);
	
	Optional<OfferDomain> price(OfferDomain offerDomain);

}
