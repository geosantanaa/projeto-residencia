package com.projeto.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.model.dto.PedidoEntradaDto;
import com.projeto.model.dto.PedidoSaidaDto;
import com.projeto.service.PedidoService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("ecommerce")
@Log4j2
@Validated
public class PedidoController {

	@Autowired
	PedidoService service;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("pedido")
	public PedidoSaidaDto criarPedido(@Valid @RequestBody PedidoEntradaDto entrada) {
		log.info("Recebendo uma requisição: metodo = criar, entradaDto{}", entrada);
		return service.criarPedido(entrada);
	}

	@PutMapping("pedido/id/{id}")
	public void alterarPedido(@PathVariable("id") Long id, @Valid @RequestBody PedidoEntradaDto entrada) {
		log.info("alterar: {} {}", id, entrada);
		service.alterarPedido(id, entrada);
	}

	@GetMapping("pedido/id/{id}")
	public PedidoSaidaDto pegarUmPedido(@PathVariable("id") Long id) {
		log.info("PegarUm: {}", id);
		return service.pegarUmPedido(id);
	}

	@DeleteMapping("pedido/id/{id}")
	public void excluirPedido(@PathVariable("id") Long id) {
		log.info("excluir: {}", id);
		service.excluirPedido(id);
	}

	@GetMapping("pedido")
	public List<PedidoSaidaDto> listarPedido() {
		log.info("listar");
		return service.listarPedidos();
	}

}
