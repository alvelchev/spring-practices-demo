package com.springpageable.advice;

import com.springpageable.dto.ErrorDetailsDTO;
import com.springpageable.exception.BadRequestException;
import com.springpageable.exception.ConflictException;
import com.springpageable.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.CONFLICT;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(CONFLICT)
    public ErrorDetailsDTO handleResourceNotFoundException(final ConflictException e, final WebRequest request) {
        return this.createErrorDetails(e, request);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class, ConstraintViolationException.class, MissingServletRequestPartException.class, MissingRequestHeaderException.class, HttpMessageNotReadableException.class, BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetailsDTO handleBadRequestExceptions(final Exception e, final WebRequest request) {
        log.error("{}, endpoint: {}", e.getMessage(), request.getDescription(false));
        return this.createErrorDetails(e, request);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetailsDTO handleEntityNotFoundException(final ResourceNotFoundException e, final WebRequest request) {
        return this.createErrorDetails((Exception) e, request);
    }

    protected ErrorDetailsDTO createErrorDetails(Exception e, WebRequest request) {
        return createErrorDetails(e.getMessage(), request);
    }

    protected ErrorDetailsDTO createErrorDetails(String errorMessage, WebRequest request) {
        return new ErrorDetailsDTO(LocalDateTime.now(), errorMessage, request.getDescription(false));
    }
}
