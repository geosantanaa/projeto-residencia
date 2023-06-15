package com.projeto.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projeto.exception.NegocioException;
import com.projeto.exception.TabelaDeErros;
import com.projeto.model.dto.ProdutoEntradaDto;
import com.projeto.repository.ProdutoRepository;

@Component
public class ProdutoValidator {

	@Autowired
	private ProdutoRepository repository;

	public void validarProduto(ProdutoEntradaDto entrada) {
		if (repository.existsByNome(entrada.getNome())) {
			throw new NegocioException(TabelaDeErros.PRODUTO_JA_CADASTRADO);
		}
	}

}
