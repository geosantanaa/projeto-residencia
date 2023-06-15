package com.projeto.model.dto;


import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutoSaidaDto {
	
	private Long id;
	private String nome;
	private BigDecimal preco;
	private Long quantidade;


}
