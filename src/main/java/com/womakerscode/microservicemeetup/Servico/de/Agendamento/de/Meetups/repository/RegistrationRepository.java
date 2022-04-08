package com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.repository;

import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.model.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration,Integer> {

    boolean existsByRegistration(String registration);
}
