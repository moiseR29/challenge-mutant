package com.challenge.controllers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.challenge.help.Stats;
import com.challenge.services.StatsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatsControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private StatsServiceImpl service;
	
	@Autowired
	private StatsController statsController;
	
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(statsController)
				.build();
	}
	
	@Test
	public void getStats_1() throws Exception {
		
		Stats stats = new Stats(0, 0);
		
		Mockito.when(service.findStats()).thenReturn(stats);

		ResultActions resultActions = mockMvc.perform(get(HelpConst.URI_STATS));

		MvcResult result = resultActions.andExpect(status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();
		assertEquals(asJsonString(stats), content);
	}
	
	/*@Test
	public void getStats_2() throws Exception {
		
		Stats stats = new Stats(0, 1);
		
		Mockito.when(service.insert(new Individuo(Convert.adnGetSring(HelpConst.DNAMUTANTE_1),HelpConst.MUTANTE)));

		ResultActions resultActions = mockMvc.perform(get(HelpConst.URI_STATS));

		MvcResult result = resultActions.andExpect(status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();
		assertEquals(asJsonString(stats), content);
	}*/
	
	private static String asJsonString(final Object obj) {
		try {
			return (new ObjectMapper()).writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
