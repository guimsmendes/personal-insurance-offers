package com.mendes.insuranceoffers.dataprovider.repository.exception;

public class StoredProcedureException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StoredProcedureException(String mensagem) {
		super(mensagem);
	}
	
	public StoredProcedureException(String mensagem, Throwable throwable) {
		super(mensagem, throwable);
	}

}
