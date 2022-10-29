package com.heimdall.core.exceptions.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private String code;
    private String message;
    private List<ErrorField> fields;
}
