package com.helpdesk.api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.helpdesk.api.entities.Chamado;
import com.helpdesk.api.entities.Cliente;
import com.helpdesk.api.entities.Tecnico;
import com.helpdesk.api.enums.Perfil;
import com.helpdesk.api.enums.Prioridade;
import com.helpdesk.api.enums.Status;
import com.helpdesk.api.repositories.ChamadoRepository;
import com.helpdesk.api.repositories.ClienteRepository;
import com.helpdesk.api.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Autowired
	private TecnicoRepository tecRepository;
	
	@Autowired
	private ClienteRepository cliRepository;
	
	@Autowired
	private ChamadoRepository chaRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		Tecnico tec1 = new Tecnico(null, "Maycon Jardim", "12448877742", "maycon@gmail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Jose Rovaldo", "12488847742", "jose@gmail.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		tecRepository.saveAll(Arrays.asList(tec1));
		cliRepository.saveAll(Arrays.asList(cli1));
		chaRepository.saveAll(Arrays.asList(c1));
		
	}

}
