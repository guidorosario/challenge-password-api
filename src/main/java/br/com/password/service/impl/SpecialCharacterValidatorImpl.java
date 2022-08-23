package br.com.password.service.impl;

import br.com.password.model.PasswordResponse;
import br.com.password.service.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static br.com.password.constraints.Messages.ERROR_SPECIAL_CHARACTER;
import static br.com.password.constraints.ValidatorConstraints.SPECIAL_CHARACTER;

@Service
public class SpecialCharacterValidatorImpl implements ValidatorService {
    private static final Logger LOG = LoggerFactory.getLogger(SpecialCharacterValidatorImpl.class);

    @Override
    public Mono<PasswordResponse> validatorPassword(String password, PasswordResponse passwordResponse) {
        LOG.info("Iniciando validação de caractere especial na senha");
        if(!password.matches(SPECIAL_CHARACTER)) {
            LOG.info("Senha nao possui caractere especial");
            passwordResponse.getErrorPassword().add(ERROR_SPECIAL_CHARACTER);
        }
        return Mono.just(passwordResponse);
    }
}
