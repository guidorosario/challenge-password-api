package br.com.password.controller;

import br.com.password.exception.PasswordValidatorException;
import br.com.password.model.request.PasswordRequest;
import br.com.password.service.impl.DigitValidatorImpl;
import br.com.password.service.impl.LengthValidatorImpl;
import br.com.password.service.impl.LowerCaseValidatorImpl;
import br.com.password.service.impl.PasswordServiceImpl;
import br.com.password.service.impl.RepetitionCharacterValidatorImpl;
import br.com.password.service.impl.SpaceBetweenValidatorImpl;
import br.com.password.service.impl.SpecialCharacterValidatorImpl;
import br.com.password.service.impl.UpperCaseValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static br.com.password.constraints.Password.EMPTY_PASSWORD;
import static br.com.password.constraints.Password.NO_DIGIT_PASSWORD;
import static br.com.password.constraints.Password.NO_LOWER_CASE_PASSWORD;
import static br.com.password.constraints.Password.NO_SPECIAL_CHARACTER_PASSWORD;
import static br.com.password.constraints.Password.NO_UPPER_CASE_PASSWORD;
import static br.com.password.constraints.Password.ONE_CHARACTER_PASSWORD;
import static br.com.password.constraints.Password.REPETITION_CHARACTER_PASSWORD;
import static br.com.password.constraints.Password.SPACE_BETWEEN_PASSWORD;
import static br.com.password.constraints.Password.VALID_PASSWORD;
import static br.com.password.mock.MockPasswordResponse.errorsBetweenSpace;
import static br.com.password.mock.MockPasswordResponse.errorsLengthDigitLowerCaseSpecialCharacter;
import static br.com.password.mock.MockPasswordResponse.errorsLengthSpaceBetweenDigitLowerAndUpperCaseSpecialCharacter;
import static br.com.password.mock.MockPasswordResponse.errorsLowerCase;
import static br.com.password.mock.MockPasswordResponse.errorsNoDigit;
import static br.com.password.mock.MockPasswordResponse.errorsNoSpecialCharacter;
import static br.com.password.mock.MockPasswordResponse.errorsRepetitionCharacter;
import static br.com.password.mock.MockPasswordResponse.errorsUpperCase;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = PasswordController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
@Import({PasswordServiceImpl.class, LengthValidatorImpl.class,
        SpaceBetweenValidatorImpl.class, DigitValidatorImpl.class,
        LowerCaseValidatorImpl.class, UpperCaseValidatorImpl.class,
        SpecialCharacterValidatorImpl.class, RepetitionCharacterValidatorImpl.class})
class PasswordControllerIntegrationTest {

    @Autowired
    WebTestClient webTestClient;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testando senha valida retornando true")
    void validPasswordShouldPass() {

        webTestClient
                .post()
                .uri("/password/v1/validate")
                .body(BodyInserters.fromValue(new PasswordRequest(VALID_PASSWORD)))
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.checkedPassword").isEqualTo(true);

    }

    @Test
    @DisplayName("Testando senha vazia retornando exception")
    void emptyPasswordShouldError() {

        var exception = new PasswordValidatorException(errorsLengthSpaceBetweenDigitLowerAndUpperCaseSpecialCharacter().getErrorPassword().toString());

        webTestClient
                .post()
                .uri("/password/v1/validate")
                .body(BodyInserters.fromValue(new PasswordRequest(EMPTY_PASSWORD)))
                .exchange()
                .expectStatus()
                .is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo(exception.getError())
                .jsonPath("error_description").isEqualTo(exception.getMessage());

    }

    @Test
    @DisplayName("Testando senha com um caractere retornando exception")
    void oneCharacterPasswordShouldError() {

        var exception = new PasswordValidatorException(errorsLengthDigitLowerCaseSpecialCharacter().getErrorPassword().toString());

        webTestClient
                .post()
                .uri("/password/v1/validate")
                .body(BodyInserters.fromValue(new PasswordRequest(ONE_CHARACTER_PASSWORD)))
                .exchange()
                .expectStatus()
                .is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo(exception.getError())
                .jsonPath("error_description").isEqualTo(exception.getMessage());

    }

    @Test
    @DisplayName("Testando senha com espaco retornando exception")
    void spaceBetweenPasswordShouldError() {

        var exception = new PasswordValidatorException(errorsBetweenSpace().getErrorPassword().toString());

        webTestClient
                .post()
                .uri("/password/v1/validate")
                .body(BodyInserters.fromValue(new PasswordRequest(SPACE_BETWEEN_PASSWORD)))
                .exchange()
                .expectStatus()
                .is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo(exception.getError())
                .jsonPath("error_description").isEqualTo(exception.getMessage());

    }

    @Test
    @DisplayName("Testando senha sem letra maiuscula retornando exception")
    void noUpperCasePasswordShouldError() {

        var exception = new PasswordValidatorException(errorsUpperCase().getErrorPassword().toString());

        webTestClient
                .post()
                .uri("/password/v1/validate")
                .body(BodyInserters.fromValue(new PasswordRequest(NO_UPPER_CASE_PASSWORD)))
                .exchange()
                .expectStatus()
                .is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo(exception.getError())
                .jsonPath("error_description").isEqualTo(exception.getMessage());

    }

    @Test
    @DisplayName("Testando senha sem letra minuscula retornando exception")
    void noLowerCasePasswordShouldError() {

        var exception = new PasswordValidatorException(errorsLowerCase().getErrorPassword().toString());

        webTestClient
                .post()
                .uri("/password/v1/validate")
                .body(BodyInserters.fromValue(new PasswordRequest(NO_LOWER_CASE_PASSWORD)))
                .exchange()
                .expectStatus()
                .is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo(exception.getError())
                .jsonPath("error_description").isEqualTo(exception.getMessage());

    }
    @Test
    @DisplayName("Testando senha sem digito retornando exception")
    void noDigitPasswordShouldError() {

        var exception = new PasswordValidatorException(errorsNoDigit().getErrorPassword().toString());

        webTestClient
                .post()
                .uri("/password/v1/validate")
                .body(BodyInserters.fromValue(new PasswordRequest(NO_DIGIT_PASSWORD)))
                .exchange()
                .expectStatus()
                .is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo(exception.getError())
                .jsonPath("error_description").isEqualTo(exception.getMessage());

    }

    @Test
    @DisplayName("Testando senha sem caracteres especiais retornando exception")
    void noSpecialCharacterPasswordShouldError() {

        var exception = new PasswordValidatorException(errorsNoSpecialCharacter().getErrorPassword().toString());

        webTestClient
                .post()
                .uri("/password/v1/validate")
                .body(BodyInserters.fromValue(new PasswordRequest(NO_SPECIAL_CHARACTER_PASSWORD)))
                .exchange()
                .expectStatus()
                .is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo(exception.getError())
                .jsonPath("error_description").isEqualTo(exception.getMessage());

    }

    @Test
    @DisplayName("Testando senha com caracteres repetidos retornando exception")
    void repetitionCharacterPasswordShouldError() {

        var exception = new PasswordValidatorException(errorsRepetitionCharacter().getErrorPassword().toString());

        webTestClient
                .post()
                .uri("/password/v1/validate")
                .body(BodyInserters.fromValue(new PasswordRequest(REPETITION_CHARACTER_PASSWORD)))
                .exchange()
                .expectStatus()
                .is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo(exception.getError())
                .jsonPath("error_description").isEqualTo(exception.getMessage());

    }
}