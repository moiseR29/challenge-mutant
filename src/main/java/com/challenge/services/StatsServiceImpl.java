package com.challenge.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.dao.IndividuoDao;
import com.challenge.excepciones.BDException;
import com.challenge.help.Stats;
import com.challenge.help.Tipo;
import com.challenge.models.entity.Individuo;

/**
 * Clase Service Stats
 * @author Moises Rebatta
 *
 */
@Service
public class StatsServiceImpl implements StatsService{

	@Autowired
	private IndividuoDao individuoDao;

	@Override
	@Transactional
	public Individuo insert(Individuo individuo) throws BDException {
		return individuoDao.save(individuo);
	}

	@Override
	public List<Individuo> findByTipo(String tipo) throws BDException {
		return individuoDao.findByTipo(tipo);
	}

	@Override
	public List<Individuo> findByAdn(String adn) throws BDException {
		return individuoDao.findByAdn(adn);
	}
	
	public Stats findStats() throws BDException {
		return new Stats(findByTipo(Tipo.HUMANO).size(), findByTipo(Tipo.MUTANTE).size());
	}

}
