package com.riwi.template.application.exceptions;

import com.riwi.template.application.dtos.exceptions.ErrorResponse;
import com.riwi.template.domain.exceptions.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class InvalidCredentials {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ErrorResponse invalidCredentialsException(InvalidCredentialsException exception){

        return ErrorResponse.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .status(HttpStatus.UNAUTHORIZED.name())
                .message(exception.getMessage())
                .build();
    }
}