package com.mendes.insuranceoffers.dataprovider.repository.procedure;

import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.mendes.ofertas.dataprovider.repository.SimpleJdbcCallFactory;

public abstract class SybaseProcedure<E, S> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SybaseProcedure.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SimpleJdbcCallFactory simpleJdbcCallFactory;

	private SimpleJdbcCall criarInstancia(String procedureName, String nomeResultSet, RowMapper<?> rowMapper) {
		return simpleJdbcCallFactory.createInstance(this.jdbcTemplate).withProcedureName(procedureName)
				.returningResultSet(nomeResultSet, rowMapper);
	}

	private SimpleJdbcCall criarInstancia(String ownerSchema, String procedureName, SqlParameter[] parametros,
			String nomeResultSet, RowMapper<?> rowMapper) {
		return simpleJdbcCallFactory.createInstance(this.jdbcTemplate).withCatalogName(ownerSchema)
				.withProcedureName(procedureName).withoutProcedureColumnMetaDataAccess().declareParameters(parametros)
				.returningResultSet(nomeResultSet, rowMapper);
	}

	private S chamarProcedure(SimpleJdbcCall simpleJdbcCall, SqlParameterSource parametros) {
		try {
			LOGGER.info("Iniciando execução da procedure: {}", simpleJdbcCall.getProcedureName());
			Map<String, Object> retornoExecucaoProcedure = simpleJdbcCall.execute(parametros);
			validarRetornoExecucaoProcedure(simpleJdbcCall.getProcedureName(), retornoExecucaoProcedure);
			return extrairDados(retornoExecucaoProcedure);
		} catch (InvalidDataAccessApiUsageException e) {
			throw new StoredProcedureException("Falha ao realizar a conexão com o banco de dados.", e);
		}
	}

	protected void validarRetornoExecucaoProcedure(String nomeProcedure, Map<String, Object> dadosRetornados) {
		if(isEmpty(dadosRetornados)) {
			throw new StoredProcedureException("A Procedure " + nomeProcedure + " não retornou dados.")
		}
		int codigoErro = isNotEmpty(dadosRetornados.get(CODIGO_ERRO))?
				Integer.parseInt(dadosRetornados.get(CODIGO_ERRO).toString()) : PROCESSAMENTO_EXITO;
				
		String mensagemErro = isNotEmpty(dadosRetornados.get(MENSAGEM_ERRO))?
				(String) dadosRetornados.get(MENSAGEM_ERRO) : PROCESSAMENTO_MENSAGEM_SEM_ERRO;
				
		if(codigoErro != PROCESSAMENTO_EXITO && codigoErro!= PROCESSADO_MAS_SEM_DADOS) {
			String mensagemLog = montarMensagemLog(nomeProcedure, codigoErro, mensagemErro);
			LOGGER.error(mensagemLog);
			throw new StoredProcedureException(mensagemLog);
		}
		
		LOGGER.info("Procedure executada com sucesso. Resultado: {}", dadosRetornados);
		
	}
	
	private String montarMensagemLog(String nomeProcedure, int codigoErro, Sring mensagemErro) {
		return "Erro ao validar retorno da procedure " + nomeProcedure + ". " + CODIGO_ERRO + "; " + codigoErro + " e "
				+ MENSAGEM_ERRO + ": " + mensagemErro + " .";
	}

	protected S executar(String procedureName, String nomeResultSet, RowMapper<?> rowMapper, E entrada) {
		SimpleJdbcCall jdbcCall = criarInstancia(procedureName, nomeResultSet, rowMapper);

		SqlParameterSource parametros = montarParametros(entrada);
		return chamarProcedure(jdbcCall, parametros);
	}

	protected S executar(String ownerSchema, String procedureName, String nomeResultSet,
			SqlParameter[] parametrosDeclarados, RowMapper<?> rowMapper, E entrada) {
		SimpleJdbcCall jdbcCall = criarInstancia(ownerSchema, procedureName, parametrosDeclarados, nomeResultSet,
				rowMapper);

		SqlParameterSource parametros = montarParametros(entrada);
		return chamarProcedure(jdbcCall, parametros);
	}

	public abstract S executar(E entrada);

	protected abstract SqlParameterSource montarParametros(E parametros);

	protected abstract S extrairDados(Map<String, Object> dados);

}
