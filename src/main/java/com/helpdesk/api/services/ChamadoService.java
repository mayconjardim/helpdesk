package com.helpdesk.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.api.entities.Chamado;
import com.helpdesk.api.repositories.ChamadoRepository;
import com.helpdesk.api.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	
	@Autowired
	private ChamadoRepository repository;
	
	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ID: " + id));
	}
	
}
