/*
 * Copyright (c) 2020. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package com.heimdall.entrypoint.util;

import java.util.Collections;
import java.util.List;

public final class ApiReturn {

    public static ResponseWrapper<?> sucess(Integer httpStatus) {
        return returnBuilder(httpStatus);
    }

    public static ResponseWrapper<?> sucess(Integer httpStatus, Object data) {
        return returnBuilder(httpStatus, data);
    }

    public static ResponseWrapper<?> sucess(Integer httpStatus, String code, Object data) {
        return returnBuilder(httpStatus, code, data);
    }

    public static ResponseWrapper<?> sucess(Integer httpStatus, Object data,
                                            List<ApiMessage> messages) {
        return returnBuilder(httpStatus, data, messages);
    }

    public static ResponseWrapper<?> sucess(Integer httpStatus, String code,
                                            Object data, List<ApiMessage> messages) {
        return returnBuilder(httpStatus, code, data, messages);
    }

    public static ApiError error(Integer httpStatus, ApiMessage message) {
        return errorBuilder(httpStatus, null, null, message);
    }

    public static ApiError error(Integer httpStatus, String code, ApiMessage message) {
        return errorBuilder(httpStatus, code, null, message);
    }

    public static ApiError error(Integer httpStatus, String code, List<String> fields,
                                 ApiMessage message) {
        return errorBuilder(httpStatus, code, fields, message);
    }

    public static ApiError error(Integer httpStatus, String code, List<String> fields,
                                 List<ApiMessage> messages) {
        return errorBuilder(httpStatus, code, fields, messages);
    }

    private static ResponseWrapper<?> returnBuilder(Integer httpStatus) {
        return returnBuilder(httpStatus, null, null, null);
    }

    private static ResponseWrapper<?> returnBuilder(Integer httpStatus, Object data) {
        return returnBuilder(httpStatus, null, data, null);
    }

    private static ResponseWrapper<?> returnBuilder(Integer httpStatus, String code, Object data) {
        return returnBuilder(httpStatus, code, data, null);
    }

    private static ResponseWrapper<?> returnBuilder(Integer httpStatus, Object data, List<ApiMessage> messages) {
        return returnBuilder(httpStatus, null, data, messages);
    }

    private static ResponseWrapper<?> returnBuilder(Integer httpStatus, String code,
                                                    Object data, List<ApiMessage> messages) {
        return new ResponseWrapper<>(httpStatus, code, data, messages);
    }

    private static ApiError errorBuilder(Integer httpStatus, String code, List<String> fields,
                                         ApiMessage message) {
        return errorBuilder(httpStatus, code, fields, Collections.singletonList(message));
    }

    private static ApiError errorBuilder(Integer httpStatus, String code, List<String> fields,
                                         List<ApiMessage> messages) {
        return new ApiError(httpStatus, code, fields, messages);
    }
}
