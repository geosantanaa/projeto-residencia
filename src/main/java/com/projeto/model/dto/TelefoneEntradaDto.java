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
public class TelefoneEntradaDto {

	@ApiModelProperty(example = "+11", value = "DDD do Número do Cliente", required = true)
	@NotEmpty(message = "campo obrigatório")
	@Size( max=5, message="ddd inválido")
	private String ddd;

	@ApiModelProperty(example = "11111-1111", value = "Número de Telefone do cliente", required = true)
	@NotEmpty(message = "campo obrigatório")
	@Size( max=20, message = "número inválido")
	private String numero;


}
