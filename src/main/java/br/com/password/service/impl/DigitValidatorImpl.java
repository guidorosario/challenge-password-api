package br.com.password.service.impl;

import br.com.password.model.PasswordResponse;
import br.com.password.service.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static br.com.password.constraints.Messages.ERROR_DIGIT;
import static br.com.password.constraints.ValidatorConstraints.DIGIT;

@Service
public class DigitValidatorImpl implements ValidatorService {

    private static final Logger LOG = LoggerFactory.getLogger(DigitValidatorImpl.class);

    @Override
    public Mono<PasswordResponse> validatorPassword(String password, PasswordResponse passwordResponse) {
        LOG.info("Iniciando validação de digito na senha");
        if(!password.matches(DIGIT)) {
            LOG.info("Não possui digito");
            passwordResponse.getErrorPassword().add(ERROR_DIGIT);
        }

        return Mono.just(passwordResponse);
    }
}
