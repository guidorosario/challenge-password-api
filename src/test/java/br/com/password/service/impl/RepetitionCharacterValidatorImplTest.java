package br.com.password.service.impl;

import br.com.password.model.PasswordResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import reactor.test.StepVerifier;

import static br.com.password.constraints.Messages.ERROR_REPETITION_CHARACTER;
import static br.com.password.constraints.Password.REPETITION_CHARACTER_PASSWORD;
import static br.com.password.constraints.Password.VALID_PASSWORD;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RepetitionCharacterValidatorImplTest {

    @InjectMocks
    RepetitionCharacterValidatorImpl repetitionCharacterValidator;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testando senha com caracteres repetido")
    void repetitionCharacterPasswordShouldAddErrorPassword() {
        StepVerifier.create(repetitionCharacterValidator.validatorPassword(REPETITION_CHARACTER_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().contains(ERROR_REPETITION_CHARACTER)))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Testando senha sem caracteres repetido")
    void noRepetitionCharacterPasswordShouldPass() {
        StepVerifier.create(repetitionCharacterValidator.validatorPassword(VALID_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().isEmpty()))
                .expectComplete()
                .verify();
    }
}
