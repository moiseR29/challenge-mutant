package com.challenge.excepciones;

/**
 * Excepcion la cual detecta cuando el parametro es nulo ó 0
 * @author Moises Rebatta
 *
 */
public class ADNNullException extends Exception{

	private static final long serialVersionUID = 1L;

	private String mensaje;
	
	public ADNNullException(String mensaje) {
		this.mensaje= mensaje;
	}

	@Override
	public String getMessage() {
		return mensaje;
	}	
}