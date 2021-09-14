/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.implementations;

import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.BaseBoundaryMapper;

import io.swagger.annotations.ApiModel;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.hateoas.RepresentationModel;


import javax.validation.constraints.Email;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *     User Boundary Mapper from delivery layer.
 *     This class use Builder Design Pattern with Lombok.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@ApiModel(value = "User")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class UserBoundaryMapper extends RepresentationModel<UserBoundaryMapper> implements BaseBoundaryMapper {

    private static final long serialVersionUID = 4539279048073767467L;

    private long id;
    private String username;
    @Email
    private String email;
    private String password;
    private String tenantid;
    private boolean enabled;
    private String confirmationToken;
    private boolean acceptTerm;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private List<RoleBoundaryMapper> roleBoundaryMapper;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
