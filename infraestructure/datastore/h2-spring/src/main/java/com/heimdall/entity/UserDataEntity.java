/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package com.heimdall.entity;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDataEntity extends BaseDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Email
    private String pendingEmail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String tenantid;

    @Column(nullable = false)
    private boolean enabled;

    private String confirmationToken;

    @Column(nullable = false)
    private boolean aceptTerm;

    private boolean accountNonLocked;

    private boolean accountNonExpired;

    private boolean credentialsNonExpired;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_users", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<RoleDataEntity> roleDataMappers;

    public boolean isExpired(long minutes) {
        var expire = super.getCreatedOn().plusMinutes(minutes);
        return LocalDateTime.now().isAfter(expire);
    }
}
