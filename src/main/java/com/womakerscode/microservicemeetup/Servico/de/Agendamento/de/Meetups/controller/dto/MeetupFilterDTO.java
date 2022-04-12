package com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeetupFilterDTO {

    private String registration;

    private  String event;
}
