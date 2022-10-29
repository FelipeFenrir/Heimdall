package com.heimdall.core.exceptions;

import com.heimdall.core.exceptions.util.ErrorField;
import com.heimdall.core.exceptions.util.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends RuntimeException {
    private final ErrorMessage errorMessage;

    public BusinessException(String code, String message, List<ErrorField> fields) {
        super();
        this.errorMessage = new ErrorMessage(code, message, fields);
    }

    public BusinessException(String code, String message) {
        super();
        this.errorMessage = new ErrorMessage(code, message, List.of());
    }

    public BusinessException(String code, String message, ErrorField field) {
        super();
        List<ErrorField> uniqueFieldList = new ArrayList<>();
        uniqueFieldList.add(field);
        this.errorMessage = new ErrorMessage(code, message, uniqueFieldList);
    }

    public ErrorMessage getError() {
        return this.errorMessage;
    }
}
