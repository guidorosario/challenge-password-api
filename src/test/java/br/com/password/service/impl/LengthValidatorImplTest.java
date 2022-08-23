package br.com.password.service.impl;

import br.com.password.model.PasswordResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import reactor.test.StepVerifier;

import static br.com.password.constraints.Messages.ERROR_LENGTH;
import static br.com.password.constraints.Password.ONE_CHARACTER_PASSWORD;
import static br.com.password.constraints.Password.VALID_PASSWORD;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LengthValidatorImplTest {

    @InjectMocks
    LengthValidatorImpl lengthValidator;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testando senha > 9 caracteres")
    void lengthPasswordShouldAddErrorPassword() {
        StepVerifier.create(lengthValidator.validatorPassword(ONE_CHARACTER_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().contains(ERROR_LENGTH)))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Testando senha =< 9 caracteres")
    void lengthPasswordShouldPass() {
        StepVerifier.create(lengthValidator.validatorPassword(VALID_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().isEmpty()))
                .expectComplete()
                .verify();
    }
}
