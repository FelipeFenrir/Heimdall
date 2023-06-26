/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.infrastructure.adapters.commons.config.documentation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>Swagger Properties class.</p>
 * <p>
 * Swagger enabled propertie.
 *
 * Example for configuration in application.properties or application.yml
 *
 * In .properties file:
 *
 *      app.swagger.enabled = true
 *
 * In .yml file:
 *
 *      app:
 *          swagger:
 *              enabled: true
 *              auth:
 *                  client: #####
 *                  password: #####
 * </p>
 * @author Felipe de Andrade Batista
 */
@Getter
@Setter
@Component
public class SwaggerProperties {

    /**
     * SWAGGER PAGE V2.
     */
    public static final String DOCUMENTATION_PAGE = "swagger-ui.html";
    /**
     * SWAGGER RESOURCES LOCATION V2.
     */
    public static final String RESOURCE_LOCATION_2 = "classpath:/META-INF/resources/";
    /**
     * SWAGGER RESOURCES V2.
     */
    public static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-resources",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security",
            "/swagger-ui.html",
            "/**swagger**/**"};

    /**
     * SWAGGER PAGE V3.
     */
    public static final String DOCUMENTATION_PAGE_3 = "/swagger-ui/**";
    /**
     * SWAGGER RESOURCES LOCATION V3.
     */
    public static final String RESOURCE_LOCATION_3 = "classpath:/META-INF/resources/webjars/springfox-swagger-ui/";

    /**
     * Name of Swagger Token.
     */
    public static final String NAME_SWAGGER_TOKEN = "SwaggerToken";
    /**
     * Name of Security schema in Swagger.
     */
    public static final String AUTHORIZATION_FLOW_SCHEMA = "spring_oauth";
    /**
     * Name of Security schema in Swagger.
     */
    public static final String RESOURCE_OWNER_SCHEMA = "spring_oauth_resource_owner";
    /**
     * Name of Security schema in Swagger.
     */
    public static final String IMPLICIT_SCHEMA = "spring_oauth_implicit";

    /**
     * <p>
     * Swagger enabled propertie.
     *
     *
     * Example for configuration in application.properties or application.yml
     *
     * In .properties file:
     *
     *      app.swagger.enabled = true
     *
     * In .yml file:
     *
     *      app:
     *          swagger:
     *              enabled: true
     * </p>
     */
    @Value("${app.swagger.enabled:false}")
    private boolean enableSwagger;

    /**
     * <p>
     * Swagger Auth Client Name or ID propertie.
     *
     *
     * Example for configuration in application.properties or application.yml
     *
     * In .properties file:
     *
     *      app.swagger.auth.client = #####
     *
     * In .yml file:
     *
     *      app:
     *          swagger:
     *              auth:
     *                  client: #####
     * </p>
     */
    @Value("${app.swagger.auth.client:SWAGGER_CLIENT_APP}")
    private String swaggerAuthClient;

    /**
     * <p>
     * Swagger Auth Client Password propertie.
     *
     *
     * Example for configuration in application.properties or application.yml
     *
     * In .properties file:
     *
     *      app.swagger.auth.password = #####
     *
     * In .yml file:
     *
     *      app:
     *          swagger:
     *              auth:
     *                  password: #####
     * </p>
     */
    @Value("${app.swagger.auth.password:password}")
    private String swaggerAuthPassword;
}
