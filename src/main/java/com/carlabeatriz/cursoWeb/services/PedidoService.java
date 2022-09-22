package com.carlabeatriz.cursoWeb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlabeatriz.cursoWeb.domain.Pedido;
import com.carlabeatriz.cursoWeb.repositories.PedidoRepository;
import com.carlabeatriz.cursoWeb.services.exeptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow (() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
				}
}
