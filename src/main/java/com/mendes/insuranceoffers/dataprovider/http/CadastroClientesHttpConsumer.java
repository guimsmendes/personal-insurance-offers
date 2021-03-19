package com.mendes.insuranceoffers.dataprovider.http;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mendes.ofertas.dataprovider.http.model.response.ClienteHttpResponse;
import com.mendes.ofertas.dataprovider.http.model.response.DataHttpResponse;

public interface CadastroClientesHttpConsumer {
	
	@GetMapping(
			value = "/pessoas/clientes/{uuid_cliente}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<DataHttpResponse<ClienteHttpResponse>> buscarporUUID(
			@PathVariable("uuid_cliente") UUID uuidCliente);

}
