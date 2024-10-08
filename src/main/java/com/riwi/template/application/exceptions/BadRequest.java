package com.riwi.template.application.exceptions;

import com.riwi.template.application.dtos.exceptions.ErrorSimple;
import com.riwi.template.application.dtos.exceptions.ErrorsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequest {

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ErrorSimple badRequest(Exception exception){

        List<String> errors = new ArrayList<>();

        if (exception instanceof MethodArgumentNotValidException e){
            e.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        } else {
            errors.add(exception.getMessage());
        }

        return ErrorsResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(errors)
                .build();
    }
}
