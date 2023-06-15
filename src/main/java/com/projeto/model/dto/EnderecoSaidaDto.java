package com.projeto.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnderecoSaidaDto {
	

	private Long id;
	private String cep;
	private String logradouro;
	private String bairro;
	private String numero;
	private String complemento;
	

}
