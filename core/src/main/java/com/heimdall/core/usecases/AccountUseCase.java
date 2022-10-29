/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.core.usecases;

import com.heimdall.core.domains.model.ResetEmailToken;
import com.heimdall.core.domains.model.ResetPasswordToken;
import com.heimdall.core.domains.model.Role;
import com.heimdall.core.domains.model.User;
import com.heimdall.core.domains.factory.implementations.ResetEmailTokenFactoryImpl;
import com.heimdall.core.domains.factory.implementations.ResetPasswordTokenFactoryImpl;
import com.heimdall.core.domains.factory.implementations.UserFactoryImpl;
import com.heimdall.ports.command.AccountCommand;
import com.heimdall.core.domains.enumerators.EnumMasterScopes;
import com.heimdall.core.exceptions.ResetEmailTokenNotFoundException;
import com.heimdall.core.exceptions.ResetPassTokenNotFoundException;
import com.heimdall.core.exceptions.UserAlreadyExistException;
import com.heimdall.core.exceptions.UserNotEnabledException;
import com.heimdall.core.exceptions.UserNotFoundException;
import com.heimdall.ports.datastore.ResetEmailTokenGateway;
import com.heimdall.ports.datastore.ResetPasswordTokenGateway;
import com.heimdall.ports.datastore.RoleGateway;
import com.heimdall.ports.datastore.UserGateway;

import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.Optional;

/**
 * <p>
 *     Account management use case implementation.
 *     This class implements the Facade Design Pattern.
 * </p>
 * @author Felipe de Andrade Batista
 */
@AllArgsConstructor
public class AccountUseCase implements AccountCommand {

    private final UserGateway userGateway;
    private final RoleGateway roleGateway;
    private final ResetPasswordTokenGateway resetPasswordTokenGateway;
    private final ResetEmailTokenGateway resetEmailTokenGateway;

    @Override
    public void registerUserByEmail(String registrationEmail) throws UserAlreadyExistException {
        //log.debug("Registering new User with e-mail: {}", registrationEmail);
        Optional<User> loaded = userGateway.findByEmail(registrationEmail);
        Role defaultAdminRole = null;

        if (loaded.isPresent()) {
            // if yes, use existing User instead.
            throw new UserAlreadyExistException("User already exist");
        } else {
            // New User with ADMIN Role.
            Optional<Role> adminrole = roleGateway.findByName(EnumMasterScopes.ROLE_ADMIN.getNameOfRule());
            if (adminrole.isPresent()) {
                defaultAdminRole = adminrole.get();
            }
        }

        final User newUser = new UserFactoryImpl().create(
                registrationEmail,
                Collections.singletonList(defaultAdminRole));

        userGateway.save(newUser);
    }

    @Override
    public void confirmUserByEmail(String confirmationToken, String password) throws UserNotFoundException {
        //log.debug("Confirming User with token {}", confirmationToken);

        // Find the User associated with the reset token
        Optional<User> user = userGateway.findByConfirmationToken(confirmationToken);
        if (user.isEmpty()) {
            throw new UserNotFoundException("No User found for token");
        }

        final User userConfirmed = new UserFactoryImpl().create(
                user.get().getId(),
                user.get().getUsername(),
                user.get().getEmail(),
                // Set new password
                password,
                user.get().getTenantid(),
                // Set User to enabled
                null,
                true,
                true,
                false,
                false,
                false,
                user.get().getRole()
        );

        userGateway.save(userConfirmed);
    }

    /*
    TODO: Check the use of User Object in this method.
    TODO: Return the token.
     */
    @Override
    public void createResetPasswordToken(User user, long expiryMinutesResetPassToken)
            throws UserNotFoundException, UserNotEnabledException {
        //log.debug("Resetting password for User: {}", user.getEmail());

        Optional<User> optionalUser = userGateway.findByEmail(user.getEmail());
        if (optionalUser.isEmpty()) {
            //log.debug("User not found.");
            throw new UserNotFoundException("User not found.");
        }

        // invalidate current password
        user = optionalUser.get();
        if(!user.isEnabled()) {
            //log.debug("User not enabled.");
            throw new UserNotEnabledException("User not enabled.");
        }

        // Generate random 36-character string token for reset password link
        final ResetPasswordToken token = new ResetPasswordTokenFactoryImpl().create(expiryMinutesResetPassToken, user);
        resetPasswordTokenGateway.save(token);
    }

