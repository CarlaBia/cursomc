package com.carlabeatriz.cursoWeb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlabeatriz.cursoWeb.domain.Cliente;
import com.carlabeatriz.cursoWeb.repositories.ClienteRepository;
import com.carlabeatriz.cursoWeb.services.exeptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow (() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
				}
}
