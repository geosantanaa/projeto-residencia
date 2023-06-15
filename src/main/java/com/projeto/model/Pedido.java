package com.projeto.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "pedido")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(nullable = false, name = "id_produto")
	private List<Produto> produto;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = false, name = "id_cliente")
	private Cliente cliente;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "forma_pagamento", nullable = true, length = 30)
	private FormaDePagamento pagamento;
	
	@Column(nullable = false, name = "total_compra")
	private BigDecimal totalCompra;
	
	
	

}
