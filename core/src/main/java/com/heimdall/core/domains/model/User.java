/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.domains.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *      Domain Interface of {@link User}.
 * </p>
 * <p>
 *     This class implements Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista.
 */
public interface User extends BaseDomain {

    /**
     * <p>
     *     Get ID of {@link User}.
     * </p>
     * @return ID of {@link User}.
     */
    UUID getId();

    /**
     * <p>
     *     Get Name of {@link User}.
     * </p>
     * @return Name of {@link User}.
     */
    String getUsername();

    /**
     * <p>
     *     Get the e-mail of {@link User}.
     * </p>
     * @return {@link User} e-mail.
     */
    String getEmail();

    /**
     * <p>
     *     Get the pending validation e-mail of {@link User}.
     * </p>
     * @return {@link User} pending validation e-mail.
     */
    String getPendingEmail();

    /**
     * <p>
     *     Get Password of {@link User}.
     * </p>
     * @return {@link User} Password.
     */
    String getPassword();

    /**
     * <p>
     *     Get Tenant ID of {@link User}.
     * </p>
     * @return {@link User} Tenant ID.
     */
    String getTenantid();

    /**
     * <p>
     *     Get Confirmation Token of {@link User}.
     * </p>
     * @return {@link User} confirmation token.
     */
    String getConfirmationToken();

    /**
     * <p>
     *     {@link User} is enabled.
     * </p>
     * <p>
     *     {@link User} is Disabled until they click on confirmation link in email.
     * </p>
     * @return True or False.
     */
    boolean isEnabled();

    /**
     * <p>
     *     {@link User} accept the Term.
     *     Term is not accepted by default.
     * </p>
     * @return True or False.
     */
    boolean isAcceptTerm();

    /**
     * <p>
     *     {@link User} account is Expired.
     *     Account is not expired by default.
     * </p>
     * @return True or False.
     */
    boolean isAccountNonExpired();

    /**
     * <p>
     *     {@link User} account is Locked.
     *     Account is not locked by default.
     * </p>
     * @return True or False.
     */
    boolean isAccountNonLocked();

    /**
     * <p>
     *     {@link User} credentials is Expired.
     *     credentials is not expired by default.
     * </p>
     * @return True or False.
     */
    boolean isCredentialsNonExpired();

    /**
     * <p>
     *     Get {@link User} roles.
     * </p>
     * @return List of {@link User} {@link Role}.
     */
    Collection<Role> getRole();

    /**
     * <p>
     *     Generate a Default {@link User} username by the email.
     * </p>
     * @param email {@link User} email.
     * @return Default username for {@link User}.
     */
    static String generateDefaultUsernameByEmail(final String email) {
        return email.substring(0, email.indexOf('@'));
    }

    /**
     * <p>
     *     Generate a Default {@link User} password.
     * </p>
     * @return Default password for {@link User}.
     */
    static String generateDefaultPassword() {
        String defaultLetters = LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        return shuffleText(defaultLetters);
    }

    /**
     * Verify if confirmation token is expired.
     *
     * @param minutes minutes of token expiration.
     * @return Token is Expired or not.
     */
    default boolean isConfirmationTokenExpired(long minutes) {
        return LocalDateTime.now().isAfter(getCreatedOn().plusMinutes(minutes));
    };

    static String shuffleText(String text) {
        List<String> letters = Arrays.asList(text.split(""));
        Collections.shuffle(letters);
        StringBuilder letterBuilder = new StringBuilder(text.length());
        for (String k : letters) {
            letterBuilder.append(k);
        }
        return letterBuilder.toString();
    }
}
