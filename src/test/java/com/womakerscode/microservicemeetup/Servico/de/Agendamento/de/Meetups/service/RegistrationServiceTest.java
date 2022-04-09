package com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.service;

import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.exception.BusinessException;
import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.model.entity.Registration;
import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.repository.RegistrationRepository;
import com.womakerscode.microservicemeetup.Servico.de.Agendamento.de.Meetups.service.impl.RegistrationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class RegistrationServiceTest {

    RegistrationService registrationService;

    @MockBean
    RegistrationRepository repository;

    @BeforeEach
    public void setUp(){

        this.registrationService = new RegistrationServiceImpl(repository);
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
        assertThat(savedRegistration.getDateOfRegistration()).isEqualTo("01/04/2022");
        assertThat(savedRegistration.getRegistration()).isEqualTo("001");

    }

    @Test
    @DisplayName("Should throw business error when trying to save a new registration duplicated")
    public void shouldNotSaveAsRegistrationDuplicated(){

        Registration registration = createValidRegistration();
        Mockito.when(repository.existsByRegistration(Mockito.any())).thenReturn(true);

        Throwable exception = catchThrowable( () -> registrationService.save(registration));
        assertThat(exception)
                .isInstanceOf(BusinessException.class)
                .hasMessage("Registration already created");

        Mockito.verify(repository, Mockito.never()).save(registration);

    }

    @Test
    @DisplayName("Should get an Registration by Id")
    public void getByRegistrationTest(){

        //cenario
        Integer id = 11;
        Registration registration = createValidRegistration();
        registration.setId(id);
        Mockito.when(repository.findById(registration.getId())).thenReturn(Optional.of(registration));

        //execucao
        Optional<Registration> foundRegistration = registrationService.getRegistrationById(id);

        assertThat(foundRegistration.isPresent()).isTrue();
        assertThat(foundRegistration.get().getId()).isEqualTo(id);
        assertThat(foundRegistration.get().getName()).isEqualTo(registration.getName());
        assertThat(foundRegistration.get().getDateOfRegistration()).isEqualTo(registration.getDateOfRegistration());
        assertThat(foundRegistration.get().getRegistration()).isEqualTo(registration.getRegistration());
    }

    @Test
    @DisplayName("Should return empty when get a registration by id that doesnt exists.")
    public void registrationNotFoundByIdTest(){

        //cenario
        Integer id = 11;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<Registration> registration = registrationService.getRegistrationById(id);

        //assert
        assertThat(registration.isPresent()).isFalse();
    }

    @Test
    @DisplayName("Should delete a student")
    public void deleteRegistrationTest(){

        Registration registration = Registration.builder().id(11).build();

        Assertions.assertDoesNotThrow(() -> registrationService.delete(registration));

        Mockito.verify(repository, Mockito.times(1)).delete(registration);
    }

    @Test
    @DisplayName("Should update a registration.")
    public void updateRegistration(){

        //cenario
        Integer id = 11;
        Registration updatingRegistration = Registration.builder().id(11).build();

        //execucao
        Registration updatedRegistration = createValidRegistration();
        updatedRegistration.setId(id);

        Mockito.when(repository.save(updatingRegistration)).thenReturn(updatedRegistration);
        Registration registration = registrationService.update(updatingRegistration);

        //assercao
        assertThat(registration.getId()).isEqualTo(updatedRegistration.getId());
        assertThat(registration.getName()).isEqualTo(updatedRegistration.getName());
        assertThat(registration.getDateOfRegistration()).isEqualTo(updatedRegistration.getDateOfRegistration());
        assertThat(registration.getRegistration()).isEqualTo(updatedRegistration.getRegistration());
    }

    @Test
    @DisplayName("Should filter registration by properties.")
    public void findRegistrationTest(){

        //cenario
        Registration registration = createValidRegistration();
        PageRequest pageRequest = PageRequest.of(0, 10);

        List<Registration> listRegistration = Arrays.asList(registration);
        Page<Registration> page = new PageImpl<Registration>(Arrays.asList(registration),
                PageRequest.of(0, 10),1);

        //execucao
        Mockito.when(repository.findAll(Mockito.any(Example.class), Mockito.any(PageRequest.class)))
                .thenReturn(page);

        Page<Registration> result = registrationService.find(registration, pageRequest);

        //assercao
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent()).isEqualTo(listRegistration);
        assertThat(result.getPageable().getPageNumber()).isEqualTo(0);
        assertThat(result.getPageable().getPageSize()).isEqualTo(10);
    }

    @Test
    @DisplayName("Should get a registration model by registration attribute.")
    public void getRegistrationByRegistrationAttribute(){

        String registrationAttribute = "1234";

        Mockito.when(repository.findByRegistration(registrationAttribute))
                .thenReturn(Optional.of(Registration.builder().id(11).registration(registrationAttribute).build()));
        Optional<Registration> registration = registrationService.getRegistrationByAttribute(registrationAttribute);

        //assercao
        assertThat(registration.isPresent()).isTrue();
        assertThat(registration.get().getId()).isEqualTo(11);
        assertThat(registration.get().getRegistration()).isEqualTo(registrationAttribute);

        Mockito.verify(repository, Mockito.times(1)).findByRegistration(registrationAttribute);
    }

    private Registration createValidRegistration() {
        return Registration.builder()
                .id(101)
                .name("Sheila Neves")
                .dateOfRegistration("01/04/2022")
                .registration("001")
                .build();
    }
}