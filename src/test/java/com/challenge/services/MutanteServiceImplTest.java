package com.challenge.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.challenge.constantes.HelpConst;
import com.challenge.excepciones.ADNNullException;
import com.challenge.excepciones.BDException;
import com.challenge.excepciones.FormatoInvalidoException;
import com.challenge.services.StatsServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutanteServiceImplTest {

	@MockBean
	private StatsServiceImpl stats;
	
	@Autowired
	private MutanteServiceImpl mutanteService;
	
	@Test
	public void esMutanteTrue_1() throws ADNNullException, FormatoInvalidoException, BDException {
		assertEquals(true, mutanteService.isMutant(HelpConst.DNAMUTANTE_1));
	}
	
	@Test
	public void esMutanteTrue_2() throws ADNNullException, FormatoInvalidoException, BDException {
		assertEquals(true, mutanteService.isMutant(HelpConst.DNAMUTANTE_2));
	}
	
	@Test
	public void esHumanoTrue_1() throws ADNNullException, FormatoInvalidoException, BDException {
		assertEquals(false, mutanteService.isMutant(HelpConst.DNAHUMANO_1));
	}
	
	@Test
	public void esHumanoTrue_2() throws ADNNullException, FormatoInvalidoException, BDException {
		assertEquals(false, mutanteService.isMutant(HelpConst.DNAHUMANO_2));
	}
}