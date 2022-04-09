package com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.controller;

import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.controller.exception.APIError;
import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIError handleValidateException(MethodArgumentNotValidException e) {

        BindingResult bindingResult = e.getBindingResult();

        return new APIError(bindingResult);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIError handleBusinessException(BusinessException e) {

        return new APIError(e);
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus
    public APIError handleResponseStatusException(ResponseStatusException e) {

        return new APIError(e);
    }
}
