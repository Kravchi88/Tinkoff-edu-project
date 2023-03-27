package ru.tinkoff.edu.java.bot;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tinkoff.errorResponse.ApiErrorResponse;
import org.tinkoff.exceptions.WrongRequestParameterException;

import java.util.Arrays;

@RestControllerAdvice
public class ErrorsHandler {
    @ExceptionHandler(WrongRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiErrorResponse wrongRequestParameter(WrongRequestParameterException e) {
        return new ApiErrorResponse(
                "Invalid request parameters",
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                e.getClass().getName(),
                e.getMessage(),
                Arrays.stream(e.getStackTrace()).map(String::valueOf).toList()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiErrorResponse httpMessageNotReadable(HttpMessageNotReadableException e) {
        return new ApiErrorResponse(
                "Invalid request parameters",
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                e.getClass().getName(),
                e.getMessage(),
                Arrays.stream(e.getStackTrace()).map(String::valueOf).toList()
        );
    }
}
