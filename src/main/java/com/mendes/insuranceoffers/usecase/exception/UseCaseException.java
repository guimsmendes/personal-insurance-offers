package com.mendes.insuranceoffers.usecase.exception;

public class UseCaseException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UseCaseException() {}
	
	public static class OfferNotFound extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public OfferNotFound(String message) {
			super(message);
		}
	}
	
	public static class CoverNotFound extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public CoverNotFound(String message) {
			super(message);
		}
	}
	
	public static class ClientNotFound extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public ClientNotFound(String message) {
			super(message);
		}
	}
	
	public static class UnableToPriceOffer extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public UnableToPriceOffer(String message) {
			super(message);
		}
	}
	
	public static class UnableToPersistInsuranceProposal extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public UnableToPersistInsuranceProposal(String message) {
			super(message);
		}
	}
	

}
