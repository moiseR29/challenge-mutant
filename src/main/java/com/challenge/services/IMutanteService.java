package com.challenge.services;

import com.challenge.excepciones.ADNNullException;
import com.challenge.excepciones.BDException;
import com.challenge.excepciones.FormatoInvalidoException;

public interface IMutanteService {
	
	boolean isMutant(String[] dna) throws ADNNullException, FormatoInvalidoException,BDException ;

}
