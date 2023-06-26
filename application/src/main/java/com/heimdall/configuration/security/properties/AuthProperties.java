/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package com.heimdall.configuration.security.properties;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 *      AuthProperties class.
 *      This class contain all properties for Auth Server.
 * </p>
 *
 * # Injected properties in .yml
 * auth:
 *     redirectionUrl: {AUTH.REDIRECTURL}
 *     emailFrom: {AUTH.EMAILFROM}
 *     registrationEmailFrom: {AUTH.REGISTRYEMAILFROM}
 *     confirmationEmailFrom: {AUTH.CONFIRMEMAILFROM}
 *     corsAllowedOrigins: '*'
 *     checkUserScopes: true
 *     locationFileJKS: src/main/resources/{YOUR_FILE}.jks
 *     jksKeyPair: {YOUR_KEY_PAIR_NAME}
 *     jksPassword: {YOUR_JKS_FILE_PASSWORD}
 *     expiryDateResetToken: {AUTH.EXPIRYDATE_RESET_TOKEN}
 *     expiryDateConfirmToken: {AUTH.EXPIRYDATE_CONFIRM_TOKEN}
 *
 * @author Felipe de Andrade Batista
 */
@Getter
@Setter
@Component
@ConfigurationProperties("auth")
public class AuthProperties {

    public static final String OAUTH_PATH = "/oauth/**";

    public static final String CONFIRM_ACCESS = "/oauth/confirm_access";

    public static final String AUTHORIZE_VIEW = "authorize";

    public static final String IS_ANONYMOUS = "isAnonymous()";

    public static final String IS_AUTHENTICATED = "isAuthenticated()";

    public static final String IS_FULLY_AUTHENTICATED = "isFullyAuthenticated()";

    public static final String REMEMBER_ME = "isRememberMe()";

    public static final String PERMIT_ALL = "permitAll()";

    //private String redirectionUrl;
    private String emailFrom;
    private String registrationEmailFrom;
    private String confirmationEmailFrom;

    private String corsAllowedOrigins;
    private Boolean checkUserScopes;
    private String locationFileJKS;
    private String jksKeyPair;
    private String jksPassword;
    private Integer expiryDateResetToken;
    private Integer expiryDateConfirmToken;
}
