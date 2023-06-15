package com.projeto.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.model.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

	boolean existsByNumero(String numero);

	


}
