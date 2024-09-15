package com.riwi.template.application.exceptions;

import com.riwi.template.application.dtos.exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFound {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ErrorResponse userNotFoundException(UsernameNotFoundException exception){

        return ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .message(exception.getMessage())
                .build();
    }
}
