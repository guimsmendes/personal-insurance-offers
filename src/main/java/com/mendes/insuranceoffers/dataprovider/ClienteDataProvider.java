package com.mendes.insuranceoffers.dataprovider;

import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.mendes.ofertas.dataprovider.http.model.response.ClienteHttpResponse;
import com.mendes.ofertas.dataprovider.http.model.response.DataHttpResponse;

@Component
public class ClienteDataProvider extends DataProviderHttp implements ClienteGateway {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientaDataProvider.class);
	
	private CadastroClientesHttpConsumer cadastroClientesHttpConsumer;
	
	private ClienteDataProviderMapper clienteDataProviderMapper;
	
	@Autowired
	public ClienteDataProvider(CadastroClientesHttpConsumer cadastroClientesHttpConsumer,
			ClienteDataProviderMapper clienteDataProviderMapper) {
		this.cadastroClientesHttpConsumer = cadastroClientesHttpConsumer;
		this.clienteDataProviderMapper = clienteDataProviderMapper;
	}
	
	@Override
	public Optional<ClientDomain> buscarClientePorId(UUID idCliente) {
		try {
			LOGGER.info("Buscando informações em caDataHttpResponse<T>es para o cliente de UUID {}", idCliente);
			
			ResponseEntity<DataHttpResponse<ClienteHttpResponse>> dataHttpResponseResponseEntity =
					cadastroClienteHttpConsumer.buscarPorUUID(idCliente);
			
			LOGGER.info("HTTP CODE STATUS {} para busca por informacoes em cadastro de clientes para o cliente de UUID {}",
					dataHttpResponseResponseEntity.getStatusCodeValue(),
					idCliente);
			if(isEmpty(getData(dataHttpResponseResponseEntity))) {
				return Optional.empty();
			}
			return Optional.ofNullable(clienteDataProviderMapper.toDomain(getData(dataHttpResponseResponseEntity)));
		} catch (Exception e) {
			LOGGER.error("Não foi possível consumir as informações do cadastro para o cliente de UUID {} Mensagem {}", idCliente, e);
			return Optional.empty();
		}
	}

}
