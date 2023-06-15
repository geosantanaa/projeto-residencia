package com.projeto.service;

import java.time.LocalDate;
import java.util.Collections;
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
import com.projeto.model.Cliente;
import com.projeto.model.Endereco;
import com.projeto.model.Telefone;
import com.projeto.model.dto.ClienteAlterarDto;
import com.projeto.model.dto.ClienteEntradaDto;
import com.projeto.model.dto.ClienteSaidaDto;
import com.projeto.repository.ClienteRepository;
import com.projeto.repository.EnderecoRepository;
import com.projeto.repository.TelefoneRepository;
import com.projeto.validator.ClienteValidator;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ClienteService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private TelefoneRepository telefoneRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ClienteValidator validator;

	public ClienteSaidaDto criarCliente(ClienteEntradaDto entrada) {
		Cliente entity = mapper.map(entrada, Cliente.class);
		if (entrada.getDataDeCadastro() == null) {
			entity.setDataDeCadastro(LocalDate.now());
		}
		validator.validarCpf(entrada);
		log.info("Processando uma requisição: metodo = criar, entity = {}", entity);
		
		
		List<Long> idsTelefones = entrada.getIdTelefone();
		List<Telefone> telefones = telefoneRepository.findAllById(idsTelefones);

		if (!telefones.isEmpty()) {
		    entity.setTelefone(telefones);
		}
		
		Optional<Endereco> buscandoEndereco = enderecoRepository.findById(entrada.getIdEndereco());
		if (buscandoEndereco.isPresent()) {
			Endereco endereco = buscandoEndereco.get();
			entity.setEndereco(endereco);
		}

		Cliente entityBanco = clienteRepository.save(entity);
		log.info("O banco de dados retornou: entityBanco = {}", entityBanco);

		return mapper.map(entityBanco, ClienteSaidaDto.class);
	}

	public void alterarCliente(Long id, ClienteAlterarDto alterar) {
		try {
			Optional<Cliente> buscandoCliente = clienteRepository.findById(id);
			Cliente entityBanco = buscandoCliente.get();
			mapper.map(alterar, entityBanco);
			
			Optional<Telefone> buscandoTelefone = telefoneRepository.findById(alterar.getIdTelefone());
			if (buscandoTelefone.isPresent()) {
				Telefone telefone = buscandoTelefone.get();
				entityBanco.setTelefone(Collections.singletonList(telefone));
			}
			
			Optional<Endereco> buscandoEndereco = enderecoRepository.findById(alterar.getIdEndereco());
			if (buscandoEndereco.isPresent()) {
				Endereco endereco = buscandoEndereco.get();
				entityBanco.setEndereco(endereco);
			}
			clienteRepository.save(entityBanco);

		}catch (NoSuchElementException e) {
			throw new NegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO);
		}
		
	}
	public ClienteSaidaDto pegarUmCliente(Long id) {
		try {
			Optional<Cliente> buscandoCliente = clienteRepository.findById(id);

			Cliente entityBanco = buscandoCliente.get();
			log.info("O Banco de Dados retornou: entityBanco={}", entityBanco);
			return mapper.map(entityBanco, ClienteSaidaDto.class);
		}catch(NoSuchElementException e){
			throw new NegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO);
		}
		
	}
	public void excluirCliente(Long id) {
		try {
			clienteRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new NegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO);

		}
	}

	public List<ClienteSaidaDto> listarCliente() {
		List<Cliente> listaClientes = clienteRepository.findAll();
		return mapper.map(listaClientes, new TypeToken<List<ClienteSaidaDto>>() {
		}.getType());
	}

}
