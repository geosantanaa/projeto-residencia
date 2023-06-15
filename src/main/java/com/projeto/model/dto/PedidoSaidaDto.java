package com.projeto.model.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projeto.model.Cliente;
import com.projeto.model.FormaDePagamento;
import com.projeto.model.Produto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoSaidaDto {

	private Long id;
	private List <Produto> Produto;
	private Cliente Cliente;
	private FormaDePagamento pagamento;
	private BigDecimal totalCompra;
	

	
	

}
