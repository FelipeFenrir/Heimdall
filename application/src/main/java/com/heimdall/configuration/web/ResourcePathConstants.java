/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.configuration.web;


public class ResourcePathConstants {

    public static final String WEBJARS_PATH = "/webjars/**";

    public static final String IMAGES_PATH = "/images/**";

    public static final String CSS_PATH = "/css/**";

    public static final String JS_PATH = "/js/**";

    public static final String HEALTH_PATH = "/health**";

    public static final String[] PATH_PATTERNS = {
            WEBJARS_PATH,
            IMAGES_PATH,
            CSS_PATH,
            JS_PATH
    };

    public static final String[] PATH_SECURITY_PATTERNS = {
            WEBJARS_PATH,
            IMAGES_PATH,
            CSS_PATH,
            JS_PATH,
            HEALTH_PATH
    };

    public static final String  METAINF_RESOURCE = "classpath:/META-INF/resources/";

    public static final String[] RESOURCE_LOCATION = {
            METAINF_RESOURCE.concat("webjars/"),
            "classpath:/static/images/",
            "classpath:/static/css/",
            "classpath:/static/js/"};

    private ResourcePathConstants() {
        throw new IllegalStateException("Utility Class");
    }
}
