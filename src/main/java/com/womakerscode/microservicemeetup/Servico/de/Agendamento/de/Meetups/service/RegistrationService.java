package com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.service;

import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.model.entity.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface RegistrationService {

    Registration save(Registration any);

    Optional<Registration> getRegistrationById(Integer id);

    void delete(Registration registration);

    Registration update(Registration updatingRegistration);

    Page<Registration> find(Registration filter, PageRequest pageRequest);

    Optional<Registration> getRegistrationByAttribute(String registrationAttribute);
}
