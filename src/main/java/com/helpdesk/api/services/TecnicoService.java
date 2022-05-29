package com.helpdesk.api.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.helpdesk.api.dtos.TecnicoDTO;
import com.helpdesk.api.entities.Pessoa;
import com.helpdesk.api.entities.Tecnico;
import com.helpdesk.api.repositories.PessoaRepository;
import com.helpdesk.api.repositories.TecnicoRepository;
import com.helpdesk.api.services.exceptions.DataIntegrityViolationException;
import com.helpdesk.api.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

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
		tecnicoDTO.setSenha(encoder.encode(tecnicoDTO.getSenha()));
		validCpfEmail(tecnicoDTO);
		Tecnico obj = new Tecnico(tecnicoDTO);
		return repository.save(obj);
	}

	public Tecnico update(@Valid Integer id, TecnicoDTO tecnicoDTO) {
		tecnicoDTO.setId(id);
		Tecnico obj = findById(id);
		validCpfEmail(tecnicoDTO);
		obj = new Tecnico(tecnicoDTO);
		return repository.save(obj);
		
	}

	public void delete(Integer id) {
		Tecnico obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Tecnico possue ordem de serviçõs");
		} 
			repository.deleteById(id);
		
	}
	
	private void validCpfEmail(TecnicoDTO tecnicoDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		obj = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
		}
	}
}
