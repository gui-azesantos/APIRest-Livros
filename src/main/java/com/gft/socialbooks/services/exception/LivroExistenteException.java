package com.gft.socialbooks.services.exception;

public class LivroExistenteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6378466831622544014L;

	
	public LivroExistenteException(String mensagem) {
		super(mensagem);
	}

	public LivroExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
