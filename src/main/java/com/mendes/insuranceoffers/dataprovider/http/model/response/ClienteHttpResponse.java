package com.mendes.insuranceoffers.dataprovider.http.model.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonNaming(SnakeCaseStrategy.class)
public class ClienteHttpResponse {
	
	private UUID idCliente;
	private String tipoPessoa;
	private String cpfCnpj;
	private String nomeCompleto;
	private LocalDate dataNascimentoFundacao;
	private char sexo;
	private int estadoCivil;
	private List<EnderecoHttpResponse> enderecos;
	private List<TelefoneHttpResponse> telefones;
	private List<EmailHttpResponse> emails;
	
	@Getter
	@Setter
	@JsonNaming(SnakeCaseStrategy.class)
	public static class EnderecoHttpResponse {
		private UUID idEndereco;
		private boolean principal;
		private boolean residencial;
		private String logradouro;
		private String numero;
		private String bairro;
		private String cep;
		private String cidade;
		private String uf;
		private String pais;
		private LocalDateTime dataAtualizacao;
	}
	
	@Getter
	@Setter
	@JsonNaming(SnakeCaseStrategy.class)
	public static class TelefoneHttpResponse {
		private UUID idTelefone;
		private boolean principal;
		private boolean celular;
		private int ddi;
		private int ddd;
		private int numero;
		private LocalDateTime dataAtualizacao;
		
	}
	
	@Getter
	@Setter
	@JsonNaming(SnakeCaseStrategy.class)
	public static class EmailHttpResponse {
		private UUID idEmail;
		private boolean principal;
		private String email;
		private LocalDateTime dataAtualizacao;
		
	}

}
