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
import com.projeto.model.Telefone;
import com.projeto.model.dto.TelefoneEntradaDto;
import com.projeto.model.dto.TelefoneSaidaDto;
import com.projeto.repository.TelefoneRepository;
import com.projeto.validator.TelefoneValidator;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TelefoneService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private TelefoneRepository repository;

	@Autowired
	private TelefoneValidator validator;

	public TelefoneSaidaDto criarTelefone(TelefoneEntradaDto entrada) {
		validator.validarNumero(entrada);
		Telefone telefone = mapper.map(entrada, Telefone.class);
		log.info("Processando uma requisição: metodo = criar, entity = {}", telefone);

		Telefone entityBanco = repository.save(telefone);
		log.info("O banco de dados retornou: entityBanco = {}", entityBanco);

		return mapper.map(entityBanco, TelefoneSaidaDto.class);

	}
	public void alterarTelefone(Long id, TelefoneEntradaDto entrada) {
		try {
			validator.validarNumero(entrada);
			Optional<Telefone> buscandoTelefone = repository.findById(id);
			Telefone entityBanco = buscandoTelefone.get();
			mapper.map(entrada, entityBanco);
			repository.save(entityBanco);

		}catch(NoSuchElementException e) {
			throw new NegocioException(TabelaDeErros.TELEFONE_NAO_CADASTRADO);

		}
		
	}
	public TelefoneSaidaDto pegarUmEndereco(Long id) {
		try {
			Optional<Telefone> buscandoTelefone = repository.findById(id);
			Telefone entityBanco = buscandoTelefone.get();
			log.info("O Banco de Dados retornou: entityBanco={}", entityBanco);
			return mapper.map(entityBanco, TelefoneSaidaDto.class);

		} catch (NoSuchElementException e) {
			throw new NegocioException(TabelaDeErros.TELEFONE_NAO_CADASTRADO);

		}

	}

	public void excluirEndereco(Long id) {
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new NegocioException(TabelaDeErros.TELEFONE_NAO_CADASTRADO);

		}

	}

	public List<TelefoneSaidaDto> listarTelefones() {
		List<Telefone> listaTelefones = repository.findAll();
		return mapper.map(listaTelefones, new TypeToken<List<TelefoneSaidaDto>>() {
		}.getType());
	}

}
