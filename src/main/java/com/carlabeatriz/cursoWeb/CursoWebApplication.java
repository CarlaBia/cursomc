package com.carlabeatriz.cursoWeb;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.carlabeatriz.cursoWeb.domain.Categoria;
import com.carlabeatriz.cursoWeb.domain.Cidade;
import com.carlabeatriz.cursoWeb.domain.Cliente;
import com.carlabeatriz.cursoWeb.domain.Endereco;
import com.carlabeatriz.cursoWeb.domain.Estado;
import com.carlabeatriz.cursoWeb.domain.ItemPedido;
import com.carlabeatriz.cursoWeb.domain.Pagamento;
import com.carlabeatriz.cursoWeb.domain.PagamentoComBoleto;
import com.carlabeatriz.cursoWeb.domain.PagamentoComCartao;
import com.carlabeatriz.cursoWeb.domain.Pedido;
import com.carlabeatriz.cursoWeb.domain.Produto;
import com.carlabeatriz.cursoWeb.domain.enums.EstadoPagamento;
import com.carlabeatriz.cursoWeb.domain.enums.TipoCliente;
import com.carlabeatriz.cursoWeb.repositories.CategoriaRepository;
import com.carlabeatriz.cursoWeb.repositories.CidadeRepository;
import com.carlabeatriz.cursoWeb.repositories.ClienteRepository;
import com.carlabeatriz.cursoWeb.repositories.EnderecoRepository;
import com.carlabeatriz.cursoWeb.repositories.EstadoRepository;
import com.carlabeatriz.cursoWeb.repositories.ItemPedidoRepository;
import com.carlabeatriz.cursoWeb.repositories.PagamentoRepository;
import com.carlabeatriz.cursoWeb.repositories.PedidoRepository;
import com.carlabeatriz.cursoWeb.repositories.ProtudoRepository;

@SpringBootApplication
public class CursoWebApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProtudoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ItemPedidoRepository itemPedido;

	public static void main(String[] args) {
		SpringApplication.run(CursoWebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Inform??tica");
		Categoria cat2 = new Categoria(null, "Escrit??rio");
		Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat4 = new Categoria(null, "Artigo decora????o");
		Categoria cat5 = new Categoria(null, "Artigo Lazer");
		Categoria cat6 = new Categoria(null, "Cal??ados");
		Categoria cat7 = new Categoria(null, "Vestu??rio");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "S??o Paulo");

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2 ,cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Cidade c1 = new Cidade(null, "Uberl??ndia", est1);
		Cidade c2 = new Cidade(null, "S??o Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria", "maria@hotmail.com", "44445678", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("12345-1234", "321432-765"));

		Endereco e1 = new Endereco(null, "Bua boa Viagem", "30", "T??rreo", "Bela Vista", "44444440", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua jardins", "3", "T??rreo", "Flores", "999899908", cli1, c2);
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("21/09/2022 11:55"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("22/09/2022 12:00"), cli1, e2);

		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("22/09/2022 00:00"), null);
		ped2.setPagamento(pagto2);
		
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 200.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00); 
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedido.saveAll(Arrays.asList(ip1,ip2,ip3));		
	}

}
