/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package br.com.fenrir.auth.adapters.entrypoint.util;

import br.com.fenrir.auth.adapters.outbound.dataprovider.datamapper.UserDataMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.security.Principal;
import java.util.Locale;

/**
 * <p>
 *      AuthUtils class.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class UserExtractUtils {

    private static final String DEFAULT_ERROR_MSG = "ApiError on Authentication.";

    @Autowired
    private static MessageSource messages;

    /**
     * Convert Principal to UserDataMapper Entity for details.
     * This is Used in Login on Auth Server with Login Page.
     *
     * @param principal is a Login ID
     * @return {@link UserDataMapper}
     */
    public static UserDataMapper userPassAuthToUser(Principal principal) {
        UsernamePasswordAuthenticationToken userPassAuthToken
                = (UsernamePasswordAuthenticationToken) principal;
        return (UserDataMapper) userPassAuthToken.getPrincipal();
    }

    /**
     * Convert Principal to UserDataMapper Entity for details.
     * This is used in Oauth Flow with Token.
     *
     * @param principal is a Login ID
     * @return {@link UserDataMapper}
     */
    public static UserDataMapper oauthAuthToUser(Principal principal) {
        OAuth2Authentication userPassAuthToken
                = (OAuth2Authentication) principal;
        return (UserDataMapper) userPassAuthToken.getPrincipal();
    }

    /**
     * Convert Principal to UserDataMapper Entity for details.
     * This is used in Remember Me Cookie.
     *
     * @param principal is a Login ID
     * @return {@link UserDataMapper}
     */
    public static UserDataMapper rememberMeAuthToUser(Principal principal) {
        RememberMeAuthenticationToken userPassAuthToken
                = (RememberMeAuthenticationToken) principal;
        return (UserDataMapper) userPassAuthToken.getPrincipal();
    }

    /**
     * Verify type of Principal and Convert.
     * @param principal is a Login ID
     * @return {@link UserDataMapper}
     */
    private static UserDataMapper getUserOfPrincipal(Principal principal) {
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            return userPassAuthToUser(principal);
        } else if (principal instanceof OAuth2Authentication) {
            return oauthAuthToUser(principal);
        } else if (principal instanceof RememberMeAuthenticationToken) {
            return rememberMeAuthToUser(principal);
        } else {
            return null;
        }
    }

    /**
     * Convert Principal to UserDataMapper Entity for details.
     * Verify type of Authentication and Convert the Principal Object in UserDataMapper Object.
     *
     * @param principal is a Login ID
     * @return {@link UserDataMapper}
     * @throws AuthenticationCredentialsNotFoundException UserDataMapper not Found.
     */
    public static UserDataMapper getInAuthObject(Principal principal)
            throws AuthenticationCredentialsNotFoundException {
        if (principal != null) {
            UserDataMapper userDataMapper = getUserOfPrincipal(principal);
            if (userDataMapper != null) {
                    return userDataMapper;
            } else {
                AuthenticationCredentialsNotFoundException authException =
                        new AuthenticationCredentialsNotFoundException(DEFAULT_ERROR_MSG);
                log.error("ApiError in Instance of Principal.", authException);
                throw authException;
            }
        } else {
            AuthenticationCredentialsNotFoundException authException =
                    new AuthenticationCredentialsNotFoundException(DEFAULT_ERROR_MSG);
            log.error("Principal is Null.", authException);
            throw authException;
        }
    }

    /**
     * Convert Principal to UserDataMapper Entity for details.
     * Verify type of Authentication and Convert the Principal Object in UserDataMapper Object with Locale.
     *
     * @param principal is a Login ID
     * @param locale Locale to Response
     * @return {@link UserDataMapper}
     * @throws AuthenticationCredentialsNotFoundException UserDataMapper not Found.
     */
    public static UserDataMapper getInAuthObject(Principal principal, Locale locale)
            throws AuthenticationCredentialsNotFoundException {
        if (principal != null) {
            UserDataMapper userDataMapper = getUserOfPrincipal(principal);
            if (userDataMapper != null) {
                return userDataMapper;
            } else {
                AuthenticationCredentialsNotFoundException authException =
                        new AuthenticationCredentialsNotFoundException(messages.getMessage(
                                "common.error.instanceAuth", null, locale)
                        );
                log.error("ApiError in Instance of Principal.", authException);
                throw authException;
            }
        } else {
            AuthenticationCredentialsNotFoundException authException =
                    new AuthenticationCredentialsNotFoundException(messages.getMessage(
                            "common.error.instanceAuth", null, locale)
                    );
            log.error("Principal is Null.", authException);
            throw authException;
        }
    }
}
