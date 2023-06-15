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

import com.projeto.model.dto.EnderecoEntradaDto;
import com.projeto.model.dto.EnderecoSaidaDto;
import com.projeto.service.EnderecoService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("ecommerce")
@Log4j2
@Validated
public class EnderecoController {

	@Autowired
	EnderecoService service;
	

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("endereco")
	public EnderecoSaidaDto criarPedido(@Valid @RequestBody EnderecoEntradaDto entrada) {
		log.info("Recebendo uma requisição: metodo = criar, entradaDto{}", entrada);
		return service.criarEndereco(entrada);
	}
	
	@PutMapping("endereco/id/{id}")
	public void alterarEndereco(@PathVariable("id") Long id, @Valid @RequestBody  EnderecoEntradaDto entrada) {
		log.info("alterar: {} {}", id, entrada);
		service.alterarEndereco(id, entrada);
	}
	
	@GetMapping("endereco/id/{id}")
	public EnderecoSaidaDto pegarUmEndereco(@PathVariable("id") Long id) {
		log.info("PegarUm: {}", id);
		return service.pegarUmEndereco(id);
	}

	@DeleteMapping("endereco/id/{id}")
	public void excluirEndereco(@PathVariable("id") Long id) {
		log.info("excluir: {}", id);
		service.excluirEndereco(id);
	}

	@GetMapping("endereco")
	public List<EnderecoSaidaDto> listarEndereco() {
		log.info("listar");
		return service.listarEnderecos();
	}

}
