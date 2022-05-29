package com.helpdesk.api.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.helpdesk.api.dtos.ChamadoDTO;
import com.helpdesk.api.entities.Chamado;
import com.helpdesk.api.entities.Cliente;
import com.helpdesk.api.entities.Tecnico;
import com.helpdesk.api.enums.Prioridade;
import com.helpdesk.api.enums.Status;
import com.helpdesk.api.repositories.ChamadoRepository;
import com.helpdesk.api.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;

	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ID: " + id));
	}

	public Page<ChamadoDTO> findAllPaged(Pageable pageable) {
		Page<Chamado> list = repository.findAll(pageable);
		return list.map(x -> new ChamadoDTO(x));

	}

	public Chamado create(@Valid ChamadoDTO chamadoDTO) {
		return repository.save(newChamado(chamadoDTO));
	}

	public Chamado newChamado(ChamadoDTO chamadoDTO) {
		Tecnico tecnico = tecnicoService.findById(chamadoDTO.getTecnico());
		Cliente cliente = clienteService.findById(chamadoDTO.getCliente());

		Chamado chamado = new Chamado();
		if (chamadoDTO.getId() != null) {
			chamado.setId(chamadoDTO.getId());
		}
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
		chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
		chamado.setTitulo(chamadoDTO.getTitulo());
		chamado.setObservacoes(chamadoDTO.getObservacoes());
		return chamado;

	}

}
