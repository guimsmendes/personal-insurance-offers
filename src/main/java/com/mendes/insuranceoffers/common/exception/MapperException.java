package com.mendes.insuranceoffers.common.exception;

public class MapperException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MapperException(Throwable throwable) {
		super("Unable to transform object.", throwable);
	}
	
	public MapperException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
