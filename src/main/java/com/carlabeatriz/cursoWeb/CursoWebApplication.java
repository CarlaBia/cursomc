package com.carlabeatriz.cursoWeb;

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
import com.carlabeatriz.cursoWeb.domain.Produto;
import com.carlabeatriz.cursoWeb.domain.enums.TipoCliente;
import com.carlabeatriz.cursoWeb.repositories.CategoriaRepository;
import com.carlabeatriz.cursoWeb.repositories.CidadeRepository;
import com.carlabeatriz.cursoWeb.repositories.ClienteRepository;
import com.carlabeatriz.cursoWeb.repositories.EnderecoRepository;
import com.carlabeatriz.cursoWeb.repositories.EstadoRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(CursoWebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria", "maria@hotmail.com", "44445678", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("12345-1234", "321432-765"));
		
		Endereco e1 = new Endereco(null, "Bua boa Viagem", "30", "Térreo", "Bela Vista", "44444440", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua jardins", "3", "Térreo", "Flores", "999899908", cli1, c2);
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}
	

}
