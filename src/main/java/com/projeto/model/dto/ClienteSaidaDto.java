package com.projeto.model.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projeto.model.Endereco;
import com.projeto.model.Telefone;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteSaidaDto {

	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private String senha;
	private List <Telefone> telefone;
	private Endereco endereco;
	private LocalDate dataDeCadastro;

}
