package com.mendes.insuranceoffers.dataprovider.model.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcedurePrecificacaoRequest {
	
	private String codigoIdentificacaoOfertaCreditoConsignado;
	private String codigoOfertaSeguro;
	private int codigoParceiro;
	private int codigoProdutoParceiro;
	private int codigoRamo;
	private int codigoProduto;
	private int codigoPlano;
	private String codigoCanal;
	private String tipoSegmentoBancoItau;
	private int codigoTipoConvenioPrestamista;
	private BigDecimal valorCredito;
	private LocalDate dataNascimento;	
	private String nomeUsuario;
	private int codigoBanco;
	private int numeroAgencia;
	private static final String DIGITO = StringUtils.EMPTY;
	private int numeroContaCorrente;
	private String digitoVerificadorContaCorrente;
	private int quantidadeParcelasCreditoConsignado;
	private LocalDate dataVencimentoPrimeiraParcela;
	private int diaVencimentoParcela;
	private final LocalDate dataOperacaoCredito = LocalDate.now();
	private String indicadorPiloto;
	private final LocalDate dataProposta = LocalDate.now();
	private LocalDate dataInicioVigencia;
	private static final String INDICADOR_TRACE = "N";

}
