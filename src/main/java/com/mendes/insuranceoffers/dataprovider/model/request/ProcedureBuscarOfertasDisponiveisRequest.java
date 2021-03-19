package com.mendes.insuranceoffers.dataprovider.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcedureBuscarOfertasDisponiveisRequest {
	
	private String codigoIdentificacaoCanalSeguroPrestamista;
	private String tipoSegmentoBanco;
	private int codigoParceiro;
	private int codigoProdutoParceiro;
	private int codigoTipoConvenioPrestamista;
	private int quantidadeIdade;

}
