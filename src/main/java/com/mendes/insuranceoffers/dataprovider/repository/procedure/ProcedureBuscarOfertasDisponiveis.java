package com.mendes.insuranceoffers.dataprovider.repository.procedure;

import java.sql.Types;
import java.util.Map;
import java.util.Optional;

import com.mendes.ofertas.dataprovider.model.request.ProcedureBuscarOfertasDisponiveisRequest;

@Component
public class ProcedureBuscarOfertasDisponiveis extends SybaseProcedure<ProcedureBsucarOfertasDisponiveisRequest>, Optional<List<ProcedureBuscarOfertasDisponiveisResponse>>{
	
	
	@Override
	public Optional<List<ProcedureBuscarOfertasDisponiveisResponse>> executar(ProcedureBsucarOfertasDisponiveisRequest entrada) {
		return super.executar(PROCEDURE_BUSCAR_OFERTAS_DISPONIVEIS, COMBOS_PRODUTOS_RESULTSET, new ProcedureBuscarOfertasDisponiveisMapper(), entrada);
	}

	@Override
	protected SqlParameterSource montarParametros(ProcedureBuscarOfertasDisponiveisRequest entityRequest) {
		return new MapSqlParameterSource()
				.addValue(CODIGO_ERRO, Types.INTEGER)
				.addValue(MENSAGEM_ERRO, Types.VARCHAR)
				.addValue(CODIGO_CANAL_ELETRONICO_BANCO_ITAU, entityRequest.getCodigoIdentificacaoCanalSeguroPrestamista(), Types.CHAR)
				.addValue(TIPO_SEGMENTO_BANCO_ITAU, entityRequest.getTipoSegmentoBanco(), Types.CHAR)
				.addValue(CODIGO_PARCEIRO, entityRequest.getCodigoParceiro(), Types.INTEGER)
				.addValue(CODIGO_PRODUTO_PARCEIRO, entityRequest.getCodigoProdutoParceiro(), Types.INTEGER)
				.addValue(CODIGO_TIPO_CONVENIO_PRESTAMISTA, entityRequest.getCodigoTipoConvenioPrestamista(), Types.INTEGER)
				.addValue(QUANTIDADE_IDADE, entityRequest.getQuantidadeIdade(), Types.INTEGER);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected Optional<List<ProcedureBuscarOfertasDisponiveisResponse>> extrairDados(Map<String, Object> resultadoProcedure) {
		List<ProcedureBuscarOfertasDisponiveisResponse> retornoResultadoProcedure = 
				(List<ProcedureBuscarOfertasDisponiveisResponse>) resultadoProcedure.get(COMBOS_PRODUTOS_RESULTSET);
		
		return isEmpty(retornoResultadoProcedure) ? Optional.empty() : Optional.ofNullable(retornoResultadoProcedure);
	}
}
