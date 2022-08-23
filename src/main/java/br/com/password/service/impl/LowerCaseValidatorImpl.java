package br.com.password.service.impl;

import br.com.password.model.PasswordResponse;
import br.com.password.service.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static br.com.password.constraints.Messages.ERROR_LOWER_CASE;
import static br.com.password.constraints.ValidatorConstraints.LOWER_CASE;

@Service
public class LowerCaseValidatorImpl implements ValidatorService {
    private static final Logger LOG = LoggerFactory.getLogger(LowerCaseValidatorImpl.class);

    @Override
    public Mono<PasswordResponse> validatorPassword(String password, PasswordResponse passwordResponse) {
        LOG.info("Iniciando validação de letra minuscula na senha");
        if(!password.matches(LOWER_CASE)) {
            LOG.info("Senha nao possui letra minuscula");
            passwordResponse.getErrorPassword().add(ERROR_LOWER_CASE);
        }

        return Mono.just(passwordResponse);
    }
}
