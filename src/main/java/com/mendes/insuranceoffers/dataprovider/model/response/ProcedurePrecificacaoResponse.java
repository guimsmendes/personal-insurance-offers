package com.mendes.insuranceoffers.dataprovider.model.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcedurePrecificacaoResponse {
	
	private BigDecimal numeroOfertaSeguro;
	private BigDecimal valortotal;
	private int quantidadeParcelasSeguroPrestamista;
	private BigDecimal valorParcelaSeguroPrestamista;
	private BigDecimal percentualTaxaJurosEfetivo;
	private BigDecimal valorTotalTaxaJuros;

}
