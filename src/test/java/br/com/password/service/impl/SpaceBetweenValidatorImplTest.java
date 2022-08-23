package br.com.password.service.impl;

import br.com.password.model.PasswordResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import reactor.test.StepVerifier;

import static br.com.password.constraints.Messages.ERROR_SPACE_BETWEEN;
import static br.com.password.constraints.Password.SPACE_BETWEEN_PASSWORD;
import static br.com.password.constraints.Password.VALID_PASSWORD;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpaceBetweenValidatorImplTest {

    @InjectMocks
    SpaceBetweenValidatorImpl spaceBetweenValidator;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testando senha com espaços")
    void spaceBetweenPasswordShouldAddErrorPassword() {
        StepVerifier.create(spaceBetweenValidator.validatorPassword(SPACE_BETWEEN_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().contains(ERROR_SPACE_BETWEEN)))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Testando senha valida sem espaço")
    void noSpaceBetweenPasswordShouldPass() {
        StepVerifier.create(spaceBetweenValidator.validatorPassword(VALID_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().isEmpty()))
                .expectComplete()
                .verify();
    }
}
