package com.victorsantos.archetype.maven.springboot.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victorsantos.archetype.maven.springboot.application.constant.AuthorizationCode;
import com.victorsantos.archetype.maven.springboot.application.usecase.authorize.AuthorizeUseCase;
import com.victorsantos.archetype.maven.springboot.application.usecase.authorize.AuthorizeUseCaseRequest;
import com.victorsantos.archetype.maven.springboot.application.usecase.authorize.AuthorizeUseCaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
class AuthorizeControllerImpl implements AuthorizeController {

    private final AuthorizeUseCase authorizeUseCase;

    private final ObjectMapper objectMapper;

    @Override
    public AuthorizeUseCaseResponse authorize(HttpServletRequest httpServletRequest) {
        try {
            var request = objectMapper.readValue(httpServletRequest.getInputStream(), AuthorizeUseCaseRequest.class);
            return authorizeUseCase.run(request);
        } catch (IOException e) {
            log.error("Bad request: {}", e.getMessage());
            return new AuthorizeUseCaseResponse(AuthorizationCode.OTHER);
        } catch (Exception e) {
            log.error("Error while authorizing transaction", e);
            return new AuthorizeUseCaseResponse(AuthorizationCode.OTHER);
        }
    }
}
