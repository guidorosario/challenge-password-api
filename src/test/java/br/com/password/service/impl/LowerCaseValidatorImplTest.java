package br.com.password.service.impl;

import br.com.password.model.PasswordResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import reactor.test.StepVerifier;

import static br.com.password.constraints.Messages.ERROR_LOWER_CASE;
import static br.com.password.constraints.Password.NO_LOWER_CASE_PASSWORD;
import static br.com.password.constraints.Password.VALID_PASSWORD;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LowerCaseValidatorImplTest {

    @InjectMocks
    LowerCaseValidatorImpl lowerCaseValidator;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Testando senha sem letra minuscula")
    void noLowerCasePasswordShouldAddErrorPassword() {
        StepVerifier.create(lowerCaseValidator.validatorPassword(NO_LOWER_CASE_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().contains(ERROR_LOWER_CASE)))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Testando senha com letra minuscula")
    void lowerCasePasswordShouldPass() {
        StepVerifier.create(lowerCaseValidator.validatorPassword(VALID_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().isEmpty()))
                .expectComplete()
                .verify();
    }
}
