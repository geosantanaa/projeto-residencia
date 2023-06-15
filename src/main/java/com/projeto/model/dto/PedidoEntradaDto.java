package com.projeto.model.dto;


import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PedidoEntradaDto {
	
	@NotNull(message = "informe o id do produto")
    private List<Long> idProduto;
	
	@NotNull(message = "informe o id do cliente")
	private Long idCliente;
	
	@NotNull(message = "informe a forma de pagamento")
	@Pattern(regexp = "DINHEIRO|CARTAO_DE_CREDITO|CARTAO_DE_DEBITO|BOLETO|PICPAY|PAYPAL", message = "inválido")
	private String pagamento;
	
	private BigDecimal totalCompra;
	
	
	
	

	
	

	
	
	
	
}
