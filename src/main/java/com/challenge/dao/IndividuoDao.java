package com.challenge.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.models.entity.Individuo;

@Repository
public interface IndividuoDao extends JpaRepository<Individuo, Long>{
	
	public List<Individuo> findByTipo(String tipo);
	
	public List<Individuo> findByAdn(String adn);
	
	
	
}