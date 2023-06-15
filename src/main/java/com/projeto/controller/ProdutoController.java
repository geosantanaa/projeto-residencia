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

import com.projeto.model.dto.ProdutoEntradaDto;
import com.projeto.model.dto.ProdutoSaidaDto;
import com.projeto.service.ProdutoService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("ecommerce")
@Log4j2
@Validated
public class ProdutoController {

	@Autowired
	ProdutoService service;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("produto")
	public ProdutoSaidaDto criarProduto(@Valid @RequestBody ProdutoEntradaDto entrada) {
		log.info("Recebendo uma requisição: metodo = criar, entradaDto{}", entrada);
		return service.criarProduto(entrada);
	}
	
	@PutMapping("produto/id/{id}")
	public void alterarProduto(@PathVariable("id") Long id, @Valid @RequestBody  ProdutoEntradaDto entrada) {
		log.info("alterar: {} {}", id, entrada);
		service.alterarProduto(id, entrada);
	}

	@GetMapping("produto/id/{id}")
	public ProdutoSaidaDto pegarUmProduto(@PathVariable("id") Long id) {
		log.info("PegarUm: {}", id);
		return service.pegarUm(id);
	}

	@DeleteMapping("produto/id/{id}")
	public void excluirProduto(@PathVariable("id") Long id) {
		log.info("excluir: {}", id);
		service.excluirProduto(id);
	}

	@GetMapping("produto")
	public List<ProdutoSaidaDto> listarProduto() {
		log.info("listar");
		return service.listarProduto();
	}

}
