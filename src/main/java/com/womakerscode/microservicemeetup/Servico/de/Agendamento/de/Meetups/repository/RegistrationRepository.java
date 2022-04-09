package com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.repository;

import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.model.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

    Boolean existsByRegistration(String registration);

    Optional<Registration> findByRegistration(String registrationAttribute);
}
