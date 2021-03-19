package com.mendes.insuranceoffers.dataprovider.http;

import org.springframework.http.ResponseEntity;

import com.mendes.ofertas.dataprovider.http.model.response.DataHttpResponse;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

public class DataProviderHttp {
	
	protected <T> T getData(ResponseEntity<DataHttpResponse<T>> responseEntity) {
		if(isNotEmpty(responseEntity.getBody())) {
			return responseEntity.getBody().getData();
		}
		return null;
	}

}
