package com.mendes.insuranceoffers.dataprovider.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OfferRepository {
	
	private ProcedureBuscarOfertasDisponiveis procedureBuscarOfertasDisponiveis;
	
	private ProcedurePrecificacao procedurePrecificacao;
	
	@Autowired
	public OfferRepository(ProcedureBuscarOfertasDisponiveis procedureBuscarOfertasDisponiveis, ProcedurePrecificacao procedurePrecificacao) {
		this.procedureBuscarOfertasDisponiveis = procedureBuscarOfertasDisponiveis;
		this.procedurePrecificacao = procedurePrecificacao;	
	}
	
	public Optional<List<ProcedureBuscarOfertasDisponiveisResponse>> searchAvailableOffers(InsuredRisk insuredRisk) {
		return procedureBuscarOfertasDisponiveis.executar(ofertasDisponiveisRequest);
	}
	
	public Optional<List<ProcedurePrecificacaoResponse>> searchPricing(ProcedurePrecificacaoRequest procedurePrecificacaoRequest) {
		return procedurePrecificacao.executar(procedurePrecificacaoRequest);
	}

}
