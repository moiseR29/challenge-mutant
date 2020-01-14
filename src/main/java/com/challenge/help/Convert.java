package com.challenge.help;

/**
 * Clase Helper
 * Convierte un String[] a String
 * @author Moises Rebatta
 *
 */
public class Convert {

	public static String adnGetSring(String[] h) {
		String strAdn = convertir(h);
		return strAdn;
	}

	private static String convertir(String[] h) {
		String devolver = "";
		for (int i = 0; i < h.length; i++) {
			if (i == h.length - 1)
				devolver += (h[i]);
			else
				devolver += (h[i] + " ");
		}
		return devolver;
	}

}
