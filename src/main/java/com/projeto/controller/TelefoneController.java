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

import com.projeto.model.dto.TelefoneEntradaDto;
import com.projeto.model.dto.TelefoneSaidaDto;
import com.projeto.service.TelefoneService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("ecommerce")
@Log4j2
@Validated
public class TelefoneController {

	@Autowired
	TelefoneService service;
	

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("telefone")
	public TelefoneSaidaDto criarTelefone(@Valid @RequestBody TelefoneEntradaDto entrada) {
		log.info("Recebendo uma requisição: metodo = criar, entradaDto{}", entrada);
		return service.criarTelefone(entrada);
	}
	
	@PutMapping("telefone/id/{id}")
	public void alterarTelefone(@PathVariable("id") Long id, @Valid @RequestBody  TelefoneEntradaDto entrada) {
		log.info("alterar: {} {}", id, entrada);
		service.alterarTelefone(id, entrada);
	}
	
	@GetMapping("telefone/id/{id}")
	public TelefoneSaidaDto pegarUmTelefone(@PathVariable("id") Long id) {
		log.info("PegarUm: {}", id);
		return service.pegarUmEndereco(id);
	}

	@DeleteMapping("telefone/id/{id}")
	public void excluirTelefone(@PathVariable("id") Long id) {
		log.info("excluir: {}", id);
		service.excluirEndereco(id);
	}

	@GetMapping("telefone")
	public List<TelefoneSaidaDto> listarTelefones() {
		log.info("listar");
		return service.listarTelefones();
	}

}
