package com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	boolean existsByCep(String cep);


}
