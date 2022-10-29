/*
 * Copyright (c) 2020. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package com.heimdall.entrypoint.util;

import br.com.fenrir.persistencia.utils.PagingUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.odin.mythcriteria.cto.MythCTOImp;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.CacheControl;

public class BaseRest {

    private static final Logger LOG = LoggerFactory.getLogger(BaseRest.class);

    private static final String PRAGMA_NOCACHE = "no-cache";

    public static ResponseEntity<Object> error(HttpStatus httpStatus, String message) {
        return error(httpStatus, null, new ApiMessage(null, null, message));
    }

    public static ResponseEntity<Object> error(HttpStatus httpStatus, String code, String type, String title,
                                               String message) {
        return error(httpStatus, code, new ApiMessage(type, title, message));
    }

    public static ResponseEntity<Object> error(HttpStatus httpStatus, String code, ApiMessage message) {
        return error(httpStatus, code, null, message);
    }

    public static ResponseEntity<Object> error(HttpStatus httpStatus, String code, List<String> fields,
                                               ApiMessage messages) {
        return error(httpStatus, code, fields, Collections.singletonList(messages));
    }

    public static ResponseEntity<Object> error(HttpStatus httpStatus, String code, List<ApiMessage> messages) {
        return error(httpStatus, code, null, messages);
    }

    public static ResponseEntity<Object> error(HttpStatus httpStatus, String code, List<String> fields, List<ApiMessage> messages) {
        return ResponseEntity
                .status(httpStatus)
                .header(HttpHeaders.PRAGMA, PRAGMA_NOCACHE)
                .cacheControl(CacheControl.noCache())
                .cacheControl(CacheControl.noStore())
                .body(ApiReturn.error(httpStatus.value(), code, fields, messages));
    }

    public static ResponseEntity<Object> success(Object obj) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.PRAGMA, PRAGMA_NOCACHE);
        return success(httpHeaders, HttpStatus.OK, obj);
    }

    public static ResponseEntity<Object> success(HttpStatus status) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.PRAGMA, PRAGMA_NOCACHE);
        return success(httpHeaders, status, null);
    }

    public static ResponseEntity<Object> success(HttpStatus status, Object obj) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.PRAGMA, PRAGMA_NOCACHE);
        return success(httpHeaders, status, obj);
    }

    public static ResponseEntity<Object> success(HttpHeaders headers, Object obj) {
        return success(headers, HttpStatus.OK, obj);
    }

    public static ResponseEntity<Object> success(HttpHeaders headers, HttpStatus status,
                                                 Object obj) {
        return ResponseEntity
                .status(status)
                .headers(headers)
                .header(HttpHeaders.PRAGMA, PRAGMA_NOCACHE)
                .cacheControl(CacheControl.noCache())
                .cacheControl(CacheControl.noStore())
                .body(
                        obj != null
                                ? ApiReturn.sucess(status.value(), obj)
                                : ApiReturn.sucess(status.value())
                );
    }

    public static Object toMapper(Object obj) {
        // object -> Map
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(obj, Map.class);
        LOG.debug("DEBUG: Objeto transformado em Map<String, Object>: {}", map);
        return map;
    }

//    /**
//     * Check if pagination properties (page, size) is used.
//     * This Method grants pagination or not to GET Methods in REST Controller.
//     * @param filter {@link MythCTOImp}
//     * @param page Integer page number
//     * @param size Integer size of pages
//     * @return {@link MythCTOImp}
//     */
//    public static MythCTOImp<?> verifyPagination(MythCTOImp<?> filter, Integer page,
//            Integer size) {
//        return verifyPagination(filter, page,
//            size, null, null);
//    }
//
//    /**
//     * Check if pagination properties (page, size) is used.
//     * This Method grants pagination or not to GET Methods in REST Controller.
//     * @param filter {@link MythCTOImp}
//     * @param page Integer page number
//     * @param size Integer size of pages
//     * @param lastID The Last ID in Query.
//     * @return {@link MythCTOImp}
//     */
//    public static MythCTOImp<?> verifyPagination(MythCTOImp<?> filter, Integer page,
//            Integer size, Long lastID) {
//        return verifyPagination(filter, page,
//            size, null, lastID);
//    }
//
//    /**
//     * Check if pagination properties (page, size) is used.
//     * This Method grants pagination or not to GET Methods in REST Controller.
//     * @param filter {@link MythCTOImp}
//     * @param page Integer page number
//     * @param size Integer size of pages
//     * @param propertieName Primary Key propertie name
//     * @param lastID The Last ID in Query.
//     * @return {@link MythCTOImp}
//     */
//    public static MythCTOImp<?> verifyPagination(MythCTOImp<?> filter, Integer page,
//            Integer size, String propertieName, Long lastID) {
//        if (page != null && size != null) {
//            PagingUtils pUtils = new PagingUtils(filter)
//                    .setPageNumber(page)
//                    .setSizeNumber(size);
//            if (propertieName != null) {
//                pUtils.setIdPropertieName(propertieName);
//            }
//            if (lastID != null) {
//                filter = pUtils.build(lastID);
//            } else {
//                filter = pUtils.build();
//            }
//        }
//        return filter;
//    }
}
