package com.helpdesk.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.api.entities.Tecnico;
import com.helpdesk.api.repositories.TecnicoRepository;
import com.helpdesk.api.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() ->  new ObjectNotFoundException("Tecnico não encontrado: " + id));
		
	}
	
}
