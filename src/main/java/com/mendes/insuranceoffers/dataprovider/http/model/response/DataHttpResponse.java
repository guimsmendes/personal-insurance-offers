package com.mendes.insuranceoffers.dataprovider.http.model.response;

public class DataHttpResponse<T>{
	
	private T data;
	
	public void setData(T data) {
		this.data = data;
	}
	
	public T getData() {
		return data;
	}

}
