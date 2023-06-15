package com.projeto.model;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "produto")
public class Produto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false, length = 200)
	private String nome;
	
	@Column(nullable = false, name = "preco")
	private BigDecimal preco;
	
	@Column(nullable = false, name = "quantidade_produto")
	private Long quantidade;
	
	



}
