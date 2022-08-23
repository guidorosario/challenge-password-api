package br.com.password.service.impl;

import br.com.password.model.PasswordResponse;
import br.com.password.service.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static br.com.password.constraints.Messages.ERROR_SPACE_BETWEEN;
import static br.com.password.constraints.ValidatorConstraints.SPACE_BETWEEN;

@Service
public class SpaceBetweenValidatorImpl implements ValidatorService {

    private static final Logger LOG = LoggerFactory.getLogger(SpaceBetweenValidatorImpl.class);

    @Override
    public Mono<PasswordResponse> validatorPassword(String password, PasswordResponse passwordResponse) {
        LOG.info("Iniciando validação de espaço na senha");
        if(password.matches(SPACE_BETWEEN)) {
            LOG.info("Senha possui espaços");
            passwordResponse.getErrorPassword().add(ERROR_SPACE_BETWEEN);
        }

        return Mono.just(passwordResponse);
    }

}
