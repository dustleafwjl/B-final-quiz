package com.thoughtworks.capability.gtb.finalquiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResult handler(MethodArgumentNotValidException exception) {
        String defaultMessage = Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage();
        return ErrorResult.builder().status(400).timestamp(new Date().toString()).message(defaultMessage).details("Bad Request").build();
    }

    @ExceptionHandler(TraineeIsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResult handler(TraineeIsNotFoundException exception) {
        String defaultMessage = exception.getMessage();
        // GTB: - magic number
        return ErrorResult.builder().status(404).timestamp(new Date().toString()).message(defaultMessage).details("Not Found").build();
    }

    @ExceptionHandler(TrainerSizeIsToLessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResult handler(TrainerSizeIsToLessException exception) {
        String defaultMessage = exception.getMessage();
        return ErrorResult.builder().status(400).timestamp(new Date().toString()).message(defaultMessage).details("Bad Request").build();
    }
}
