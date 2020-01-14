package com.challenge.services;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.challenge.constantes.HelpConst;
import com.challenge.dao.IndividuoDao;
import com.challenge.excepciones.BDException;
import com.challenge.help.Convert;
import com.challenge.models.entity.Individuo;
import com.challenge.services.StatsServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatsServiceImplTest {

	@MockBean
	private IndividuoDao repo;
	
	@Autowired
	private StatsServiceImpl service;
	
	@Test
	public void getMutantes_OK() throws BDException {
		when(repo.findByTipo(HelpConst.MUTANTE)).thenReturn(
				Stream.of(new Individuo(Convert.adnGetSring(HelpConst.DNAMUTANTE_1),HelpConst.MUTANTE))
				.collect(Collectors.toList())
			);
		assertEquals(1, service.findByTipo(HelpConst.MUTANTE).size());
	}
	
	@Test
	public void getHuman_Ok() throws BDException {
		when(repo.findByTipo(HelpConst.HUMANO)).thenReturn(
				Stream.of(new Individuo(Convert.adnGetSring(HelpConst.DNAHUMANO_1),HelpConst.HUMANO))
				.collect(Collectors.toList())
			);
		assertEquals(1, service.findByTipo(HelpConst.HUMANO).size());
	}	

	@Test
	public void getMutantes_Bad() throws BDException {
		when(repo.findByTipo(HelpConst.MUTANTE)).thenReturn(
				Stream.of(new Individuo(Convert.adnGetSring(HelpConst.DNAHUMANO_1),HelpConst.HUMANO))
				.collect(Collectors.toList())
			);
		assertNotEquals(2, service.findByTipo(HelpConst.MUTANTE).size());
	}
	
	@Test
	public void getHuman_Bad() throws BDException {
		when(repo.findByTipo(HelpConst.HUMANO)).thenReturn(
				Stream.of(new Individuo(Convert.adnGetSring(HelpConst.DNAMUTANTE_1),HelpConst.MUTANTE))
				.collect(Collectors.toList())
			);
		assertNotEquals(2, service.findByTipo(HelpConst.HUMANO).size());
	}
}
