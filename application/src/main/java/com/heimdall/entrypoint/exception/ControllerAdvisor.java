/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.exception;

import com.heimdall.entrypoint.constants.MessageBundleConstants;

import com.fenrir.rest.utils.BaseRest;

import com.heimdall.entrypoint.util.ApiMessage;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *     Exception Handler of Controller.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messages;

    /**
     * Handle Exception if DataNotFoundException is Throw.
     * @param ex {@link DataNotFoundException}.
     * @param request request {@link WebRequest}.
     * @return {@link ResponseEntity}.
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleNodataFoundException(DataNotFoundException ex, WebRequest request) {
        return BaseRest.error(HttpStatus.NO_CONTENT, messages.getMessage(
                MessageBundleConstants.API_COMMON_RESPONSE_NO_CONTENT, null, request.getLocale())
        );
    }

    /**
     * Handle Exception if ResourceNotFoundException is Throw.
     * @param ex {@link ResourceNotFoundException}.
     * @param request request {@link WebRequest}.
     * @return {@link ResponseEntity}.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return BaseRest.error(HttpStatus.NOT_FOUND, messages.getMessage(
                MessageBundleConstants.API_COMMON_RESPONSE_NOT_FOUND, null, request.getLocale())
        );
    }

    /**
     * Handle Exception if MethodArgumentTypeMismatchException is Throw.
     * @param ex {@link ResourceNotFoundException}.
     * @param request request {@link WebRequest}.
     * @return {@link ResponseEntity}.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        final String name = ex.getName();
        final String type = Objects.requireNonNull(ex.getRequiredType()).getSimpleName();
        final Object value = ex.getValue();
        final String message = messages.getMessage(
                MessageBundleConstants.API_COMMON_RESPONSE_BAD_REQUEST_TYPE_MISMATCH,
                null,
                request.getLocale());
        final String title = messages.getMessage(
                MessageBundleConstants.API_COMMON_RESPONSE_BAD_REQUEST,
                null,
                request.getLocale());
        final String formattedMessage = String.format(message, name, type, value);
        final ApiMessage apiMessage = new ApiMessage();
        apiMessage.setTitulo(title);
        apiMessage.setTexto(formattedMessage);

        return BaseRest.error(
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.toString(),
                apiMessage);
    }

    /**
     * Handle Exception if MethodArgumentNotValidException is Throw.
     * @param ex {@link MethodArgumentNotValidException}.
     * @param headers {@link HttpHeaders}.
     * @param status {@link HttpStatus}.
     * @param request {@link WebRequest}.
     * @return {@link ResponseEntity}.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Override
    protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                           @NonNull HttpHeaders headers,
                                                                           @NonNull HttpStatus status,
                                                                           @NonNull WebRequest request) {

//        List<String> errors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(x -> x.getDefaultMessage())
//                .collect(Collectors.toList());

        List<ApiMessage> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> {
                    ApiMessage message = new ApiMessage();
                    message.setTitulo(fieldError.getField());
                    message.setTexto(fieldError.getDefaultMessage());
                    return message;
                })
                .collect(Collectors.toList());

        return BaseRest.error(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.toString(), errors);
    }
}
