/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.usecase;

import br.com.fenrir.auth.core.entity.domain.User;
import br.com.fenrir.auth.core.usecase.exceptions.UserNotFoundException;
import br.com.fenrir.auth.core.usecase.exceptions.ResetEmailTokenNotFoundException;
import br.com.fenrir.auth.core.usecase.exceptions.ResetPassTokenNotFoundException;
import br.com.fenrir.auth.core.usecase.exceptions.UserAlreadyExistException;
import br.com.fenrir.auth.core.usecase.exceptions.UserNotEnabledException;

/**
 * <p>
 *      Account management use case interface.
 * </p>
 * <p>
 *     This class use Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface AccountUseCase {

    /**
     * <p>
     *      Register a new {@link User}.
     * </p>
     *
     * @param registrationEmail {@link User} registration email.
     */
    void registerUserByEmail(String registrationEmail) throws UserAlreadyExistException;

    /**
     * <p>
     *      Confirm given {@link User} based on token and set his password.
     * </p>
     *
     * @param confirmationToken confirmation token.
     * @param password Password of {@link User}.
     */
    void confirmUserByEmail(String confirmationToken, String password) throws UserNotFoundException;

    /**
     * <p>
     *      Reset password for given {@link User}.
     * </p>
     *
     * @param user {@link User}.
     * @param expiryMinutesResetPassToken minutes to expiry the token.
     */
    void createResetPasswordToken(User user, long expiryMinutesResetPassToken)
            throws UserNotFoundException, UserNotEnabledException;

    /**
     * <p>
     *      Confirm given reset password based on token and set his password.
     * </p>
     *
     * @param resetPasswordToken reset password token.
     * @param newPassword Password of {@link User}.
     */
    void confirmResetPass(String resetPasswordToken, String newPassword) throws ResetPassTokenNotFoundException;

    /**
     * <p>
     *      Change password for given {@link User}.
     * </p>
     *
     * @param username Username of {@link User}.
     * @param oldPassword Password of {@link User}.
     * @param newPassword new password for the {@link User}.
     * @return True if password was changed.
     */
    boolean changePassword(String username, String oldPassword, String newPassword);

    /**
     * <p>
     *      Change email for given {@link User}.
     * </p>
     *
     * @param username Username of {@link User}.
     * @param password Password of {@link User}.
     * @param newEmail New e-mail of {@link User}.
     * @param expiryMinutesResetEmailToken minutes to expiry the token.
     * @return True if e-mail reset token as created.
     */
    boolean createResetEmailToken(String username, String password, String newEmail, long expiryMinutesResetEmailToken);

    /**
     * <p>
     *      Verify email for given {@link User}.
     * </p>
     *
     * @param confirmationToken confirmation token.
     * @return New email to user.
     */
    String confirmResetEmail(String  confirmationToken) throws ResetEmailTokenNotFoundException;
}
