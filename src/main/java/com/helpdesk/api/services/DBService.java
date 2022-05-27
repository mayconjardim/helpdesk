package com.helpdesk.api.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.api.entities.Chamado;
import com.helpdesk.api.entities.Cliente;
import com.helpdesk.api.entities.Tecnico;
import com.helpdesk.api.enums.Perfil;
import com.helpdesk.api.enums.Prioridade;
import com.helpdesk.api.enums.Status;
import com.helpdesk.api.repositories.ChamadoRepository;
import com.helpdesk.api.repositories.ClienteRepository;
import com.helpdesk.api.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecRepository;
	
	@Autowired
	private ClienteRepository cliRepository;
	
	@Autowired
	private ChamadoRepository chaRepository;
	
	
	public void instanciaDb() {
		
		Tecnico tec1 = new Tecnico(null, "Maycon Jardim", "12448877742", "maycon@gmail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Jose Rovaldo", "88471989", "jose@gmail.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		tecRepository.saveAll(Arrays.asList(tec1));
		cliRepository.saveAll(Arrays.asList(cli1));
		chaRepository.saveAll(Arrays.asList(c1));
		
	}

}
