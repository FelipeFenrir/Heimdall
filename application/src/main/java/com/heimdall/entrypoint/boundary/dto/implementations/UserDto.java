/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.boundary.dto.implementations;

import com.heimdall.entrypoint.boundary.dto.IBaseDto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Email;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Schema(name = "User")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserDto extends RepresentationModel<UserDto> implements IBaseDto {

    private UUID id;
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
    private List<RoleDto> roleBoundaryMapper;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
