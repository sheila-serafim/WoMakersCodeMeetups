package com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.service.impl;

import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.exception.BusinessException;
import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.model.entity.Registration;
import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.repository.RegistrationRepository;
import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.service.RegistrationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private RegistrationRepository repository;

    public RegistrationServiceImpl(RegistrationRepository repository) {

        this.repository = repository;
    }

    @Override
    public Registration save(Registration registration) {

        if(repository.existsByRegistration(registration.getRegistration())){
            throw new BusinessException("Registration already created");
        }

        return repository.save(registration);
    }

    @Override
    public Optional<Registration> getRegistrationById(Integer id){
        return this.repository.findById(id);
    }
}
