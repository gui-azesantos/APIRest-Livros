package com.gft.socialbooks.services.exception;

public class AutorExistenteException extends RuntimeException{

		
	/**
	 * 
	 */
	private static final long serialVersionUID = -3327429173609053936L;

	public AutorExistenteException(String mensagem) {
		super(mensagem);
	}

	public AutorExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
