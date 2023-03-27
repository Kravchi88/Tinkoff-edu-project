package ru.tinkoff.edu.java.scrapper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tinkoff.errorResponse.ApiErrorResponse;
import org.tinkoff.exceptions.*;

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
    @ExceptionHandler(ChatNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApiErrorResponse chatNotFound(ChatNotFoundException e) {
        return new ApiErrorResponse(
                "The chat does not exist",
                String.valueOf(HttpStatus.NOT_FOUND.value()),
                e.getClass().getName(),
                e.getMessage(),
                Arrays.stream(e.getStackTrace()).map(String::valueOf).toList()
        );
    }
    @ExceptionHandler(LinkNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApiErrorResponse linkNotFound(LinkNotFoundException e) {
        return new ApiErrorResponse(
                "The link does not exist",
                String.valueOf(HttpStatus.NOT_FOUND.value()),
                e.getClass().getName(),
                e.getMessage(),
                Arrays.stream(e.getStackTrace()).map(String::valueOf).toList()
        );
    }
    @ExceptionHandler(ChatAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ApiErrorResponse chatAlreadyExists(ChatAlreadyExistsException e) {
        return new ApiErrorResponse(
                "The chat already exists",
                String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()),
                e.getClass().getName(),
                e.getMessage(),
                Arrays.stream(e.getStackTrace()).map(String::valueOf).toList()
        );
    }
    @ExceptionHandler(LinkAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ApiErrorResponse linkAlreadyExists(LinkAlreadyExistsException e) {
        return new ApiErrorResponse(
                "The link already exists",
                String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()),
                e.getClass().getName(),
                e.getMessage(),
                Arrays.stream(e.getStackTrace()).map(String::valueOf).toList()
        );
    }
}
