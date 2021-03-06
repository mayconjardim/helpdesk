package com.helpdesk.api.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.helpdesk.api.dtos.ClienteDTO;
import com.helpdesk.api.entities.Cliente;
import com.helpdesk.api.entities.Pessoa;
import com.helpdesk.api.repositories.ClienteRepository;
import com.helpdesk.api.repositories.PessoaRepository;
import com.helpdesk.api.services.exceptions.DataIntegrityViolationException;
import com.helpdesk.api.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado: " + id));

	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO clienteDTO) {
		clienteDTO.setId(null);
		clienteDTO.setSenha(encoder.encode(clienteDTO.getSenha()));
		validCpfEmail(clienteDTO);
		Cliente obj = new Cliente(clienteDTO);
		return repository.save(obj);
	}

	public Cliente update(@Valid Integer id, ClienteDTO clienteDTO) {
		clienteDTO.setId(id);
		Cliente obj = findById(id);
		if(!clienteDTO.getSenha().equals(obj.getSenha())) {
			clienteDTO.setSenha(encoder.encode(clienteDTO.getSenha()));
		}
		
		validCpfEmail(clienteDTO);
		obj = new Cliente(clienteDTO);
		return repository.save(obj);
		
	}

	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possue ordem de serviçõs");
		} 
			repository.deleteById(id);
		
	}
	
	private void validCpfEmail(ClienteDTO clienteDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(clienteDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != clienteDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		obj = pessoaRepository.findByEmail(clienteDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != clienteDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
		}
	}
}
