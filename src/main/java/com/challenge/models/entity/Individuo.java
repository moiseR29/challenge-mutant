package com.challenge.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * Clase POJO la cual contiene un constructor con parametro para facilitar los Test
 * @author Moises Rebatta
 *
 */
@Entity
@Table(name = "individuo")
public class Individuo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Individuo() {}
	
	public Individuo(String adn,String tipo) {
		this.adn = adn;
		this.tipo = tipo;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "el adn no puede estar vacio")
	@Column(nullable = false, unique = true)
	private String adn;
	
	@NotEmpty(message = "el tipo no puede estar vacio")
    @Column(name="tipo", nullable = false)
    private String tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdn() {
		return adn;
	}

	public void setAdn(String adn) {
		this.adn = adn;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}