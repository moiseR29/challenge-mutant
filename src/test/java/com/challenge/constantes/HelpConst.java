package com.challenge.constantes;

public class HelpConst {
	
	public static final String MUTANTE = "MUTANTE";
	public static final String HUMANO = "HUMANO";
	
	public static final String URI_MUTANT = "/mutant";
	public static final String URI_STATS = "/stats";
	
	public static final String CONTENT_TYPE_JSON = "application/json";
	
	public static final String MOCKMUTANTE = "{\"dna\":[\"GTGCGA\",\"CACTGC\",\"TCATGG\",\"AGAAGG\",\"CCCTTA\",\"TCCCCG\"]}";
	public static final String MOCKHUMANO = "{\"dna\":[\"GTGCGA\",\"CACTAC\",\"TCCTGG\",\"CGAAAG\",\"CCCGTA\",\"TCGCTG\"]}";
	public static final String MOCKINVALID_1 = "{\"dna\":[\"AXGCGT\",\"CAGTGC\",\"TTCTGG\",\"TGAAGG\",\"TCGCTG\"]}";
	public static final String MOCKINVALID_2 = "{\"dna\":[\"ATGC\",\"CAGTGC\",\"TTCTGG\",\"AGAAGG\",\"TCGCTG\"]}";
	public static final String MOCKINVALID_3 = "{\"dna\":[]}";
	
	
	public static final String[] DNAMUTANTE_1 =  new String[] { 
			  "ATGCGA", 
			  "CAGTGC", 
			  "TTATGG", 
			  "AGAAGG", 
			  "CCCCTA", 
			  "TCGCTG"};
	
	public static final String[] DNAMUTANTE_2 = new String[] { 
			  "GTGCGA", 
			  "CACTGC", 
			  "TCATGG", 
			  "CGATAG", 
			  "CCCTTA", 
			  "TCCCCG"};
	
	public static final String[] DNAHUMANO_1 =  new String[] { 
			  "GTGCGA", 
			  "CACTAC", 
			  "TCCTGG", 
			  "CGAAAG", 
			  "CCCGTA", 
			  "TCGCTG"};
	
	public static final String[] DNAHUMANO_2 =  new String[] { 
			  "GTGCGA", 
			  "CACTGC", 
			  "TCATGG", 
			  "CGAAAG", 
			  "CCCGTA", 
			  "TCGCTG"};
	
	public static final String[] DNAINVALID_1 =  new String[] { 
			  "ATGCG", 
			  "CAGTGC", 
			  "TTATOG", 
			  "TGAAGG", 
			  "CCCCTA", 
			  "TCGCTG"};
	
	public static final String[] DNAINVALID_2 =  new String[] { 
			  "ATGCGA", 
			  "CAGTGC", 
			  "TTATOG", 
			  "XGAAGG", 
			  "CCCCTA", 
			  "TCGCTG"};
	
	public static final String[] DNAINVALID_3 =  new String[10];

}
