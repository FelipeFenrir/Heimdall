/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package com.heimdall.infrastructure.adapters.commons.config.documentation;

//import br.com.fenrir.persistencia.utils.FilterUtils;
//import br.com.fenrir.persistencia.utils.PagingUtils;
//import br.com.fenrir.address.commons.utils.domain.filter.Filter;
//import br.com.fenrir.address.commons.utils.domain.filter.Joins;
//import br.com.fenrir.address.commons.utils.domain.filter.Paging;
//import br.com.fenrir.address.commons.utils.domain.json.RequestWrapper;

import br.com.fenrir.app.config.DocumentationConstants;
import br.com.fenrir.commons.utils.EnvUtil;
import br.com.fenrir.commons.utils.enums.EnumCommonRoles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.ImplicitGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.LoginEndpoint;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Configuration class of Swagger. The Swagger is a Documentation Tool for APIs.
 *
 * @author Felipe de Andrade Batista
 * @see <a href="https://swagger.io/">swagger.io</a>
 */
@Slf4j
@Configuration
@RefreshScope
@EnableSwagger2
public class SwaggerConfig {

    {
        log.info("INFO: Swagger Configuration.");
    }

    private final String loginUrl = EnvUtil.getInstance()
            .getCompleteNetHost()
            .get(EnvUtil.HTTPS_SCHEME)
            .concat("/login");
    private final String tokenUrl = EnvUtil.getInstance()
            .getCompleteNetHost()
            .get(EnvUtil.HTTPS_SCHEME)
            .concat("/oauth/token");
    private final String authorizationUrl = EnvUtil.getInstance()
            .getCompleteNetHost()
            .get(EnvUtil.HTTPS_SCHEME)
            .concat("/oauth/authorize");

    //private static final String PATH_REGEX = "^\\/?(api|oauth).*$";
    private static final String PATH_REGEX = "^\\/?(api).*$";

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Autowired
    private DocumentationConstants appProperties;

    /**
     * Create a Docket Swagger Object with configuration in .properties or .yml
     * files.
     *
     * @return Docket {@link Docket}
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerProperties.isEnableSwagger())
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex(PATH_REGEX))
                .build()
//                .ignoredParameterTypes(FilterUtils.class, PagingUtils.class,
//                        Filter.class, Joins.class, Paging.class, RequestWrapper.class)
                .apiInfo(apiInfo())
                .forCodeGeneration(false)
//                .globalOperationParameters(params)
                .securitySchemes(Arrays.asList(
                        securityScheme(),
                        resourceOwnerScheme(),
                        implicitScheme()
                ))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    /**
     * Create a ApiInfo Swagger Object for documentation in Swagger UI.
     *
     * @return ApiInfo {@link ApiInfo}
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(appProperties.getAppTitle())
                .description(appProperties.getAppDescription())
                .version(appProperties.getAppVersion())
                //.termsOfServiceUrl("http://terms-of-services.url")
                //.license("Termos de licensa")
                //.licenseUrl("http://url-to-license.com")
                .contact(
                    new Contact(
                            appProperties.getAppContactName(),
                            appProperties.getAppContactUrl(),
                            appProperties.getAppContactEmail()))
                            .build();
                }

    /**
     * Bean of Security Configuration of Swagger.
     * @return {@link SecurityConfiguration} in Swagger.
     */
    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId(swaggerProperties.getSwaggerAuthClient())
                .clientSecret(swaggerProperties.getSwaggerAuthPassword())
                .scopeSeparator(",")
                .build();
    }

    /**
     * Configure a Swagger Security Scheme.
     * @return {@link SecurityScheme} in Swagger.
     */
    private SecurityScheme securityScheme() {
        GrantType authorizationCodeGrant = new AuthorizationCodeGrantBuilder()
                .tokenEndpoint(new TokenEndpoint(tokenUrl, SwaggerProperties.NAME_SWAGGER_TOKEN))
                .tokenRequestEndpoint(
                        new TokenRequestEndpoint(authorizationUrl,
                                swaggerProperties.getSwaggerAuthClient(),
                                swaggerProperties.getSwaggerAuthPassword()))
                .build();


        return new OAuthBuilder().name(SwaggerProperties.AUTHORIZATION_FLOW_SCHEMA)
                .grantTypes(Collections.singletonList(authorizationCodeGrant))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    /**
     * Configure a Swagger Security Scheme.
     * @return {@link SecurityScheme} in Swagger.
     */
    private SecurityScheme resourceOwnerScheme() {
        GrantType resourceOwnerPasswordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(tokenUrl);

        return new OAuthBuilder().name(SwaggerProperties.RESOURCE_OWNER_SCHEMA)
                .grantTypes(Collections.singletonList(resourceOwnerPasswordCredentialsGrant))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    /**
     * Configure a Swagger Security Scheme.
     * @return {@link SecurityScheme} in Swagger.
     */
    private SecurityScheme implicitScheme() {
        GrantType implicitGrantType = new ImplicitGrantBuilder()
                .loginEndpoint(new LoginEndpoint(loginUrl))
                .tokenName(SwaggerProperties.NAME_SWAGGER_TOKEN)
                .build();

        return new OAuthBuilder().name(SwaggerProperties.IMPLICIT_SCHEMA)
                .grantTypes(Collections.singletonList(implicitGrantType))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    /**
     * Configure a Swagger Security Context.
     * @return {@link SecurityContext} in Swagger.
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(
                        new SecurityReference(SwaggerProperties.AUTHORIZATION_FLOW_SCHEMA, scopes()),
                        new SecurityReference(SwaggerProperties.RESOURCE_OWNER_SCHEMA, scopes()),
                        new SecurityReference(SwaggerProperties.IMPLICIT_SCHEMA, scopes())
                ))
                .forPaths(regex(PATH_REGEX))
                .build();
    }

    /**
     * Return the Authorization Scope List.
     * @return Array of {@link AuthorizationScope}.
     */
    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = {
                new AuthorizationScope(
                        EnumCommonRoles.ROLE_SWAGGER.getNameOfRule(),
                        EnumCommonRoles.ROLE_SWAGGER.getDescriptionOfRule())
        };
        return scopes;
    }
}
