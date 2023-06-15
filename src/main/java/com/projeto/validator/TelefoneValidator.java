package com.projeto.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projeto.exception.NegocioException;
import com.projeto.exception.TabelaDeErros;
import com.projeto.model.dto.TelefoneEntradaDto;
import com.projeto.repository.TelefoneRepository;

@Component
public class TelefoneValidator {
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	

	public void validarNumero(TelefoneEntradaDto entrada) {
		if(telefoneRepository.existsByNumero(entrada.getNumero())) {
			throw new NegocioException(TabelaDeErros.TELEFONE_JA_CADASTRADO);
		}
	}
	

}
