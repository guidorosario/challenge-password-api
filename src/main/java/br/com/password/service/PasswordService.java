package br.com.password.service;

import br.com.password.model.PasswordResponse;
import br.com.password.model.request.PasswordRequest;
import reactor.core.publisher.Mono;

public interface PasswordService {

    Mono<PasswordResponse> validatorPassword(PasswordRequest request);
}
