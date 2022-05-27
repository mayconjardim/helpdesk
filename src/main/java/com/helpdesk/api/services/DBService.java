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
		Tecnico tec2 = new Tecnico(null, "Marcelo Jardim", "88471989", "marcelo@gmail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		Tecnico tec3 = new Tecnico(null, "Douglas Jardim", "84154244", "douglas@gmail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Jose Rovaldo", "93975148", "jose@gmail.com", "123");
		Cliente cli2 = new Cliente(null, "Peixoto Ribeiro", "77458877", "peixoto@gmail.com", "123");
		Cliente cli3 = new Cliente(null, "Paulo Nakada", "33664455", "nakada@gmail.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		Chamado c2 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 02", "Segundo chamado", tec2, cli2);
		Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ANDAMENTO, "Chamado 03", "Terceiro chamado", tec3, cli3);
		
		tecRepository.saveAll(Arrays.asList(tec1));
		cliRepository.saveAll(Arrays.asList(cli1));
		chaRepository.saveAll(Arrays.asList(c1));
		
		tecRepository.saveAll(Arrays.asList(tec2));
		cliRepository.saveAll(Arrays.asList(cli2));
		chaRepository.saveAll(Arrays.asList(c2));
		
		tecRepository.saveAll(Arrays.asList(tec3));
		cliRepository.saveAll(Arrays.asList(cli3));
		chaRepository.saveAll(Arrays.asList(c3));
		
	}

}
