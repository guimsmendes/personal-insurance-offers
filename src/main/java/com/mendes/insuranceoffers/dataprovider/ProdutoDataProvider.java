package com.mendes.insuranceoffers.dataprovider;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mendes.ofertas.dataprovider.http.CatalogoProdutosHttpConsumer;
import com.mendes.ofertas.dataprovider.http.enums.CatalogoProdutosExpand;
import com.mendes.ofertas.dataprovider.mapper.ProdutoDataProviderMapper;

@Component
public class ProdutoDataProvider extends DataProviderHttp implements ProductGateway{
	
	@Autowired
	private CatalogoProdutosHttpConsumer catalogoProdutosHttpConsumer;
	
	@Autowired
	private ProdutoDataProviderMapper produtoDataProviderMapper;
	
	@Override
	public Optional<List<CoverDomain>> buscarCoberturas(UUID uuidProduto){
		
		ResponseEntity<DataHttpResponse<CatalogoProdutosCoberturaHttpResponse>> responseListarCoberturas = 
				catalogoProdutosHttpConsumer.listarCoberturas(uuidProduto,  
						CatalogoProdutosExpand.COBERTURAS,
						CatalogoProdutosExpand.RESUMO);
		
		CatalogoProdutosCoberturaHttpResponse catalogoProdutosCoberturaHttpResponse = getData(responseListarCoberturas);
		
		if(isNotEmpty(catalogoProdutosCoberturaHttpResponse)) {
			List<CatalogoProdutosCoberturaHttpResponse.Cobertura> catalogoProdutosCoberturaHttpResponses =
					catalogoProdutosCoberturaHttpResponse.getCoberturas();
			
			if(isNotEmpty(catalogoProdutosCoberturaHttpResponses)) {
				return Optional.of(catalogoProdutosCoberturaHttpResponses
						.stream()
						.map(cobertura -> produtoDataProviderMapper.toCoberturaDomain(cobertura))
						.collect(Collectors.toList()));
			}
		}
		return Optional.empty();
	}

}
