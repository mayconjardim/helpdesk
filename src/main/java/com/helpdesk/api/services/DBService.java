package com.helpdesk.api.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	public void instanciaDb() {
		
		Tecnico tec1 = new Tecnico(null, "Maycon Jardim", "09599171012", "maycon@gmail.com", encoder.encode("123"));
		tec1.addPerfil(Perfil.ADMIN);
		Tecnico tec2 = new Tecnico(null, "Marcelo Jardim", "98179335046", "marcelo@gmail.com", encoder.encode("123"));
		tec2.addPerfil(Perfil.TECNICO);
		Tecnico tec3 = new Tecnico(null, "Douglas Jardim", "18480377089", "douglas@gmail.com", encoder.encode("123"));
		tec3.addPerfil(Perfil.TECNICO);
		
		Cliente cli1 = new Cliente(null, "Jose Rovaldo", "70685132048", "jose@gmail.com", encoder.encode("123"));
		Cliente cli2 = new Cliente(null, "Peixoto Ribeiro", "59047023021", "peixoto@gmail.com", encoder.encode("123"));
		Cliente cli3 = new Cliente(null, "Paulo Nakada", "49524893029", "nakada@gmail.com", encoder.encode("123"));
		
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
