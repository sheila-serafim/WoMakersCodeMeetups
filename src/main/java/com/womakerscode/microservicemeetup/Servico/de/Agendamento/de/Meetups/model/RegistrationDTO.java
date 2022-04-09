package com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationDTO {

    private Integer id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String dateOfRegistration;

    @NotEmpty
    private String registration;
}
