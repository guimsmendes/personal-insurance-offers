package com.mendes.insuranceoffers.entrypoint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mendes.insuranceoffers.entrypoint.model.request.OfferManagementListenerRequest;
import com.mendes.insuranceoffers.usecase.OfferUseCase;
import com.mendes.insuranceoffers.usecase.domain.OfferDomain;

@RestController
public class OffersController {
	
	@Autowired
	private OfferUseCase offerUseCase;
	
	@PostMapping("/ofertas")
	public List<OfferDomain> offer(@RequestBody OfferManagementListenerRequest offerManagementListenerRequest)
	{
		return offerUseCase.generateOffers(offerManagementListenerRequest);
	}
	
}
