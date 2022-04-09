package com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServicoDeAgendamentoDeMeetupsApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServicoDeAgendamentoDeMeetupsApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){

		return new ModelMapper();
	}

}
