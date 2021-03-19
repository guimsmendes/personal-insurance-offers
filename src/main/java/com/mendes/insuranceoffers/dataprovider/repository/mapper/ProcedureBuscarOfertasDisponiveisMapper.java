package com.mendes.insuranceoffers.dataprovider.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

import com.mendes.ofertas.dataprovider.model.response.ProcedureBuscarOfertasDisponiveisResponse;

public class ProcedureBuscarOfertasDisponiveisMapper implements RowMapper<ProcedureBuscarOfertasDisponiveisResponse> {

	@Override
	public ProcedureBuscarOfertasDisponiveisResponse mapRow(@NonNull ResultSet resultSet, int rowNum) throws SQLException {
		return isNotEmpty(resultSet) ? toEntity(resultSet) : null;
	}

	private ProcedureBuscarOfertasDisponiveisResponse toEntity(ResultSet resultSet) {
		ProcedureBuscarOfertasDisponiveisResponse procedureResponse = new ProcedureBuscarOfertasDisponiveisResponse();
		
		try {
			procedureResponse.setDataInicioVigencia(resultSet.getDate(DATA_INICIO_VIGENCIA_COMBO).toLocalDate());
			procedureResponse.setCodigoCombo(resultSet.getInt(CODIGO_COMBO));
			procedureResponse.setCodigoProdutoSeguro(resultSet.getInt(CODIGO_PRODUTO));
			procedureResponse.setCodigoRamo(resultSet.getInt(CODIGO_RAMO));
			procedureResponse.setPlanoProduto(resultSet.getInt(CODIGO_PLANO_PRODUTO));
			procedureResponse.setTipoConvenio(resultSet.getInt(CODIGO_TIPO_CONVENIO_PRESTAMISTA));
			procedureResponse.setNomeProdutoSeguro(resultSet.getString(columnIndex));
		} catch (Exception e) {
			throw new MapperException(e);
		}
		return procedureResponse;
	}
	
}
