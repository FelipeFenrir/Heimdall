/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.exception;

import com.heimdall.entrypoint.boundary.entities.ApiError;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.MessageSource;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//import org.zalando.problem.Problem;
//import org.zalando.problem.StatusType;
import org.zalando.problem.spring.common.HttpStatusAdapter;

import javax.validation.constraints.NotNull;

//import java.net.URI;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    private static final String PRAGMA_NOCACHE = "no-cache";

    private final MessageSource messages;

    public ControllerAdvisor(MessageSource messages) {
        this.messages = messages;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleNodataFoundException(DataNotFoundException ex, WebRequest request) {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .header(HttpHeaders.PRAGMA, PRAGMA_NOCACHE)
            .cacheControl(CacheControl.noCache())
            .cacheControl(CacheControl.noStore())
            .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

        var problem = problemBuilder(
            HttpStatus.BAD_REQUEST,
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            messages.getMessage("api.common.response.404.resource.notfound", null, request.getLocale())
        );

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .header(HttpHeaders.PRAGMA, PRAGMA_NOCACHE)
            .cacheControl(CacheControl.noCache())
            .cacheControl(CacheControl.noStore())
            .body(problem);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                     WebRequest request) {
        final String name = ex.getName();
        final String type = Objects.requireNonNull(ex.getRequiredType()).getSimpleName();
        final Object value = ex.getValue();
        final String message = messages.getMessage(
                "api.common.response.400.badrequest.typemismatch",
                null,
                request.getLocale());
        final String title = messages.getMessage(
                "api.common.response.400.badrequest",
                null,
                request.getLocale());
        final String formattedMessage = String.format(message, name, type, value);

        var problem = problemBuilder(
            HttpStatus.BAD_REQUEST,
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            Map.of(title, formattedMessage)
        );

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .header(HttpHeaders.PRAGMA, PRAGMA_NOCACHE)
            .cacheControl(CacheControl.noCache())
            .cacheControl(CacheControl.noStore())
            .body(problem);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Override
    protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                           @NonNull HttpHeaders headers,
                                                                           @NonNull HttpStatusCode status,
                                                                           @NonNull WebRequest request) {

        Map<String, Object> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        var problem = problemBuilder(
            HttpStatus.BAD_REQUEST,
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            errors
            );

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .header(HttpHeaders.PRAGMA, PRAGMA_NOCACHE)
            .cacheControl(CacheControl.noCache())
            .cacheControl(CacheControl.noStore())
            .body(problem);
    }

    private ApiError problemBuilder(HttpStatus status, String title, String detail) {
        var statusType = new HttpStatusAdapter(status);
        return ApiError.builder()
            .status(statusType)
            //.type(URI.create("https://example.org/out-of-stock"))
            .title(title)
            .detail(detail)
            .build();
    }

    private ApiError problemBuilder(HttpStatus status, String title, String detail, Map<String, Object> parameters) {
        var problem = problemBuilder(status, title, detail);
        problem.setParameters(parameters);
        return problem;
    }
}
