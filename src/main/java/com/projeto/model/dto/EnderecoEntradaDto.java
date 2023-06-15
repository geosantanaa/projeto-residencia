package com.projeto.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EnderecoEntradaDto {

	@ApiModelProperty(example = "11111-111", value = "Cep do Cliente", required = true)
	@NotEmpty(message = "campo obrigatório")
	@Size( max=10, message="Cep inválido")
	private String cep;
	
	@ApiModelProperty(example = "Av Ferreira", value = "Logradouro do Cliente", required = true)
	@NotEmpty(message = "campo obrigatório")
	@Size( max=100, message="máximo permitido é 100 caracters")
	private String logradouro;
	
	@ApiModelProperty(example = "111", value = "Número do Endereço do Cliente", required = true)
	@NotEmpty(message = "campo obrigatório")
	@Size( max=10, message="máximo permitido é 10 caracters")
	private String numero;
	
	@ApiModelProperty(example = "Águas Brancas", value = "Bairro do Cliente", required = true)
	@NotEmpty(message = "campo obrigatório")
	@Size( max=100, message="máximo permitido é 100 caracters")
	private String bairro;
	
	
	@ApiModelProperty(example = "Próximo ao mercadinho do seu Zé", value = "Complemento do Endereço do cliente", required = true)
	@NotEmpty(message = "campo obrigatório")
	@Size( max=200, message="máximo permitido é 200 caracters")
	private String complemento;

}
