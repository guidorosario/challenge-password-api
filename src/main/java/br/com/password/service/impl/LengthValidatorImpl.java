package br.com.password.service.impl;

import br.com.password.model.PasswordResponse;
import br.com.password.service.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static br.com.password.constraints.Messages.ERROR_LENGTH;
import static br.com.password.constraints.ValidatorConstraints.LENGTH;

@Service
public class LengthValidatorImpl implements ValidatorService {

    private static final Logger LOG = LoggerFactory.getLogger(LengthValidatorImpl.class);


    @Override
    public Mono<PasswordResponse> validatorPassword(String password, PasswordResponse passwordResponse) {
        LOG.info("Iniciando validação de tamanho da senha");
        if(!password.matches(LENGTH)) {
            LOG.info("Senha não contem 9 caracteres ou mais");
            passwordResponse.getErrorPassword().add(ERROR_LENGTH);
        }

        return Mono.just(passwordResponse);
    }
}
