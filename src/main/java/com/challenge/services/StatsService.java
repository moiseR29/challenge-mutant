package com.challenge.services;

import java.util.List;

import com.challenge.excepciones.BDException;
import com.challenge.help.Stats;
import com.challenge.models.entity.Individuo;

public interface StatsService {
	
	public Individuo insert(Individuo individuo) throws BDException;
	
	/*public Long countByTipo(String tipo) throws BDException;
	
	public Long countByAdn(String adn) throws BDException;*/
	
	public List<Individuo> findByTipo(String tipo) throws BDException;
	
	public List<Individuo> findByAdn(String adn) throws BDException;
	
	public Stats findStats() throws BDException;
}