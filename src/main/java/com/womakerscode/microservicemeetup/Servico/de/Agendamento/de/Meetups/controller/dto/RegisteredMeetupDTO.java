package com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisteredMeetupDTO {

    private Boolean registered;
}
