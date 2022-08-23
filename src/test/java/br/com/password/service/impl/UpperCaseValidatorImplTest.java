package br.com.password.service.impl;

import br.com.password.model.PasswordResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import static br.com.password.constraints.Messages.ERROR_UPPER_CASE;
import static br.com.password.constraints.Password.VALID_PASSWORD;
import static br.com.password.constraints.Password.NO_UPPER_CASE_PASSWORD;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UpperCaseValidatorImplTest {

    @InjectMocks UpperCaseValidatorImpl upperCaseValidator;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Testando senha sem letra maiuscula")
    void noUpperCasePasswordShouldAddErrorPassword() {
        StepVerifier.create(upperCaseValidator.validatorPassword(NO_UPPER_CASE_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().contains(ERROR_UPPER_CASE)))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Testando senha com letra maiuscula")
    void upperCasePasswordShouldPass() {
        StepVerifier.create(upperCaseValidator.validatorPassword(VALID_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().isEmpty()))
                .expectComplete()
                .verify();
    }






}