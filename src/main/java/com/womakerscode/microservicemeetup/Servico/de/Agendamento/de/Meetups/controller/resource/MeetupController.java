package com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.controller.resource;

import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.controller.dto.MeetupDTO;
import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.controller.dto.MeetupFilterDTO;
import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.controller.dto.RegistrationDTO;
import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.model.entity.Meetup;
import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.model.entity.Registration;
import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.service.MeetupService;
import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/meetups")
@RequiredArgsConstructor
public class MeetupController {

    private final MeetupService meetupService;
    private final RegistrationService registrationService;
    private final ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Integer create(@RequestBody MeetupDTO meetupDTO) {

        Registration registration = registrationService.getRegistrationByAttribute(meetupDTO.getRegistrationAttribute())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        Meetup entity = Meetup.builder()
                .registration(registration)
                .event(meetupDTO.getEvent())
                .meetupDate("10/10/2021")
                .build();

        entity = meetupService.save(entity);
        return entity.getId();
    }


    @GetMapping
    public Page<MeetupDTO> find(MeetupFilterDTO dto, Pageable pageRequest) {
        Page<Meetup> result = meetupService.find(dto, pageRequest);
        List<MeetupDTO> meetups = result
                .getContent()
                .stream()
                .map(entity -> {

                    Registration registration = entity.getRegistration();
                    RegistrationDTO registrationDTO = modelMapper.map(registration, RegistrationDTO.class);

                    MeetupDTO meetupDTO = modelMapper.map(entity, MeetupDTO.class);
                    meetupDTO.setRegistration(registrationDTO);
                    return meetupDTO;

                }).collect(Collectors.toList());
        return new PageImpl<MeetupDTO>(meetups, pageRequest, result.getTotalElements());
    }
}
