/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.entity.domain;

import java.time.LocalDateTime;

/**
 * <p>
 *      Domain Interface of {@link ResetEmailToken}.
 * </p>
 * <p>
 *     This class implements Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface ResetEmailToken extends BaseDomain {
    /**
     * <p>
     *      Get ID of {@link ResetEmailToken} Entity.
     * </p>
     * @return {@link ResetEmailToken} ID.
     */
    long getId();

    /**
     * <p>
     *     Get Pending Email from User.
     * </p>
     * @return {@link ResetEmailToken} pending email.
     */
    String getPendingEmail();

    /**
     * <p>
     *      Get token of {@link ResetEmailToken} Entity.
     * </p>
     * @return {@link ResetEmailToken} token.
     */
    String getResetEmailToken();

    /**
     * <p>
     *      Get expiry date of {@link ResetEmailToken} Entity.
     * </p>
     * @return {@link ResetEmailToken} expiry date.
     */
    LocalDateTime getExpiryDate();

    /**
     * <p>
     *      Get {@link User} of {@link ResetEmailToken} Entity.
     * </p>
     * @return {@link User} of {@link ResetEmailToken}.
     */
    User getUser();

    /**
     * <p>
     *      Set Expiry Date in minutes.
     * </p>
     * @param minutes Expiry Date in minutes
     */
    void setExpiryDate(long minutes);

    /**
     * <p>
     *      Verify if Reset Password Token is Expired.
     * </p>
     * @return True or False
     */
    default boolean isExpired() {
        return LocalDateTime.now().isAfter(getExpiryDate());
    };
}
