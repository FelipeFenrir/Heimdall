/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.infrastructure.adapters.commons.constants;

/**
 * <p>
 *     Path Constants.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public class ResourcePathConstants {
    /**
     * <p>
     *     WEBJARS PATH.
     * </p>
     */
    public static final String WEBJARS_PATH = "/webjars/**";
    /**
     * <p>
     *     IMAGES PATH.
     * </p>
     */
    public static final String IMAGES_PATH = "/images/**";
    /**
     * <p>
     *     CSS PATH.
     * </p>
     */
    public static final String CSS_PATH = "/css/**";
    /**
     * <p>
     *     JS PATH.
     * </p>
     */
    public static final String JS_PATH = "/js/**";
    /**
     * <p>
     *     HEALTH PATH.
     * </p>
     */
    public static final String HEALTH_PATH = "/health**";


    /**
     * <p>
     *     PATHS.
     * </p>
     */
    public static final String[] PATH_PATTERNS = {
            WEBJARS_PATH,
            IMAGES_PATH,
            CSS_PATH,
            JS_PATH};

    /**
     * <p>
     *     SECURE PATHS.
     * </p>
     */
    public static final String[] PATH_SECURITY_PATTERNS = {
            WEBJARS_PATH,
            IMAGES_PATH,
            CSS_PATH,
            JS_PATH,
            HEALTH_PATH};

    /**
     * <p>
     *     META-INF RESOURCE PATH.
     * </p>
     */
    public static final String  METAINF_RESOURCE = "classpath:/META-INF/resources/";
    /**
     * <p>
     *     RESOURCES LOCATION PATH.
     * </p>
     */
    public static final String[] RESOURCE_LOCATION = {
            METAINF_RESOURCE.concat("webjars/"),
            "classpath:/static/images/",
            "classpath:/static/css/",
            "classpath:/static/js/"};

    /**
     * <p>
     *     Private constructor to prevent instance this class.
     * </p>
     */
    private ResourcePathConstants() {
        throw new IllegalStateException("Utility Class");
    }
}
