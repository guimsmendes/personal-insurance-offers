package com.mendes.insuranceoffers.dataprovider.http.interceptor;

import java.util.UUID;

import javax.security.sasl.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.mendes.ofertas.entrypoint.listener.GestaoOfertaListener;

import com.mendes.ofertas.dataprovider.http.constant.*;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class AuthorizationInterceptor implements RequestInterceptor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationInterceptor.class);
	
	@Value("${mendes.client.credentials.ofertas.client.id}")
	private String clientId;
	
	private static final int SECRET_TOKEN_SERVICE_TIMEOUT = 290000;
	
	private static final String BEARER = "Bearer";
	
	private static final TokenGenerator TOKEN_GENERATOR = new CachedDynamicSecretTokenGenerator(SECRET_TOKEN_SERVICE_TIMEOUT);

	@Override
	public void apply(RequestTemplate template) {
		template.header(X_APIKEY, clientId);
		template.header(X_FLOW_ID, X_FLOW_ID_VALUE);
		template.header(X_CORRELATION_ID, UUID.randomUUID().toString());
		
		try {
			JSONObject token = TOKEN_GENERATOR.getValidAuthorizationToken(cliendId);
			String accessToken = token.getString("access_token");
			template.header(AUTHORIZATION, accessToken);
		}
		catch (AuthenticationException e) {
			LOG.error("Mensagem da exception: {}. StackTrace: {}", e.getMessage(), e.getStackTrace());
		}
		
	}
	
	

}
