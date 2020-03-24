package com.gft.socialbooks.services.exception;

public class AutorNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 4139684358775679229L;

	public AutorNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public AutorNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
