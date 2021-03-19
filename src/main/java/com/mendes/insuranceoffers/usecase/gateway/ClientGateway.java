package com.mendes.insuranceoffers.usecase.gateway;

import java.util.Optional;
import java.util.UUID;

import com.mendes.insuranceoffers.usecase.domain.ClientDomain;


public interface ClientGateway {
	
	Optional<ClientDomain> searchClientById(final UUID clientId);

}
