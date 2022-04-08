package com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.service;

import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.model.entity.Registration;

import java.util.Optional;

public interface RegistrationService {

    Registration save(Registration any);

    Optional<Registration> getRegistrationById(Integer id);
}
