package com.projeto.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projeto.exception.NegocioException;
import com.projeto.exception.TabelaDeErros;
import com.projeto.model.dto.ClienteEntradaDto;
import com.projeto.repository.ClienteRepository;

@Component
public class ClienteValidator {
	
	@Autowired
	private ClienteRepository clienteRepository;
	

	public void validarCpf(ClienteEntradaDto entrada) {
		if(clienteRepository.existsByCpf(entrada.getCpf())) {
			throw new NegocioException(TabelaDeErros.CPF_JA_CADASTRADO);
		}
	}
	

}
