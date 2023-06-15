package com.projeto.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projeto.exception.NegocioException;
import com.projeto.exception.TabelaDeErros;
import com.projeto.model.Endereco;
import com.projeto.model.dto.EnderecoEntradaDto;
import com.projeto.model.dto.EnderecoSaidaDto;
import com.projeto.repository.EnderecoRepository;
import com.projeto.validator.EnderecoValidator;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EnderecoService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private EnderecoRepository repository;

	@Autowired
	private EnderecoValidator validator;
	
	public EnderecoSaidaDto criarEndereco(EnderecoEntradaDto entrada) {
		Endereco endereco = mapper.map(entrada, Endereco.class);
		log.info("Processando uma requisição: metodo = criar, entity = {}", endereco);
		validator.validarEndereco(entrada);
		Endereco entityBanco = repository.save(endereco);
		log.info("O banco de dados retornou: entityBanco = {}", entityBanco);

		return mapper.map(entityBanco, EnderecoSaidaDto.class);

	}
	public void alterarEndereco(Long id, EnderecoEntradaDto entrada) {
		try {
			Optional<Endereco> buscandoEndereco = repository.findById(id);
			Endereco entityBanco = buscandoEndereco.get();
			mapper.map(entrada, entityBanco);
			validator.validarEndereco(entrada);
			repository.save(entityBanco);

		}catch(NoSuchElementException e) {
			throw new NegocioException(TabelaDeErros.ENDERECO_NAO_CADASTRADO);

		}
		
	}

	public EnderecoSaidaDto pegarUmEndereco(Long id) {
		try {
			Optional<Endereco> buscandoEndereco = repository.findById(id);
			Endereco entityBanco = buscandoEndereco.get();
			log.info("O Banco de Dados retornou: entityBanco={}", entityBanco);
			return mapper.map(entityBanco, EnderecoSaidaDto.class);
		}catch(NoSuchElementException e) {
			throw new NegocioException(TabelaDeErros.ENDERECO_NAO_CADASTRADO);
		}
	
	}
	
	public void excluirEndereco(Long id) {
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new NegocioException(TabelaDeErros.ENDERECO_NAO_CADASTRADO);

		}

	}

	public List<EnderecoSaidaDto> listarEnderecos() {
		List<Endereco> listaEndereco = repository.findAll();
		return mapper.map(listaEndereco, new TypeToken<List<EnderecoSaidaDto>>() {
		}.getType());
	}

}
