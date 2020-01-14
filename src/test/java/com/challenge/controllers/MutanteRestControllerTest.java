package com.challenge.controllers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.challenge.constantes.HelpConst;
import com.challenge.excepciones.ADNNullException;
import com.challenge.excepciones.BDException;
import com.challenge.excepciones.FormatoInvalidoException;
import com.challenge.services.MutanteServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutanteRestControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private MutanteServiceImpl service;
	
	@Autowired
	private MutanteRestController mutanteController;
	
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(mutanteController)
				.build();
	}
	
	@Test
	public void isMutantOk() throws Exception {
		System.out.println("----------------------------");
		mockIsMutantService(HelpConst.DNAMUTANTE_1, true);
		
		ResultActions resultActions = mockMvc.perform(post(HelpConst.URI_MUTANT)
									   		.contentType(HelpConst.CONTENT_TYPE_JSON)
									   		.content(HelpConst.MOCKMUTANTE));
		
		MvcResult result = resultActions.andExpect(status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		assertEquals("{\"mensaje\":\"El ADN le pertenece a un Mutante\"}", content);
	}
	
	@Test
	public void isMutantNotOk() throws Exception {
		
		mockIsMutantService(HelpConst.DNAHUMANO_1, false);
		
		ResultActions resultActions = mockMvc.perform(post(HelpConst.URI_MUTANT)
									   		.contentType(HelpConst.CONTENT_TYPE_JSON)
									   		.content(HelpConst.MOCKHUMANO));
		
		MvcResult result = resultActions.andExpect(status().isForbidden()).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		assertEquals("{\"mensaje\":\"El ADN le pertenece a un Humano\"}", content);
	}
	
	@Test
	public void isMutantFormatException_1() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(post(HelpConst.URI_MUTANT)
									   		.contentType(HelpConst.CONTENT_TYPE_JSON)
									   		.content(HelpConst.MOCKINVALID_1));
		
		MvcResult result = resultActions.andExpect(status().isBadRequest()).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		assertEquals("{\"error\":\"las filas y columnas deben ser iguales (NxN)\"}", content);
	}
	
	@Test
	public void isMutantFormatException_2() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(post(HelpConst.URI_MUTANT)
									   		.contentType(HelpConst.CONTENT_TYPE_JSON)
									   		.content(HelpConst.MOCKINVALID_2));
		
		MvcResult result = resultActions.andExpect(status().isBadRequest()).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		assertEquals("{\"error\":\"las filas y columnas deben ser iguales (NxN)\"}", content);
	}
	
	@Test
	public void isMutantNull() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(post(HelpConst.URI_MUTANT)
									   		.contentType(HelpConst.CONTENT_TYPE_JSON)
									   		.content(HelpConst.MOCKINVALID_3));
		
		MvcResult result = resultActions.andExpect(status().isBadRequest()).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		assertEquals("{\"error\":\"El ADN no puede ser nulo\"}", content);
	}
	
	private void mockIsMutantService(String[] dna, boolean expectedResult) throws ADNNullException, FormatoInvalidoException, BDException{
		Mockito.when(service.isMutant(dna)).thenReturn(expectedResult);
	}
}