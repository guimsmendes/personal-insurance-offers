package com.mendes.insuranceoffers.dataprovider.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OfertaRepository {
	
	private ProcedureBuscarOfertasDisponiveis procedureBuscarOfertasDisponiveis;
	
	private ProcedurePrecificacao procedurePrecificacao;
	
	@Autowired
	public OfertaRepository(ProcedureBuscarOfertasDisponiveis procedureBuscarOfertasDisponiveis, ProcedurePrecificacao procedurePrecificacao) {
		this.procedureBuscarOfertasDisponiveis = procedureBuscarOfertasDisponiveis;
		this.procedurePrecificacao = procedurePrecificacao;	
	}
	
	public Optional<List<ProcedureBuscarOfertasDisponiveisResponse>> buscarOfertasDisponiveis(ProcedureBuscarOfertasDisponiveisRequest ofertasDisponiveisRequest) {
		return procedureBuscarOfertasDisponiveis.executar(ofertasDisponiveisRequest);
	}
	
	public Optional<List<ProcedurePrecificacaoResponse>> buscarPrecificacao(ProcedurePrecificacaoRequest procedurePrecificacaoRequest) {
		return procedurePrecificacao.executar(procedurePrecificacaoRequest);
	}

}
