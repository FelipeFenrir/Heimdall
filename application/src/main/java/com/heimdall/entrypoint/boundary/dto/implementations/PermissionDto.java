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

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 *     Permission Boundary Mapper from delivery layer.
 *     This class use Builder Design Pattern with Lombok.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Schema(name = "Permission")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class PermissionDto extends RepresentationModel<PermissionDto> implements IBaseDto {

    private UUID id;
    private String name;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
