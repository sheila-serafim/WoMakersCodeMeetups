package com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.service;

import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.model.entity.Registration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class RegistrationServiceTest {

    @BeforeEach
    public void setUp(){
        //Dependencia do service e dar um new na mesma
    }

    @Test
    @DisplayName("Should save registration")
    public void saveStudent(){

        //cenario
        Registration registration = createValidRegistration();

        //execucao
        Mockito.when(repository.existsByRegistration(Mockito.anyString())).thenReturn(false);
        Mockito.when(repository.save(registration)).thenReturn(createValidRegistration());

        Registration savedRegistration = registrationService.save(registration);

        //assert
        assertThat(savedRegistration.getId()).isEqualTo(101);
        assertThat(savedRegistration.getName()).isEqualTo("Sheila Neves");
        assertThat(savedRegistration.getDateOfRegistration()).isEqualTo(LocalDate.now());
        assertThat(savedRegistration.getRegistration()).isEqualTo("001");

    }

    private Registration createValidRegistration() {
        return Registration.builder()
                .id(101)
                .name("Sheila Neves")
                .dateOfRegistration(LocalDate.now())
                .registration("001")
                .build();
    }
}