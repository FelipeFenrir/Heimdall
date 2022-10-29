/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.infrastructure.adapters.commons.config.documentation;

//import org.springframework.beans.factory.annotation.Value;
//import br.com.fenrir.auth.commons.constants.ResourcePathConstants;
//import br.com.fenrir.commons.utils.enums.EnumInfraProfile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Swagger WebMvcConfigurer implementation.
 *
 * @author Felipe de Andrade Batista
 */
@Slf4j
@ConditionalOnProperty(value = "app.swagger.enabled", havingValue = "true")
@Component
@EnableWebMvc
public class SwaggerUiWebMvcConfigurer implements WebMvcConfigurer {
//    private final String baseUrl;

//    /**
//     * Construtor of SwaggerUiWebMvcConfigurer.
//     * @param baseUrl of application.
//     */
//    public SwaggerUiWebMvcConfigurer(@Value("${springfox.documentation.swagger-ui.base-url:}") String baseUrl) {
//        this.baseUrl = baseUrl;
//    }

    /**
     * Config Ressource Handler.
     *
     * @param registry {@link ResourceHandlerRegistry}
     */
    @Override
    public void addResourceHandlers(@Nullable ResourceHandlerRegistry registry) {
        if (registry != null) {
//        String baseUrl = StringUtils.trimTrailingCharacter(this.baseUrl, '/');
//        registry.
//                addResourceHandler(baseUrl + "/swagger-ui/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
//                .resourceChain(false);
            registry.addResourceHandler(SwaggerProperties.DOCUMENTATION_PAGE)
                    .addResourceLocations(SwaggerProperties.RESOURCE_LOCATION_2);
        }
    }

//    /**
//     * Config View controllers and View Names.
//     *
//     * @param registry {@link ViewControllerRegistry}
//     */
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController(baseUrl + "/swagger-ui/")
//                .setViewName("forward:" + baseUrl + "/swagger-ui/index.html");
//    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
////        registry
////                .addMapping("/api/pet")
////                .allowedOrigins("http://editor.swagger.io");
//        registry
//                .addMapping("/v2/api-docs.*")
//                .allowedOrigins("http://editor.swagger.io");
//    }
}