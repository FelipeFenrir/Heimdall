/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.boundary.dto.implementations;

import com.heimdall.entrypoint.boundary.dto.IBaseDto;

//import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//@Schema(name = "Role")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class RoleDto extends RepresentationModel<RoleDto> implements IBaseDto {

    private UUID id;
    private String name;
    private List<PermissionDto> permissions;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