    @Override
    public void confirmResetPass(String resetPasswordToken, String newPassword)
            throws ResetPassTokenNotFoundException {
        //log.debug("Confirming reset pass with token: {}", resetPasswordToken);

        // Find the Reset Pass Token associated with the User
        Optional<ResetPasswordToken> optionalToken = resetPasswordTokenGateway.findByToken(resetPasswordToken);

        if (optionalToken.isEmpty() || optionalToken.get().isExpired()) {
            throw new ResetPassTokenNotFoundException("Not found reset password token.");
        }

        ResetPasswordToken resetPassToken = optionalToken.get();

        final User user = new UserFactoryImpl().create(
                resetPassToken.getUser().getId(),
                resetPassToken.getUser().getUsername(),
                resetPassToken.getUser().getEmail(),
                // Set new password
                newPassword,
                resetPassToken.getUser().getTenantid(),
                resetPassToken.getUser().getConfirmationToken(),
                resetPassToken.getUser().isEnabled(),
                resetPassToken.getUser().isAcceptTerm(),
                resetPassToken.getUser().isAccountNonLocked(),
                resetPassToken.getUser().isAccountNonExpired(),
                resetPassToken.getUser().isCredentialsNonExpired(),
                resetPassToken.getUser().getRole()
        );

        // Save User
        userGateway.save(user);

        //Delete Reset Password Token
        resetPasswordTokenGateway.delete(resetPassToken);
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        //log.debug("Changing password for User: {}", username);

        Optional<User> optionalUser = userGateway.findByEmail(username);
        if (optionalUser.isEmpty()) {
            //log.debug("Cannot find {} in User domain repository.", username);
            return false;
        }

        User user = optionalUser.get();
        boolean passwordMatch = oldPassword.equals(user.getPassword());

        //log.debug("Entered password matches the old password: {}", passwordMatch);
        if (passwordMatch) {
            user = new UserFactoryImpl().create(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    // Set new password
                    newPassword,
                    user.getTenantid(),
                    user.getConfirmationToken(),
                    user.isEnabled(),
                    user.isAcceptTerm(),
                    user.isAccountNonLocked(),
                    user.isAccountNonExpired(),
                    user.isCredentialsNonExpired(),
                    user.getRole()
            );
            userGateway.save(user);
            return true;
        }

        // Password entered does not match the old password
        return false;
    }

    @Override
    public boolean createResetEmailToken(String username, String password, String newEmail,
                                         long expiryMinutesResetEmailToken) {
        //log.debug("Changing e-mail for User: {}", username);

        final Optional<User> optionalUser = userGateway.findByEmail(username);
        if (optionalUser.isEmpty() || !optionalUser.get().isEnabled()) {
            //log.debug("Cannot find {} in User domain, or this user is not enabled.", username);
            return false;
        }

        if (userGateway.findByEmail(newEmail).isPresent()) {
            //log.debug("User with email {} already exists.", newEmail);
            return false;
        }

        final User user = optionalUser.get();
        final boolean passwordMatch = password.equals(user.getPassword());

        //log.debug("Entered password matches the User password: {}", passwordMatch);
        if (passwordMatch) {
            // Generate random 36-character string token for reset password link
            final ResetEmailToken token = new ResetEmailTokenFactoryImpl()
                    .create(newEmail, expiryMinutesResetEmailToken, user);
            resetEmailTokenGateway.save(token);
            return true;
        }

        // Password entered does not match the user password
        return false;
    }

    @Override
    public String confirmResetEmail(String  confirmationToken) throws ResetEmailTokenNotFoundException {
        //log.debug("Confirming reset email with token: {}", confirmationToken);

        // Find the Reset Email Token associated with the User
        Optional<ResetEmailToken> optionalToken = resetEmailTokenGateway.findByToken(confirmationToken);

        if (optionalToken.isEmpty()) {
            throw new ResetEmailTokenNotFoundException("Not found reset email token.");
        }

        final ResetEmailToken resetEmailToken = optionalToken.get();
        final User user = new UserFactoryImpl().create(
                resetEmailToken.getUser().getId(),
                resetEmailToken.getUser().getUsername(),
                // Set new email
                resetEmailToken.getPendingEmail(),
                resetEmailToken.getUser().getPassword(),
                resetEmailToken.getUser().getTenantid(),
                resetEmailToken.getUser().getConfirmationToken(),
                resetEmailToken.getUser().isEnabled(),
                resetEmailToken.getUser().isAcceptTerm(),
                resetEmailToken.getUser().isAccountNonLocked(),
                resetEmailToken.getUser().isAccountNonExpired(),
                resetEmailToken.getUser().isCredentialsNonExpired(),
                resetEmailToken.getUser().getRole()
        );

        // Save User
        userGateway.save(user);
        // Save userDataMapper
        resetEmailTokenGateway.delete(resetEmailToken);

        return resetEmailToken.getPendingEmail();
    }
}
