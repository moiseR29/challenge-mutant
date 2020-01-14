package com.challenge.excepciones;

public class BDException extends Exception{
	
	private static final long serialVersionUID = 1L;

	private String mensaje;
	
	public BDException(String mensaje) {
		this.mensaje= mensaje;
	}

	@Override
	public String getMessage() {
		return mensaje;
	}	
}
