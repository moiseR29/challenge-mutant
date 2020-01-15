package com.challenge.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.excepciones.ADNNullException;
import com.challenge.excepciones.BDException;
import com.challenge.excepciones.FormatoInvalidoException;
import com.challenge.help.DNA;
import com.challenge.services.IMutanteService;

/**
 * Controlador Mutante
 * Post -> "/mutant"
 * @author Moise Rebatta 
 *
 */
@RestController
public class MutanteRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(MutanteRestController.class);
	
	@Autowired
	IMutanteService mutanteService;
	
	@PostMapping("/mutant")
	public ResponseEntity<?> response(@Valid @RequestBody DNA dna) {
		
		Map<String,Object> response = new HashMap<>();
		
		boolean isMutant;
		
		try {
			isMutant = mutanteService.isMutant(dna.getDna());
			
			if(isMutant) {
				response.put("mensaje", "El ADN le pertenece a un Mutante");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
			}else {
				response.put("mensaje", "El ADN le pertenece a un Humano");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.FORBIDDEN);
			}
			
		} catch (ADNNullException nullException) {
			logger.error(nullException.getMessage());
			response.put("error", nullException.getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		} catch (FormatoInvalidoException formatException) {
			logger.error(formatException.getMessage());
			response.put("error", formatException.getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		} catch (BDException bd) {
			logger.error(bd.getMessage());
			response.put("error", bd.getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.put("error", "Error interno");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

/**
 * Controlador de Excepciones, al mandar un parametro que no sea un String[]
 * @author Moise Rebatta
 *
 */
@ControllerAdvice
class CustomControllerAdvice {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "NO SE CUMPLE EL FORMATO DE PETICION")
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public @ResponseBody void handleException() {
    }
}
