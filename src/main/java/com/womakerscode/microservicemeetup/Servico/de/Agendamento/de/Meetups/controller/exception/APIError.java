package com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.controller.exception;

import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.exception.BusinessException;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APIError {

    private final List<String> errors;

    public APIError(BindingResult bindingResult) {

        this.errors = new ArrayList<>();
        bindingResult.getAllErrors().forEach(error -> this.errors.add(error.getDefaultMessage()));
    }

    public APIError(BusinessException e) {

        this.errors = Arrays.asList(e.getMessage());
    }

    public APIError(ResponseStatusException e) {

        this.errors = Arrays.asList(e.getReason());
    }

    public List<String> getErrors() {

        return errors;
    }
}
