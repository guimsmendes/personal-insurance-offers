package com.mendes.insuranceoffers.dataprovider;

import com.mendes.insuranceOffers.usecase.gateway.OfferGateway;

public class OfertaDataProvider implements OfferGateway{
	
	private OfertaRepository ofertaRepository;
	
	private OfertaDataProviderMapper ofertaDataProviderMapper;
	
	private GestaoOfertaListenerMapper gestaoOfertaListenerMapper;
	
	
	@Override
	public Optional<List<OfertaDomain>> buscarOfertasDisponiveis(GestaoOfertaListenerRequest ofertaListenerRequest){
		OfertaDomain ofertaDomain = gestaoOfertaListenerMapper.toOfertaDomain(ofertaListenerRequest);
		return ofertaRepository.buscarOfertasDisponiveis(ofertaDataProviderMapper.toProcedureRequest(ofertaListenerRequest))
				.map(ofertasDisponiveis -> agregarDados(ofertasDisponiveis, ofertaDomain));
	}
	
	@Override
	public Optional<OfertaDomain> precificar(OfertaDomain ofertaDomain){
		return ofertaRepository.buscarPrecificacao(ofertaDataProviderMapper.toProcedurePrecificacaoRequest(ofertaDomain))
				.map(procedurePrecificacaoResponse -> ofertaDataProviderMapper.toDomain(procedurePrecificacaoResponse));
	}
	
	private List<OfertaDomain> agregarDados(List<ProcedureBuscarOfertasDisponiveisResponse> ofertaDisponiveis, OfertaDomain ofertaDomain) {
		List<OfertaDomain> ofertasDisponiveisDomain = ofertaDataProviderMapper.toListaOfertaDomain(ofertasDisponíveis);
		return ofertasDisponiveisDomain
				.stream()
				.map(ofertaDisponivel -> {
					ofertaDomain.setDataInicioVigencia(ofertaDisponivel.getDataInicioVigencia());
					ofertaDomain.setCodigoCombo(ofertaDisponivel.getCodigoCombo());
					ofertaDomain.setProduto.getProduto());
					ofertaDomain.setCodigoOfertaSeguro(ofertaDisponivel.getCodigoOfertaSeguro());
					return ofertaDomain;
				})
				.collect(Collectors.toList());
	}

}
