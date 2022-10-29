/*
 * Copyright (c) 2020. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package com.heimdall.entrypoint.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapper<T> extends ResponseEntity<T> {

    @JsonProperty("http_status")
    private Integer httpStatus;

    @JsonProperty("code")
    private String code;

    @JsonProperty("page_rows")
    private Integer pageRows = 0;

    @JsonProperty("data")
    private T data;

    @JsonProperty("messages")
    private List<ApiMessage> messages;

    /**
     * Build Method.
     * @param httpStatus HTTP Response Code.
     * @param code Api Code.
     * @param data Content of Response.
     * @param messages List of Messages.
     */
    public ResponseWrapper(Integer httpStatus, String code, T data,
            List<ApiMessage> messages) {
        super(data, HttpStatus.valueOf(httpStatus));
        this.httpStatus = httpStatus;
        this.code = code;
        this.data = data;
        if (this.data instanceof List) {
            List<?> listaDeObjetos = (List<?>) data;
            this.pageRows = listaDeObjetos.size();
        } else {
            if (this.data != null) {
                this.pageRows = 1;
            }
        }
        this.messages = messages;
    }
}
