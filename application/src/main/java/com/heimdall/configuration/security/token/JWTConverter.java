/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package com.heimdall.configuration.security.token;

//import com.heimdall.configuration.security.properties.AuthProperties;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.UrlResource;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ResourceUtils;
//
//import java.io.FileNotFoundException;
//import java.net.URL;

//@Slf4j
//@Component
//@AllArgsConstructor
public class JWTConverter {

//    private final AuthProperties properties;
//
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter()
//            throws RuntimeException {
//        JwtAccessTokenConverter converter = new CustomTokenEnhancer();
//        URL keystoreUrl;
//        try {
//            keystoreUrl = ResourceUtils.getURL(properties.getLocationFileJKS());
//            converter.setKeyPair(
//                    new KeyStoreKeyFactory(
//                            new UrlResource(keystoreUrl),
//                            properties.getJksPassword().toCharArray()
//                    ).getKeyPair(properties.getJksKeyPair()));
//            return converter;
//        } catch (FileNotFoundException ex) {
//            log.error("ERROR: ApiError to Load JKS file. " + ex);
//            throw new RuntimeException("The Key file not Found,"
//                    + " contact the system administrator.");
//        }
//    }
}
