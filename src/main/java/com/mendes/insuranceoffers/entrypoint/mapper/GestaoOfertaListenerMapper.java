package com.mendes.insuranceoffers.entrypoint.mapper;

import java.util.UUID;

import org.apache.avro.generic.GenericRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;

import com.mendes.ofertas.common.exception.MapperException;
import com.mendes.ofertas.entrypoint.constant.KafkaConstant;
import com.mendes.ofertas.entrypoint.model.request.GestaoOfertaListenerRequest;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public abstract class GestaoOfertaListenerMapper {

	public List<OfferManagementListenerRequest> toOfertaListenerRequest(GenericRecord genericRecord) {
		ComandoSolicitarOfertaSeguroPrestamistaConsignadoData comandoSolicitarSimulacaoOfertaSeguroPrestamistaConsignadoData = (ComandoSolicitarOfertaSeguroPrestamistaConsignadoData) genericRecord
				.get(KafkaConstant.DATA);

		UUID codigoIntencaoRequisicao = getCodigoIntencaoRequisicao(genericRecord);
		List<IntencoesRecord> intencoes = comandoSolicitarSimulacaoOfertaSeguroPrestamistaConsignadoData.getPayload()
				.getPayloadDados().getIntencoes();

		return intencoes.stream().map(intencao -> getintencoesOfertaPrestamista(intencao, codigoIntencaoRequisicao))
				.findFirst().orElseThrow(() -> new KafkaListenerException("Não há elementos na lista de intenções."));

	}

	private List<OfferManagementListenerRequest> getIntencoesOfertaPrestamista(IntencoesRecord intencoesRecord,
			UUID codigoIntencaoRequisicao) {
		List<OfertaSeguroPrestamistaConsignado> listaOfertaSeguroPrestamista = getOfertasSeguroPrestamistaConsignado(
				intencoesRecord);

		return listaOfertaSeguroPrestamista.stream()
				.map(oferta -> toGestaoOfertaListenerRequest(oferta, codigoIntencaoRequisicao))
				.collect(Collector.toList());
	}

	private OfferManagementListenerRequest toGestaoOfertaListenerRequest(OfertaSeguroPrestamistaConsignado oferta,
			UUID codigoIntencaoRequisicao) {

		OfferManagementListenerRequest gestaoOfertaListenerRequest;

		try {
			gestaoOfertaListenerRequest = toGestaoOfertaListenerRequest(oferta);
			gestaoOfertaListenerRequest.setCodigoIntencaoRequisicao(codigoIntencaoRequisicao);
		} catch (Exception e) {
			throw new MapperException(e);
		}

		validaCampos(gestaoOfertaListenerRequest);
		return gestaoOfertaListenerRequest;
	}

	public static UUID getCodigoIntencaoRequisicao(GenericRecord genericRecord) {
		try {
			ComandoSolicitarOfertaSeguroPrestamistaConsignadoData comandoSolicitarOfertaSeguroPrestamistaConsignadoData = (ComandoSolicitarOfertaSeguroPrestamistaConsignadoData) genericRecord
					.get(KafkaConstant.DATA);
			String codigoIntencaoRequisicao = comandoSolicitarOfertaSeguroPrestamistaConsignadoData
					.get(KafkaConstant.CODIGO_INTENCAO_REQUISICAO);
			return UUID.fromString(isNotEmpty(codigoIntencaoRequisicao) ? codigoIntencaoRequisicao : StringUtils.EMPTY);
		} catch (IllegalArgumentException e) {
			throw new KafkaListenerException(
					"Não foi possível obet " + KafkaConstant.CODIGO_INTENCAO_REQUISICAO + " do payload.", e);
		}
	}

	private static List<OfertaSeguroPrestamistaConsignado> getOfertaSeguroPrestamistaConsignado(
			IntencoesRecord intencoesRecord) {
		return intencoesRecord.getDados().getOfertaSeguroPrestamistaConsignado();
	}

	private static void validaCampos(OfferManagementListenerRequest gestaoOfertaListenerRequest) {
		Set<ConstraintViolation<OfferManagementListenerRequest>> listaConstraintViolation = beanValidator()
				.validate(gestaoOfertaListenerRequest);

		if (isNotEmpty(listaConstraintViolation)) {
			StringBuilder mensagemErro = montaMensagem(listaConstraintViolation);
			throw new KafkaListenerException(mensagemErro.toString());
		}
	}

	private static StringBuilder montaMensagem(
			Set<ConstraintViolation<OfferManagementListenerRequest>> listaConstraintViolation) {
		StringBuilder mensagemErro = new StringBuilder();

		listaConstraintViolation.forEach(constraintViolation -> mensagemErro.append(String.format(" - campo %s %s",
				constraintViolation.getPropertyPath(), constraintViolation.getMessage())));
		
		return mensagemErro;

	}
	
	@Mapping(target = "codigoSegmentoConvenioCreditoConsignado", source = "codigoSegmentoConvenioCreditoConsignado", qualifiedByName = "toTipoConvenio")
	@Mapping(target = "codigoIdentificadorCanalSeguroPrestamista", source = "codigoIdentificacaoCanalSeguroPrestamista")
	@Mapping(target = "dataNascencaPessoa", ignore = true)
	public abstract OfferManagementListenerRequest toGestaoOfertaListenerRequest(OfertaSeguroPrestamistaConsignado ofertaSeguroPrestamistaConsignado);

	protected String map(CharSequence charSequence) {
		return isEmpty(charSequence) ? null : charSequence.toString();
	}
	
	@Named("toTipoConvenio")
	protected int toEnumTipoConvenio(CharSequence charSequence) {
		try {
			return TipoConvenio.getByCodigoTipoConvenioAZ(charSequence.toString()).getCodigoTipoConvenio();
		} catch (Exception e) {
			throw new MapperException(e);
		}
	}
	
	@Mapping(target = "codigoCanal", source = "codigoIdentificadorCanalSeguroPrestamista")
	@Mapping(target = "riscoSegurado.dataVencimentoPrimeiraParcela", source = "codigoIdentificadorCanalSeguroPrestamista")
	@Mapping(target = "riscoSegurado.codigoIdentificacaoOfertaCreditoConsignado", source = "codigoIdentificadorCanalSeguroPrestamista")
	@Mapping(target = "quantidadeParcelas", source = "codigoIdentificadorCanalSeguroPrestamista")
	@Mapping(target = "valorTotalCreditoConsignado", source = "codigoIdentificadorCanalSeguroPrestamista")
	@Mapping(target = "valorParcelaPlanoCreditoConsignado", source = "codigoIdentificadorCanalSeguroPrestamista")
	@Mapping(target = "riscoSegurado.quantidadeParcelas", source = "codigoIdentificadorCanalSeguroPrestamista")
	@Mapping(target = "riscoSegurado.diaVencimentoParcela", source = "codigoIdentificadorCanalSeguroPrestamista")
	@Mapping(target = "riscoSegurado.codigoParceiro", source = "codigoIdentificadorCanalSeguroPrestamista")
	@Mapping(target = "riscoSegurado.codigoProdutoParceiro", source = "codigoIdentificadorCanalSeguroPrestamista")
	@Mapping(target = "riscoSegurado.codigoTipoConvenioPrestamista", source = "codigoIdentificadorCanalSeguroPrestamista")
	@Mapping(target = "riscoSegurado.valorCredito", source = "codigoIdentificadorCanalSeguroPrestamista")
	@Mapping(target = "proponente.contaCorrente.codigoSegmento", source = "codigoIdentificadorCanalSeguroPrestamista")
	@Mapping(target = "proponente.dataNascimento", source = "codigoIdentificadorCanalSeguroPrestamista")
	@Mapping(target = "proponente.contaCorrente.codigoBanco", source = "codigoIdentificadorCanalSeguroPrestamista")
	@Mapping(target = "proponente.contaCorrente.numeroAgencia", source = "codigoIdentificadorCanalSeguroPrestamista")
	@Mapping(target = "proponente.contaCorrente.numeroContaCorrente", source = "codigoIdentificadorCanalSeguroPrestamista")
	@Mapping(target = "proponente.contaCorrente.digitoVerificadorContaCorrente", source = "codigoIdentificadorCanalSeguroPrestamista")
	public abstract OfertaDomain toOfertaDomain(OfferManagementListenerRequest gestaoOfertaListenerRequest);
}
