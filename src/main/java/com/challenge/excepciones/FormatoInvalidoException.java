package com.challenge.excepciones;

/**
 * Excepcion para formatos invalidos
 * @author Moises Rebatta
 *
 */
public class FormatoInvalidoException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	
	public FormatoInvalidoException(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String getMessage() {
		return mensaje;
	}
	
	

}