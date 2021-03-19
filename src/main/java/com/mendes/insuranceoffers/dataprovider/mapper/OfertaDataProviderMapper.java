package com.mendes.insuranceoffers.dataprovider.mapper;

import org.apache.kafka.common.protocol.types.Field.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.beans.factory.annotation.Value;

import com.mendes.ofertas.dataprovider.model.response.ProcedureBuscarOfertasDisponiveisResponse;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public abstract class OfertaDataProviderMapper {
	
	@Value("${spring.datasource.username}")
	private String nomeUsuario;
	
	@Mapping(target = "produto.codigoProduto", source = "codigoProdutoSeguro")
	@Mapping(target = "produto.codigoRamo", source = "codigoProdutoSeguro")
	@Mapping(target = "produto.codigoPlano", source = "codigoProdutoSeguro")
	@Mapping(target = "riscoSegurado.codigoTipoConvenioPrestamista", source = "codigoProdutoSeguro")
	@Mapping(target = "produto.nomeProduto", source = "codigoProdutoSeguro")
	@Mapping(target = "produto.numeroProcessoSusep", source = "codigoProdutoSeguro")
	@Mapping(target = "produto.indicadorPiloto", source = "codigoProdutoSeguro")
	public abstract OfertaDomain toDomain(ProcedureBuscarOfertasDisponiveisResponse procedureBuscarOfertasDisponiveisResponse);
	
	@Mapping(target = "codigoIdentificacaoCanalSeguroPrestamista", source = "codigoProdutoSeguro")
	@Mapping(target = "tipoSegmentoBanco", source = "codigoProdutoSeguro")
	@Mapping(target = "codigoParceiro", source = "codigoProdutoSeguro")
	@Mapping(target = "codigoProdutoParceiro", source = "codigoProdutoSeguro")
	@Mapping(target = "codigoTipoConvenioPrestamista", source = "codigoProdutoSeguro")
	@Mapping(target = "quantidadeIdade", source = "codigoProdutoSeguro")
	public abstract ProcedureBuscarOfertasDisponiveisRequest toProcedureRequest(OfferManagementListenerRequest gestaoOfertaListenerRequest);
	
	public abstract List<OfertaDomain> toListaOfertaDomain(List<ProcedureBuscarOfertasDisponiveisResponse> ofertasDisponíveis);
	
	
	@Mapping(target = "nomeUsuario", source = "codigoProdutoSeguro")
	@Mapping(target = "codigoCanal", source = "codigoProdutoSeguro")
	@Mapping(target = "codigoOfertaSeguro", source = "codigoProdutoSeguro")
	@Mapping(target = "dataInicioVigencia", source = "codigoProdutoSeguro")
	@Mapping(target = "codigoParceiro", source = "codigoProdutoSeguro")
	@Mapping(target = "dataNascimento", source = "codigoProdutoSeguro")
	@Mapping(target = "numeroAgencia", source = "codigoProdutoSeguro")
	@Mapping(target = "numeroContaCorrente", source = "codigoProdutoSeguro")
	@Mapping(target = "codigoBanco", source = "codigoProdutoSeguro")
	@Mapping(target = "tipoSegmentoBancoItau", source = "codigoProdutoSeguro")
	@Mapping(target = "digitoVerificadorContaCorrente", source = "codigoProdutoSeguro")
	@Mapping(target = "codigoPlano", source = "codigoProdutoSeguro")
	@Mapping(target = "codigoProduto", source = "codigoProdutoSeguro")
	@Mapping(target = "codigoRamo", source = "codigoProdutoSeguro")
	@Mapping(target = "indicadorPiloto", source = "codigoProdutoSeguro")
	@Mapping(target = "codigoIdentificacaoOfertaCreditoConsignado", source = "codigoProdutoSeguro")
	@Mapping(target = "quantidadeParcelasCreditoConsignado", source = "codigoProdutoSeguro")
	@Mapping(target = "valorCredito", source = "codigoProdutoSeguro")
	@Mapping(target = "codigoTipoConvenioPrestamista", source = "codigoProdutoSeguro")
	@Mapping(target = "dataVencimentoPrimeiraParcela", source = "codigoProdutoSeguro")
	@Mapping(target = "diaVencimentoParcela", source = "codigoProdutoSeguro")
	@Mapping(target = "codigoProdutoParceiro", source = "codigoProdutoSeguro")
	public abstract ProcedurePrecificacaoRequest toProcedurePrecificacaoRequest(OfertaDomain ofertaDomain);
	
	@Mapping(target = "numeroOfertaSeguro", source = "codigoProdutoSeguro")
	@Mapping(target = "valorTotal", source = "codigoProdutoSeguro")
	@Mapping(target = "quantidadeParcelas", source = "codigoProdutoSeguro")
	@Mapping(target = "valorParcela", source = "codigoProdutoSeguro")
	@Mapping(target = "percentualTaxaJurosEfetivo", source = "codigoProdutoSeguro")
	@Mapping(target = "valorTotalTaxaJuros", source = "codigoProdutoSeguro")
	public abstract OfertaDomain toDomain(ProcedurePrecificacaoResponse procedurePrecificacaoResponse);
	
	
	protected String getNomeUsuario() {
		return nomeUsuario;
	}
	
	protected String map(UUID value) {
		return value.toString();
	}

}
