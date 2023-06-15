package com.projeto.model.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClienteEntradaDto {
	
	@NotBlank(message = "campo obrigatório")
	@Size( max = 100, message = "máximo é de 200 caracters permitido")
	private String nome;
	
	@NotBlank(message = "campo obrigatório")
	@Size( max = 14, message = "cpf inválido")
	private String cpf;
	
	@Email
	@NotBlank(message = "campo obrigatório")
	@Size( max = 100, message = "email inválido")
	private String email;

	@NotBlank(message = "campo obrigatório")
	@Size( max = 10, message = "máximo é de 10 caracters permitido")
	private String senha;
	
	@NotNull(message = "campo obrigatório")
    private List<Long> idTelefone;
	
	@NotNull(message = "campo obrigatório")
	private Long idEndereco;
		
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDeCadastro;

	
	

}
