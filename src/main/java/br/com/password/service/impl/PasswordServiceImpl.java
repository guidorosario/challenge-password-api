package br.com.password.service.impl;

import br.com.password.constraints.ValidatorEnum;
import br.com.password.exception.PasswordValidatorException;
import br.com.password.model.PasswordResponse;
import br.com.password.model.request.PasswordRequest;
import br.com.password.service.PasswordService;
import br.com.password.service.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static br.com.password.constraints.ValidatorEnum.DIGIT;
import static br.com.password.constraints.ValidatorEnum.LENGTH;
import static br.com.password.constraints.ValidatorEnum.LOWER_CASE;
import static br.com.password.constraints.ValidatorEnum.REPETITION_CHARACTER;
import static br.com.password.constraints.ValidatorEnum.SPACE_BETWEEN;
import static br.com.password.constraints.ValidatorEnum.SPECIAL_CHARACTER;
import static br.com.password.constraints.ValidatorEnum.UPPER_CASE;

@Service
public class PasswordServiceImpl implements PasswordService {

    private static final Logger LOG = LoggerFactory.getLogger(PasswordServiceImpl.class);
    private final BeanFactory beanFactory;

    public PasswordServiceImpl(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Mono<PasswordResponse> validatorPassword(PasswordRequest request) {

        return this.getCurrentService(LENGTH).validatorPassword(request.password(), new PasswordResponse())
                .doOnSuccess(passwordResponse -> LOG.info("Validacao de tamanho finalizada"))
                .flatMap(passwordResponse ->  this.getCurrentService(SPACE_BETWEEN).validatorPassword(request.password(), passwordResponse))
                .doOnSuccess(passwordResponse -> LOG.info("Validacao de espacos finalizada"))
                .flatMap(passwordResponse -> this.getCurrentService(DIGIT).validatorPassword(request.password(), passwordResponse))
                .doOnSuccess(passwordResponse -> LOG.info("Validacao de digito finalizada"))
                .flatMap(passwordResponse -> this.getCurrentService(LOWER_CASE).validatorPassword(request.password(), passwordResponse))
                .doOnSuccess(passwordResponse -> LOG.info("Validacao de letra minuscula finalizada"))
                .flatMap(passwordResponse -> this.getCurrentService(UPPER_CASE).validatorPassword(request.password(), passwordResponse))
                .doOnSuccess(passwordResponse -> LOG.info("Validacao de maiuscula finalizada"))
                .flatMap(passwordResponse -> this.getCurrentService(SPECIAL_CHARACTER).validatorPassword(request.password(), passwordResponse))
                .doOnSuccess(passwordResponse -> LOG.info("Validacao de caractere especial finalizada"))
                .flatMap(passwordResponse -> this.getCurrentService(REPETITION_CHARACTER).validatorPassword(request.password(), passwordResponse))
                .doOnSuccess(passwordResponse -> LOG.info("Validacao de caractere repetido finalizada"))
                .flatMap(this::checkResponse);
    }

    private Mono<PasswordResponse> checkResponse(PasswordResponse passwordResponse) {
        if(!passwordResponse.getErrorPassword().isEmpty()) {
            LOG.info("Senha não cumpriu os critérios de aceite");
            return Mono.error(new PasswordValidatorException(passwordResponse.getErrorPassword().toString()));
        } else {
            passwordResponse.setCheckedPassword(true);
        }
        return Mono.just(passwordResponse);
    }

    public ValidatorService getCurrentService(ValidatorEnum validatorEnum) {
        return getBean(ValidatorEnum.fromEnum(validatorEnum.getDescription()).getValidatorService());
    }

    private ValidatorService getBean(Class<? extends ValidatorService> type) {
        return beanFactory.getBean(type);
    }
}
