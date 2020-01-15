package com.challenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.excepciones.ADNNullException;
import com.challenge.excepciones.BDException;
import com.challenge.excepciones.FormatoInvalidoException;
import com.challenge.help.Convert;
import com.challenge.help.IdentificarMutante;
import com.challenge.help.Tipo;
import com.challenge.models.entity.Individuo;

/**
 * Clase Service Mutante
 * @author Moises Rebatta
 *
 */
@Service
public class MutanteServiceImpl implements IMutanteService{
	
	@Autowired
	IdentificarMutante identificarMutante;
	
	@Autowired
	StatsServiceImpl individuoService;

	@Override
	public boolean isMutant(String[] dna) throws ADNNullException, FormatoInvalidoException,BDException {
		boolean esMutante = false;
		Individuo individuo = new Individuo();
		int existe = 0;
		int contador;
		
		existe = identificarMutante.esMutante(dna);
		
		if(existe > 1) {
			esMutante = true;
			individuo.setAdn(Convert.adnGetSring(dna));
			individuo.setTipo(Tipo.MUTANTE);
			contador = individuoService.findByAdn(individuo.getAdn()).size();
			
			if(contador == 0) 
				individuoService.insert(individuo);
		}else {
			individuo.setAdn(Convert.adnGetSring(dna));
			individuo.setTipo(Tipo.HUMANO);
			contador = individuoService.findByAdn(individuo.getAdn()).size();
			
			if(contador == 0) 
				individuoService.insert(individuo);
		}
		
		return esMutante;
	}

}
