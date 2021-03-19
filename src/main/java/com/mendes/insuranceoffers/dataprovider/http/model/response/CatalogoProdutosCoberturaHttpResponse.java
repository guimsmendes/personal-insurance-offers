package com.mendes.insuranceoffers.dataprovider.http.model.response;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(SnakeCaseStrategy.class)
public class CatalogoProdutosCoberturaHttpResponse {
	
	private String codigoProdutoParceiro;
	private UUID idProduto;
	private String codigoRamo;
	private String nomeProduto;
	private String codigoSegmento;
	private List<Cobertura> coberturas;
	
	@Getter
	@Setter
	@JsonNaming(SnakeCaseStrategy.class)
	public static class Cobertura{
		private UUID idCobertura;
		private String codigoCoberturaParceiro;
		private String nomeCobertura;
		private List<ResumoCobertura> resumosCobertura;
		
		@Getter
		@Setter
		@JsonNaming(SnakeCaseStrategy.class)
		public static class ResumoCobertura{
			private String ordem;
			private String conteudo;
			private String tipo;
			private String agrupamento;
		}
		
	}
	
	

}
