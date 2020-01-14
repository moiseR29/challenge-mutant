package com.challenge.help;

/**
 * Clase Help que se encarga a partir de un objeto mostrar los resultados mas agradables
 * @author Moises Rebatta
 *
 */
public class Stats {
	
	private int count_mutant_dna;
	private int count_human_dna;
	private double ratio;
	
	public Stats(int humanos, int mutantes) {
		this.count_human_dna = humanos;
		this.count_mutant_dna = mutantes;
		setRatio(humanos, mutantes);
	}
	
	public int getCount_mutant_dna() {
		return count_mutant_dna;
	}
	
	public int getCount_human_dna() {
		return count_human_dna;
	}
	
	public void setRatio(int humanos, int mutantes) {
		if(count_human_dna == 0 && count_mutant_dna == 0) ratio = 0;			
		else if(count_human_dna == 0 && count_mutant_dna != 0) ratio = 0;
		else ratio = count_mutant_dna / count_human_dna;
	}
	
	public double getRatio() {
		return ratio;
	}

}
