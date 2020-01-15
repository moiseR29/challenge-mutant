package com.challenge.help;

import org.springframework.stereotype.Component;

import com.challenge.excepciones.ADNNullException;
import com.challenge.excepciones.FormatoInvalidoException;

/**
 * Clase Helper la cual contiene el proceso de detecta si es Mutante ó Humano
 * @author mrebatta
 *
 */
@Component
public class IdentificarMutanteImpl implements IdentificarMutante {
	
	private static String palabraRecolectada1 = "";
	private static String palabraRecolectada2 = "";
	private static String[] diagonalIzquierdaDerecha = null;
	private static String[] diagonalDerechaIzquierda = null;
	
	public int esMutante(String[] adn) throws ADNNullException, FormatoInvalidoException{
		
		int encontrado = 0;

		if (adn == null || adn.length == 0) 
			throw new ADNNullException("El ADN no puede ser nulo");

		int filas = adn.length;
		int columnas = adn[0].length();

		if (filas < 3 && columnas < 3) 
			throw new FormatoInvalidoException("El ADN debe tener mas 3 columnas o filas");

		if (filas == columnas) {
		
		encontrado = buscarPatron(adn);
		
		} else {
			throw new FormatoInvalidoException("las filas y columnas deben ser iguales (NxN)");
		}
		
		return encontrado;
	}
	

	
	private int buscarPatron(String[] array) throws FormatoInvalidoException {
		int filas = array.length;
		int columnas = array[0].length();
		int encontrada = 0;

		int arrayTamaño = (filas + columnas) - 1;
		diagonalIzquierdaDerecha = new String[arrayTamaño];
		diagonalDerechaIzquierda = new String[arrayTamaño];

		for (int i = 0; i < filas; i++) {
			if(encontrada != 2) {
				
				palabraRecolectada1 = "";
				palabraRecolectada2 = "";
				
				if(filas != array[i].length()) 
					throw new FormatoInvalidoException("las filas y columnas deben ser iguales (NxN)");
				
				for (int z = (columnas - 1), h = 0; z >= 0; z--, h++) {
					if (validar(String.valueOf(array[i].charAt(h)).toUpperCase()) == false) 
						throw new FormatoInvalidoException("El ADN solo puede contener A-C-T-G");
					
					if (validar(String.valueOf(array[h].charAt(i)).toUpperCase()) == false) 
						throw new FormatoInvalidoException("El ADN solo puede contener A-C-T-G");
					
					palabraRecolectada1 += String.valueOf(array[i].charAt(h)).toUpperCase();
					palabraRecolectada2 += String.valueOf(array[h].charAt(i)).toUpperCase();

					if (diagonalDerechaIzquierda[i + h] == null || diagonalIzquierdaDerecha[i + h] == null) {
						if(validar(String.valueOf(array[i].charAt(h)).toUpperCase()) == false)
							throw new FormatoInvalidoException("El ADN solo puede contener A-C-T-G");
							
						if(validar(String.valueOf(array[i].charAt(z)).toUpperCase()) == false)
							throw new FormatoInvalidoException("El ADN solo puede contener A-C-T-G");
						
						diagonalIzquierdaDerecha[i + h] = String.valueOf(array[i].charAt(h)).toUpperCase();
						diagonalDerechaIzquierda[i + h] = String.valueOf(array[i].charAt(z)).toUpperCase();
					} else {
						diagonalIzquierdaDerecha[i + h] += String.valueOf(array[i].charAt(h)).toUpperCase();
						diagonalDerechaIzquierda[i + h] += String.valueOf(array[i].charAt(z)).toUpperCase();
					}

				}
								
	            if(helpContent(palabraRecolectada1) || helpContent(palabraRecolectada2)) encontrada += 1;
			
			}else {
				return encontrada;
			}
		}

			for(int w = 0; w < diagonalDerechaIzquierda.length; w++) {
				
				if(encontrada == 2) return encontrada;
				
				if(diagonalDerechaIzquierda[w].length() > 3) {
		            if(helpContent(diagonalDerechaIzquierda[w]) || helpContent(diagonalIzquierdaDerecha[w])) {
		            	encontrada += 1;
		            }
				}
			}
		
		
		return encontrada;
	}
	
	private boolean helpContent(String palabra) {
		boolean contiene = false;
		if(palabra.contains("CCCC") || palabra.contains("TTTT") || palabra.contains("AAAA") || palabra.contains("GGGG")) {
			contiene = true;
		}
		return contiene;
	}
	
	private boolean validar(String letra) {
		boolean valido = false;
			
			switch(letra) {
				case "C":
				case "A":
				case "G":
				case "T":
					valido = true;
					break;				
			}
			
		return valido;
	}

}
