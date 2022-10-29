/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.domains.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 *      Domain Interface of {@link ResetPasswordToken}.
 * </p>
 * <p>
 *     This class implements Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface ResetPasswordToken extends BaseDomain {
    /**
     * <p>
     *      Get ID of {@link ResetPasswordToken} Entity.
     * </p>
     * @return {@link ResetPasswordToken} ID.
     */
    UUID getId();

    /**
     * <p>
     *      Get token of {@link ResetPasswordToken} Entity.
     * </p>
     * @return {@link ResetPasswordToken} token.
     */
    String getResetPassToken();

    /**
     * <p>
     *      Get expiry date of {@link ResetPasswordToken} Entity.
     * </p>
     * @return {@link ResetPasswordToken} expiry date.
     */
    LocalDateTime getExpiryDate();

    /**
     * <p>
     *      Get {@link User} of {@link ResetPasswordToken} Entity.
     * </p>
     * @return {@link User} of {@link ResetPasswordToken}.
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
