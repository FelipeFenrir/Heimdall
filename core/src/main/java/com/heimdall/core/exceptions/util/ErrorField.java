package com.heimdall.core.exceptions.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorField {
    private String name;
    private String message;
    private String value;
}
