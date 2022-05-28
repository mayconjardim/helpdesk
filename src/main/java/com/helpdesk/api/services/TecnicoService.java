package com.helpdesk.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.helpdesk.api.dtos.TecnicoDTO;
import com.helpdesk.api.entities.Tecnico;
import com.helpdesk.api.repositories.TecnicoRepository;
import com.helpdesk.api.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Tecnico não encontrado: " + id));

	}

	public Page<TecnicoDTO> findAllPaged(Pageable pageable) {
		Page<Tecnico> list = repository.findAll(pageable);
		return list.map(x -> new TecnicoDTO(x));

	}

	public Tecnico create(TecnicoDTO tecnicoDTO) {
		tecnicoDTO.setId(null);
		Tecnico obj = new Tecnico(tecnicoDTO);
		return repository.save(obj);
	}
}
