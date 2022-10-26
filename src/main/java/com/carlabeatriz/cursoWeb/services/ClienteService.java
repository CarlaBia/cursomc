package com.carlabeatriz.cursoWeb.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.carlabeatriz.cursoWeb.domain.Cliente;
import com.carlabeatriz.cursoWeb.dto.ClienteDTO;
import com.carlabeatriz.cursoWeb.repositories.ClienteRepository;
import com.carlabeatriz.cursoWeb.services.exeptions.DataIntegrityException;
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
	public Cliente update(Cliente obj) {
		Cliente newObjt = find(obj.getId());
		updateData(newObjt, obj);
		return repo.save(newObjt);
	}

	public void delete(Integer id) {
		find(id);
		try {
			
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas!");
		}
	}
	public List <Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest); 
	}
	public Cliente fromDTO (ClienteDTO objDto) {
		return new Cliente(objDto.getId(),objDto.getNome(), objDto.getEmail(),null, null);
	}
	private void updateData(Cliente newObjt, Cliente obj) {
		newObjt.setNome(obj.getNome());
		newObjt.setEmail(obj.getEmail());
	}
}
