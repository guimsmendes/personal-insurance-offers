package com.mendes.insuranceoffers.dataprovider.http.decoder;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

@Component
public class HttpErrorDecoder implements ErrorDecoder{
	
	private final ErrorDecoder defaultErrorDecoder = new Default();
	
	@Value("#{'${mendes.feign.client.config.http-status-codes:}'.split(',')}")
	private List<Integer> httpStatusCodeList;
	
	@Value("${mendes.feign.client.config.retry.server.error:false}")
	private boolean retryServerError;
	
	@Value("${mendes.feign.client.config.client.server.error:false}")
	private boolean retryClientError;
	
	@Override
	public Exception decode(String s, Response response) {
		Exception exception = defaultErrorDecoder.decode(s, response);
		if(exception instanceof RetryableException) {
			return exception;
		}
		HttpStatus httpStatus = HttpStatus.valueOf(response.status());
		
		if((httpStatus.is5xxServerError() && retryServerError) || (httpStatus.is4xxClientError() && retryClientError)) {
			return new RetryableException(response.status(),
					"Unable to consume service.",
					response.request().httpMethod(),
					exception,
					null,
					response.request());
		}
		
		boolean retryHttpStatusCode = httpStatusCodeList.stream()
				.anyMatch(httpStatusCode -> httpStatusCode.equals(response.status()));
		
		if(retryHttpStatusCode) {
			return new RetryableException(response.status(), "Unable to consume service.",
					response.request().httpMethod(), exception, null, response.request());
		}
		return exception;
	}

}
