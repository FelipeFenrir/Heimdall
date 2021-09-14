/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.api;

import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface UserApi {
    ResponseEntity<?> getAllUsers();
    ResponseEntity<?> getUser(Long id);
    ResponseEntity<?> lockoutAccount(Long id);
    ResponseEntity<?> deleteAccount(Long id);
    ResponseEntity<?> getMe(Principal principal);
    ResponseEntity<?> deleteMe(Principal principal);
}
