package com.victorsantos.archetype.maven.springboot.application.usecase.authorize;

public interface AuthorizeUseCase {
    AuthorizeUseCaseResponse run(AuthorizeUseCaseRequest request);
}
