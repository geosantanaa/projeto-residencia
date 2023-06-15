package com.projeto.model.dto;



import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProdutoEntradaDto {
	
	@NotEmpty(message = "campo obrigatório")
	@Size( max=200, message="ERRO, máximo permitido é 200 caracters")
	private String nome;
	
	@Digits(integer = 10, fraction = 2, message = "inválido")
	private BigDecimal preco;
	
	private Long quantidade;

}
