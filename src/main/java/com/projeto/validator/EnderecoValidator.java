package com.projeto.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projeto.exception.NegocioException;
import com.projeto.exception.TabelaDeErros;
import com.projeto.model.dto.EnderecoEntradaDto;
import com.projeto.repository.EnderecoRepository;

@Component
public class EnderecoValidator {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	

	public void validarEndereco(EnderecoEntradaDto entrada) {
		if(enderecoRepository.existsByCep(entrada.getCep())) {
			throw new NegocioException(TabelaDeErros.ENDERECO_JA_CADASTRADO);
		}
	}
	

}
