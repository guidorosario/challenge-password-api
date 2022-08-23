package br.com.password.service.impl;

import br.com.password.model.PasswordResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import reactor.test.StepVerifier;

import static br.com.password.constraints.Messages.ERROR_SPECIAL_CHARACTER;
import static br.com.password.constraints.Password.NO_SPECIAL_CHARACTER_PASSWORD;
import static br.com.password.constraints.Password.VALID_PASSWORD;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpecialCharacterValidatorImplTest {

    @InjectMocks
    SpecialCharacterValidatorImpl specialCharacterValidator;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Testando senha sem caracteres especial")
    void noSpecialPasswordShouldAddErrorPassword() {
        StepVerifier.create(specialCharacterValidator.validatorPassword(NO_SPECIAL_CHARACTER_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().contains(ERROR_SPECIAL_CHARACTER)))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Testando senha com caracteres especial")
    void specialCasePasswordShouldPass() {
        StepVerifier.create(specialCharacterValidator.validatorPassword(VALID_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().isEmpty()))
                .expectComplete()
                .verify();
    }
}
