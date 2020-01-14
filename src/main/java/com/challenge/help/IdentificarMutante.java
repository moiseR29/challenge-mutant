package com.challenge.help;

import com.challenge.excepciones.ADNNullException;
import com.challenge.excepciones.FormatoInvalidoException;

/**
 * Interfaz del helper para Identificar Mutante
 * @author Moises Rebatta
 *
 */
public interface IdentificarMutante {

	int esMutante(String[] adn) throws ADNNullException, FormatoInvalidoException;
	
	int esMutante2(String[] adn) throws ADNNullException, FormatoInvalidoException;

}
