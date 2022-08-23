package br.com.password.controller;

import br.com.password.model.PasswordResponse;
import br.com.password.model.request.PasswordRequest;
import br.com.password.service.PasswordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/password")
public class PasswordController {

    private final PasswordService passwordService;

    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping
    @RequestMapping("/v1/validate")
    public Mono<PasswordResponse> validatePassword(@RequestBody PasswordRequest passwordRequest) {
        return passwordService.validatorPassword(passwordRequest);
    }



}
