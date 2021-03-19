package com.mendes.insuranceoffers.usecase;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mendes.insuranceoffers.usecase.domain.ClientDomain;
import com.mendes.insuranceoffers.usecase.exception.UseCaseException;
import com.mendes.insuranceoffers.usecase.gateway.ClientGateway;

@Component
public class ClientUseCase {
	
	private final ClientGateway clientGateway;
	
	public ClientUseCase(ClientGateway clientGateway) {
		this.clientGateway = clientGateway;
	}
	
	public ClientDomain searchClientById(final UUID clientId) {
		return clientGateway.searchClientById(clientId)
				.orElseThrow(() -> new UseCaseException.ClientNotFound(
						String.format("Unable to locate client with id %s", clientId)));
	}

}
