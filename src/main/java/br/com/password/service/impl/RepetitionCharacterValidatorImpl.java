package br.com.password.service.impl;

import br.com.password.model.PasswordResponse;
import br.com.password.service.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static br.com.password.constraints.Messages.ERROR_REPETITION_CHARACTER;
import static br.com.password.constraints.ValidatorConstraints.REPETITION_CHARACTER;

@Service
public class RepetitionCharacterValidatorImpl implements ValidatorService {

    private static final Logger LOG = LoggerFactory.getLogger(RepetitionCharacterValidatorImpl.class);


    @Override
    public Mono<PasswordResponse> validatorPassword(String password, PasswordResponse passwordResponse) {
        LOG.info("Iniciando validação de caracteres repetidos");
        if(password.matches(REPETITION_CHARACTER)) {
            LOG.info("Senha possui caracteres repetidos");
            passwordResponse.getErrorPassword().add(ERROR_REPETITION_CHARACTER);
        }
        return Mono.just(passwordResponse);
    }
}
