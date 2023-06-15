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
import com.projeto.model.Produto;
import com.projeto.model.dto.ProdutoEntradaDto;
import com.projeto.model.dto.ProdutoSaidaDto;
import com.projeto.repository.ProdutoRepository;
import com.projeto.validator.ProdutoValidator;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProdutoService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private ProdutoValidator validator;

	public ProdutoSaidaDto criarProduto(ProdutoEntradaDto entrada) {
		validator.validarProduto(entrada);
		Produto entity = mapper.map(entrada, Produto.class);

		log.info("Processando uma requisição: metodo = criar, entity = {}", entity);

		Produto entityBanco = repository.save(entity);

		log.info("O banco de dados retornou: entityBanco = {}", entityBanco);
		System.out.print(entity);
		return mapper.map(entityBanco, ProdutoSaidaDto.class);
	}

	public void alterarProduto(Long id, ProdutoEntradaDto entrada) {
		try {
			validator.validarProduto(entrada);
			Optional<Produto> buscandoProduto = repository.findById(id);
			validator.validarProduto(entrada);
			validator.validarProduto(entrada);

			Produto entityBanco = buscandoProduto.get();
			mapper.map(entrada, entityBanco);

			repository.save(entityBanco);
		}catch(NoSuchElementException e) {
			throw new NegocioException(TabelaDeErros.PRODUTO_NAO_ENCONTRADO);
		}
	}

	public ProdutoSaidaDto pegarUm(Long id) {
		try {
			Optional<Produto> buscandoProduto = repository.findById(id);
			Produto entityBanco = buscandoProduto.get();
			log.info("O Banco de Dados retornou: entityBanco={}", entityBanco);
			return mapper.map(entityBanco, ProdutoSaidaDto.class);
		}catch(NoSuchElementException e) {
			throw new NegocioException(TabelaDeErros.PRODUTO_NAO_ENCONTRADO);
		}
		
	}

	public void excluirProduto(Long id) {
		try {
			repository.deleteById(id);

		}catch(EmptyResultDataAccessException e) {
			throw new NegocioException(TabelaDeErros.PRODUTO_NAO_ENCONTRADO);
		}
	}

	public List<ProdutoSaidaDto> listarProduto() {
		List<Produto> listaProdutos = repository.findAll();
		return mapper.map(listaProdutos, new TypeToken<List<ProdutoSaidaDto>>() {
		}.getType());
	}

}
