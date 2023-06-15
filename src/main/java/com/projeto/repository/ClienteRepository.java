package com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	boolean existsByCpf(String cpf);

}
