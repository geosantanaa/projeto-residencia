package com.projeto.service;

import java.math.BigDecimal;
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
import com.projeto.model.Pedido;
import com.projeto.model.Produto;
import com.projeto.model.dto.PedidoEntradaDto;
import com.projeto.model.dto.PedidoSaidaDto;
import com.projeto.repository.ClienteRepository;
import com.projeto.repository.PedidoRepository;
import com.projeto.repository.ProdutoRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PedidoService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public PedidoSaidaDto criarPedido(PedidoEntradaDto entrada) {

		Pedido entity = mapper.map(entrada, Pedido.class);
		log.info("Processando uma requisição: metodo = criar, entity = {}", entity);

		List<Long> idsProdutos = entrada.getIdProduto();
		List<Produto> produtos = produtoRepository.findAllById(idsProdutos);

		if (!produtos.isEmpty()) {
			entity.setProduto(produtos);
		}

		Optional<Cliente> buscandoCliente = clienteRepository.findById(entrada.getIdCliente());
		if (buscandoCliente.isPresent()) {
			Cliente cliente = buscandoCliente.get();
			entity.setCliente(cliente);
		}
		
		// Calcula o valor total da compra
		BigDecimal valorTotal = BigDecimal.ZERO;
		for (Produto produto : produtos) {
		    Long quantidade = produto.getQuantidade(); // Obtém a quantidade do produto

		    BigDecimal precoProduto = produto.getPreco();
		    BigDecimal valorProduto = precoProduto.multiply(BigDecimal.valueOf(quantidade));
		    valorTotal = valorTotal.add(valorProduto);
		}

		// Define o valor total da compra na entidade
		entity.setTotalCompra(valorTotal);
		
		Pedido entityBanco = pedidoRepository.save(entity);
		log.info("O banco de dados retornou: entityBanco = {}", entityBanco);

		return mapper.map(entityBanco, PedidoSaidaDto.class);
	}

	public void alterarPedido(Long id, PedidoEntradaDto entrada) {
		try {
			Optional<Pedido> buscandoPedido = pedidoRepository.findById(id);
			Pedido entityBanco = buscandoPedido.get();
			mapper.map(entrada, entityBanco);
			
			List<Long> idsProdutos = entrada.getIdProduto();
			List<Produto> produtos = produtoRepository.findAllById(idsProdutos);

			if (!produtos.isEmpty()) {
				entityBanco.setProduto(produtos);
			}

			Optional<Cliente> buscandoCliente = clienteRepository.findById(entrada.getIdCliente());
			if (buscandoCliente.isPresent()) {
				Cliente cliente = buscandoCliente.get();
				System.out.println(cliente);
				entityBanco.setCliente(cliente);
			}

			pedidoRepository.save(entityBanco);

		} catch (NoSuchElementException e) {
			throw new NegocioException(TabelaDeErros.PEDIDO_NAO_ENCONTRADO);
		}
	}

	public PedidoSaidaDto pegarUmPedido(Long id) {
		try {
			Optional<Pedido> buscandoProduto = pedidoRepository.findById(id);
			Pedido entityBanco = buscandoProduto.get();
			log.info("O Banco de Dados retornou: entityBanco={}", entityBanco);
			return mapper.map(entityBanco, PedidoSaidaDto.class);
		} catch (NoSuchElementException e) {
			throw new NegocioException(TabelaDeErros.PEDIDO_NAO_ENCONTRADO);
		}

	}

	public void excluirPedido(Long id) {
		try {
			pedidoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioException(TabelaDeErros.PEDIDO_NAO_ENCONTRADO);

		}
	}

	public List<PedidoSaidaDto> listarPedidos() {
		List<Pedido> listaPedidos = pedidoRepository.findAll();
		return mapper.map(listaPedidos, new TypeToken<List<PedidoSaidaDto>>() {
		}.getType());
	}

}
