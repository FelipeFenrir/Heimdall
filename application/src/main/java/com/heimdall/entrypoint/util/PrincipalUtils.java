/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.util;

import com.heimdall.entrypoint.constants.MessageBundleConstants;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.security.Principal;
import java.util.Locale;

/**
 * <p>
 *     Extract {@link UserDetails} from Principal Object.
 * </p>
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class PrincipalUtils {

    private static final String DEFAULT_ERROR_MSG = "ApiError on Authentication.";

    @Autowired
    private static MessageSource messages;

    /**
     * <p>
     *     Convert Principal to {@link UserDetails} Entity for details.
     *     This is Used in Login on Auth Server with Login Page.
     * </p>
     *
     * @param principal is a Login ID
     * @return {@link UserDetails}
     */
    public static UserDetails userPassAuthToUser(Principal principal) {
        UsernamePasswordAuthenticationToken userPassAuthToken = (UsernamePasswordAuthenticationToken) principal;
        return (UserDetails) userPassAuthToken.getPrincipal();
    }

    /**
     * <p>
     *     Convert Principal to {@link UserDetails} Entity for details.
     *     This is used in Oauth Flow with Token.
     * </p>
     *
     * @param principal is a Login ID
     * @return {@link UserDetails}
     */
    public static UserDetails oauthAuthToUser(Principal principal) {
        OAuth2Authentication userPassAuthToken = (OAuth2Authentication) principal;
        return (UserDetails) userPassAuthToken.getPrincipal();
    }

    /**
     * <p>
     *     Convert Principal to {@link UserDetails} Entity for details.
     *     This is used in Remember Me Cookie.
     * </p>
     *
     * @param principal is a Login ID
     * @return {@link UserDetails}
     */
    public static UserDetails rememberMeAuthToUser(Principal principal) {
        RememberMeAuthenticationToken userPassAuthToken = (RememberMeAuthenticationToken) principal;
        return (UserDetails) userPassAuthToken.getPrincipal();
    }

    /**
     * <p>
     *     Verify type of Principal and Convert.
     * </p>
     *
     * @param principal is a Login ID
     * @return {@link UserDetails}
     */
    private static UserDetails getUserOfPrincipal(Principal principal) {
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
     * <p>
     *     Convert Principal to {@link UserDetails} Entity for details.
     *     Verify type of Authentication and Convert the Principal Object in {@link UserDetails} Object.
     * </p>
     *
     * @param principal is a Login ID
     * @return {@link UserDetails}
     * @throws AuthenticationCredentialsNotFoundException user not Found.
     */
    public static UserDetails getInAuthObject(Principal principal)
            throws AuthenticationCredentialsNotFoundException {
        if (principal != null) {
            UserDetails user = getUserOfPrincipal(principal);
            if (user != null) {
                return user;
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
     * <p>
     *     Convert Principal to {@link UserDetails} Entity for details.
     *     Verify type of Authentication and Convert the Principal Object in {@link UserDetails} Object with Locale.
     * </p>
     *
     * @param principal is a Login ID
     * @param locale Locale to Response
     * @return {@link UserDetails}
     * @throws AuthenticationCredentialsNotFoundException user not Found.
     */
    public static UserDetails getInAuthObject(Principal principal, Locale locale)
            throws AuthenticationCredentialsNotFoundException {
        if (principal != null) {
            UserDetails user = getUserOfPrincipal(principal);
            if (user != null) {
                return user;
            } else {
                AuthenticationCredentialsNotFoundException authException =
                        new AuthenticationCredentialsNotFoundException(messages.getMessage(
                                MessageBundleConstants.COMMON_ERROR_INSTANCE_AUTH, null, locale)
                        );
                log.error("ApiError in Instance of Principal.", authException);
                throw authException;
            }
        } else {
            AuthenticationCredentialsNotFoundException authException =
                    new AuthenticationCredentialsNotFoundException(messages.getMessage(
                            MessageBundleConstants.COMMON_ERROR_INSTANCE_AUTH, null, locale)
                    );
            log.error("Principal is Null.", authException);
            throw authException;
        }
    }
}
