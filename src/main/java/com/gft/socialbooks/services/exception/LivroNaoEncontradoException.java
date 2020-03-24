package com.gft.socialbooks.services.exception;

public class LivroNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4139684358775679229L;

	public LivroNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public LivroNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
