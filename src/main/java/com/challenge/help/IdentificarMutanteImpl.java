package com.challenge.help;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	@Override
	public int esMutante(String[] adn) throws ADNNullException, FormatoInvalidoException {
		
		limpiar();

		if (adn == null || adn.length == 0) 
			throw new ADNNullException("El ADN no puede ser nulo");

		int filas = adn.length;
		int columnas = adn[0].length();

		if (filas < 3 && columnas < 3) 
			throw new FormatoInvalidoException("El ADN debe tener mas 3 columnas o filas");

		int encontrados = 0;

		if (filas == columnas) {
			sacarTodoSimetrico(adn);
			encontrados = buscarMutante(traemeTodo());
		} else {
			throw new FormatoInvalidoException("las filas y columnas deben ser iguales (NxN)");
		}

		return encontrados;
	}
	
	public int esMutante2(String[] adn) throws ADNNullException, FormatoInvalidoException{
		
		limpiar();
		int encontrado = 0;

		if (adn == null || adn.length == 0) 
			throw new ADNNullException("El ADN no puede ser nulo");

		int filas = adn.length;
		int columnas = adn[0].length();

		if (filas < 3 && columnas < 3) 
			throw new FormatoInvalidoException("El ADN debe tener mas 3 columnas o filas");

		if (filas == columnas) {
		
		encontrado = sacarTodoSimetrico2(adn);
		
		} else {
			throw new FormatoInvalidoException("las filas y columnas deben ser iguales (NxN)");
		}
		
		return encontrado;
	}

	private static List<String> palabras = new ArrayList<String>();
	private static String palabraRecolectada1 = "";
	private static String palabraRecolectada2 = "";
	private static String[] diagonalIzquierdaDerecha = null;
	private static String[] diagonalDerechaIzquierda = null;

	private static void limpiar() {
		palabras.clear();
		palabraRecolectada1 = "";
		palabraRecolectada2 = "";
		diagonalIzquierdaDerecha = null;
		diagonalDerechaIzquierda = null;
	}
	
	private static List<String> getPalabras() {
		return palabras;
	}

	private static List<String> traemeTodo() {
		List<String> all = Stream.concat(getPalabras().stream(), getDiagonales().stream()).collect(Collectors.toList());
		return all;
	}

	private static List<String> getDiagonales() {
		List<String> diagonales = Stream.concat(getDiagonalDI().stream(), getDiagonalID().stream())
				.collect(Collectors.toList());
		return diagonales;
	}

	private static List<String> getDiagonalID() {
		return Arrays.asList(diagonalIzquierdaDerecha);
	}

	private static List<String> getDiagonalDI() {
		return Arrays.asList(diagonalDerechaIzquierda);
	}

	@SuppressWarnings("unused")
	private void extraerDiagonales(String[] array) {
		int filas = array.length;
		int columnas = array[0].length();
		
		int arrayTamaño = (filas + columnas) - 1;
		diagonalIzquierdaDerecha = new String[arrayTamaño];
		diagonalDerechaIzquierda = new String[arrayTamaño];

		for (int i = 0; i < filas; i++) {
			for (int z = (columnas - 1), h = 0; z >= 0; z--, h++) {
				if (diagonalDerechaIzquierda[i + h] == null || diagonalIzquierdaDerecha[i + h] == null) {
					diagonalIzquierdaDerecha[i + h] = String.valueOf(array[i].charAt(h)).toUpperCase();
					diagonalDerechaIzquierda[i + h] = String.valueOf(array[i].charAt(z)).toUpperCase();
				} else {
					diagonalIzquierdaDerecha[i + h] += String.valueOf(array[i].charAt(h)).toUpperCase();
					diagonalDerechaIzquierda[i + h] += String.valueOf(array[i].charAt(z)).toUpperCase();
				}
			}
		}
	}

	private void sacarTodoSimetrico(String[] array) throws FormatoInvalidoException {
		int filas = array.length;
		int columnas = array[0].length();

		int arrayTamaño = (filas + columnas) - 1;
		diagonalIzquierdaDerecha = new String[arrayTamaño];
		diagonalDerechaIzquierda = new String[arrayTamaño];

		for (int i = 0; i < filas; i++) {
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
			
			/*if (!validarFormato(palabraRecolectada1)) 
				throw new FormatoInvalidoException("El ADN solo puede contener A-C-T-G");
			
			if (!validarFormato(palabraRecolectada2)) 
				throw new FormatoInvalidoException("El ADN solo puede contener A-C-T-G"); */

			palabras.add(palabraRecolectada1);
			palabras.add(palabraRecolectada2);
		}
	}
	
	
	private int sacarTodoSimetrico2(String[] array) throws FormatoInvalidoException {
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
				
				System.out.println(palabraRecolectada2 + " " + palabraRecolectada1);
				
	            if(palabraRecolectada2.contains("CCCC") || palabraRecolectada2.contains("TTTT") ||
	               palabraRecolectada2.contains("AAAA") || palabraRecolectada2.contains("GGGG") || 
	               palabraRecolectada1.contains("CCCC") || palabraRecolectada1.contains("TTTT") ||
	               palabraRecolectada1.contains("AAAA") || palabraRecolectada1.contains("GGGG"))
	                    encontrada = encontrada + 1;
			
			}else {
				return encontrada;
			}
		}
		
		if(encontrada != 2) {
			for(int w = 0; w < diagonalDerechaIzquierda.length; w++) {
				System.out.println(diagonalDerechaIzquierda[w] + " " + diagonalIzquierdaDerecha[w]);
	            if(diagonalDerechaIzquierda[w].contains("CCCC") || diagonalDerechaIzquierda[w].contains("TTTT") ||
	            		diagonalDerechaIzquierda[w].contains("AAAA") || diagonalDerechaIzquierda[w].contains("GGGG") || 
	            		diagonalIzquierdaDerecha[w].contains("CCCC") || diagonalIzquierdaDerecha[w].contains("TTTT") ||
	            		diagonalIzquierdaDerecha[w].contains("AAAA") || diagonalIzquierdaDerecha[w].contains("GGGG") 	) {
	            	encontrada = encontrada + 1;
	            }
			}
		}
		
			System.out.println(encontrada);
		return encontrada;
	}

	@SuppressWarnings("unused")
	private void sacarHorizontalVerticalMayorFila(String[] array) throws FormatoInvalidoException {
		int filas = array.length;
		int columnas = array[0].length();

		for (int i = 0; i < filas; i++) {
			palabraRecolectada1 = "";
			palabraRecolectada2 = "";

			for (int z = 0; z < filas; z++) {
				if (z < columnas)
					palabraRecolectada1 += String.valueOf(array[i].charAt(z));

				if (i < columnas)
					palabraRecolectada2 += String.valueOf(array[z].charAt(i));

			}

			if (!validarFormato(palabraRecolectada1)) 
				throw new FormatoInvalidoException("El ADN solo puede contener A-C-T-G");
			
			if (!validarFormato(palabraRecolectada2)) 
				throw new FormatoInvalidoException("El ADN solo puede contener A-C-T-G");

			if (palabraRecolectada1 != "")
				palabras.add(palabraRecolectada1);

			if (palabraRecolectada2 != "")
				palabras.add(palabraRecolectada2);
		}
	}

	@SuppressWarnings("unused")
	private void sacarHorizontalVerticalMayorColumna(String[] array) throws FormatoInvalidoException {
		int filas = array.length;
		int columnas = array[0].length();

		for (int i = 0; i < columnas; i++) {
			palabraRecolectada1 = "";
			palabraRecolectada2 = "";
			for (int z = 0; z < columnas; z++) {
				if (i < filas)
					palabraRecolectada1 += String.valueOf(array[i].charAt(z));

				if (z < filas)
					palabraRecolectada2 += String.valueOf(array[z].charAt(i));
			}

			if (!validarFormato(palabraRecolectada1)) 
				throw new FormatoInvalidoException("El ADN solo puede contener A-C-T-G");
			
			if (!validarFormato(palabraRecolectada2)) 
				throw new FormatoInvalidoException("El ADN solo puede contener A-C-T-G");

			if (palabraRecolectada1 != "")
				palabras.add(palabraRecolectada1);

			if (palabraRecolectada2 != "")
				palabras.add(palabraRecolectada2);
		}
	}

	private boolean validarFormato(String palabra) {
		boolean valido = true;
		/*if (!palabra.contains("A") && !palabra.contains("T") && !palabra.contains("C") && !palabra.contains("G") ) {
			return false;
		}*/
		
		for(int i = 0; i< palabra.length(); i++) {
			if(palabra.charAt(i) != 'A' && palabra.charAt(i) != 'T' && palabra.charAt(i) != 'C' && palabra.charAt(i) != 'G') {
				return false;
			}
		}
		
		return valido;
	}

	private int buscarMutante(List<String> dna) {
		List<String> resultado = dna.stream().filter(s -> contiene(s)).collect(Collectors.toList());
		return resultado.size();
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

	private boolean contiene(String item) {
		boolean en = false;
		if (item.contains("CCCC") || item.contains("AAAA") || item.contains("GGGG") || item.contains("TTTT")) {
			en = true;
		}
		return en;
	}

}
