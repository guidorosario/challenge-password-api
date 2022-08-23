package br.com.password.service.impl;

import br.com.password.model.PasswordResponse;
import br.com.password.service.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static br.com.password.constraints.Messages.ERROR_UPPER_CASE;
import static br.com.password.constraints.ValidatorConstraints.UPPER_CASE;

@Service
public class UpperCaseValidatorImpl implements ValidatorService {

    private static final Logger LOG = LoggerFactory.getLogger(UpperCaseValidatorImpl.class);

    @Override
    public Mono<PasswordResponse> validatorPassword(String password, PasswordResponse passwordResponse) {
        LOG.info("Iniciando validação de letra maiúscula na senha");
        if(!password.matches(UPPER_CASE)) {
            LOG.info("Senha nao possui letra maiúscula");
            passwordResponse.getErrorPassword().add(ERROR_UPPER_CASE);
        }

        return Mono.just(passwordResponse);
    }
}
