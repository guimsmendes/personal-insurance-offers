package com.mendes.insuranceoffers.dataprovider.model.response;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcedureBuscarOfertasDisponiveisResponse {
	
	private int codigoCombo;
	private LocalDate dataInicioVigencia;
	private int codigoProdutoSeguro;
	private int codigoRamo;
	private int planoProduto;
	private int tipoConvenio;
	private String nomeProdutoSeguro;
	private String processoSusep;
	private String indicadorPiloto;
	private final UUID codigoOfertaSeguro = UUID.randomUUID();

}
