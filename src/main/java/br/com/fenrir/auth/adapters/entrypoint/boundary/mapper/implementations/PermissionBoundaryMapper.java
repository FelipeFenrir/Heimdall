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


import java.time.LocalDateTime;

/**
 * <p>
 *     Permission Boundary Mapper from delivery layer.
 *     This class use Builder Design Pattern with Lombok.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@ApiModel(value = "Permission")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionBoundaryMapper extends RepresentationModel<PermissionBoundaryMapper> implements
        BaseBoundaryMapper {

    private static final long serialVersionUID = -1242580839506147850L;

    private long id;
    private String name;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
