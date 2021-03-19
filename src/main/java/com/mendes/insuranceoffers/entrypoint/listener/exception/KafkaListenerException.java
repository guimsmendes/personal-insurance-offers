package com.mendes.insuranceoffers.entrypoint.listener.exception;

public class KafkaListenerException extends RuntimeException{

	private static final long serialVersionUID = -7578578891371360781L;
	
	public KafkaListenerException(String message) {
		super(message);
	}
	
	public KafkaListenerException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
