package com.mendes.insuranceoffers.entrypoint.listener;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.mendes.ofertas.entrypoint.constant.KafkaConstant.*;

@Component
public class GestaoOfertaListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GestaoOfertaListener.class);
	
	public final CountDownLatch latch = new CountDownLatch(1);
	
	@Value("${mendes.topico.producer.oferta}")
	private String topicoProducerOfertaSeguro;
	@Value("${mendes.topico.producer.oferta.specversion}")
	private String topicoProducerOfertaSpecversion;
	
	private GestaoOfertaUseCase gestaoOfertaUseCase;
	
	private GestaoOfertaListenerMapper gestaoOfertaListenerMapper;
	
	private GestaoOfertaProducerMapper gestaoOfertaProducerMapper;
	
	@Autowired
	public GestaoOfertaListener(GestaoOfertaUseCase gestaoOfertaUseCase, GestaoOfertaListenerMapper gestaoOfertaListenerMapper,
			GestaoOfertaProducerMapper gestaoOfertaProducerMapper) {
		this.gestaoOfertaUseCase = gestaoOfertaUseCase;
		this.gestaoOfertaListenerMapper = gestaoOfertaListenerMapper;
		this.gestaoOfertaProducerMapper = gestaoOfertaProducerMapper;
	}
	
	@SendTo("${mendes.topico.producer.oferta}")
	@KafkaListener(topics = "${mendes.topico.listener.intencao.oferta}", groupId = "${spring.kafka.consumer.group-id}")
	public Message<ComandoSimulacaoOfertaSeguroPrestamistaConsignadoRecebida> escutarTopicoIntencaoOferta(@Payload GenericRecord payload, 
			@Headers MessageHeaders headers, Acknowledgment acknowledgment) {
		
		String codigoIntencaoRequisicao = StringUtils.EMPTY;
		
		try {
			codigoIntencaoRequisicao = GestaoOfertaListenerMapper.getCodigoIntencaoRequisicao(payload).toString();
			List<GestaoOfertaListenerRequest> gestaoOfertaListenerRequests = gestaoOfertaListenerMapper.toOfertaListenerRequest(payload);
			List<Oferta> gerarOfertas = gerarOfertas(gestaoOfertaListenerRequests);
			ComandoSimulacaoOfertaSeguroPrestamistaConsignadoRecebida payloadOferta =
					gestaoOfertaProducerMapper.toComandoSimulacaoOfertaSeguroPrestamistaConsignadoRecebida(gerarOfertas);
			
			latch.countDown();
			return produzMensagem(payloadOferta, headers);
		}
		catch (Exception e) {
			latch.countDown();
			return produzMensagemFluxoErro(headers, codigoIntencaoRequisicao, e.getMessage());
		} finally {
			acknowledgment.acknowledge();
		}
	}
	
	private Message<ComandoSimulacaoOfertaSeguroPrestamistaConsignadoRecebida> produzMensagemFluxoErro(MensageHeaders headers, 
			String gestaoOfertaListenerRequests, String mensagemErro) {
		LOGGER.error("Não foi possível simular ofertas de seguro para as intenções recebidas. {}", mensagemErro);
		ComandoSimulacaoOfertaSeguroPrestamistaConsignadoRecebida payloadFluxoErroOferta = gestaoOfertaProducerMapper.getPayloadMensagemErro(mensagemErro, gestaoOfertaListenerRequests);
		return produzMensagem(payloadFluxoErroOferta, headers);
		
	}
	
	private List<OfertaDomain> gerarOfertas(List<GestaoOfertaListenerRequest> gestaoOfertaListenerRequests) {
		List<OfertaDomain> ofertasParaProduzir = gestaoOfertaListenerRequests.stream().map(this::ofertar).flatMap(List::stream).collect(Collectors.toList());
		
		if(ofertasParaProduzir.isEmpty()) {
			throw new KafkaListenerException("Não foi possível simular ofertas para as intenções recebidas")
		}
		return ofertasParaProduzir;
	}
	
	private List<OfertaDomain> ofertar(GestaoOfertaListenerRequest gestaoOfertaListenerRequest){
		try {
			return offerUseCase.generateOffers(gestaoOfertaListenerRequest);
		}
		catch(Exception e) {
			LOGGER.error("Não foi possível simular a oferta. MENSAGEM: {}CODIGO INTENÇÃO RQEQUISIÇÃO{}", e, gestaoOfertaListenerRequest.getCosigoIntencaoRequisicao());
			return new ArrayList<>();
		}
	}
	
	private Message<ComandoSimulacaoOfertaSeguroPrestamistaConsignadoRecebida> produzMensagem(ComandoSimulacaoOfertaSeguroPrestamistaConsignadoRecebida payload, MessageHeaders headers){
		return MessageBuilder
				.withPayload(payload)
				.setHeader(KafkaHeaders.REPLY_TOPIC, topicoProducerOfertaSeguro)
				.setHeader(SPEC_VERSION, topicoProducerOfertaSpecversion)
				.setHeader(CORRELATION_ID, headers.get(CORRELATION_ID))
				.setHeader(TRANSACTION_ID, headers.get(TRANSACTION_ID))
				.build();
	}

}
