package com.challenge.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.help.Stats;
//import com.challenge.help.Tipo;
import com.challenge.services.StatsService;

@RestController
@RequestMapping("/stats")
public class StatsController {
	
	private static final Logger logger = LoggerFactory.getLogger(StatsController.class);
	
	@Autowired
	private StatsService statsService;
	
	/*private int humanos;
	private int mutantes;*/
	
	@GetMapping(produces = "application/json; charset=UTF-8")
	public Stats getStats() {
		Stats stats = null;
		
		try {
			/*humanos = statsService.findByTipo(Tipo.HUMANO).size();
			mutantes = statsService.findByAdn(Tipo.MUTANTE).size();
			stats = new Stats(humanos, mutantes);	*/
			stats = statsService.findStats();
			logger.info("Mutantes => {}, Humanos => {}, Stats => {} ",
					stats.getCount_mutant_dna(),stats.getCount_human_dna(),stats.getRatio());
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		return stats;
	}

}
