/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.implementations;

import io.swagger.annotations.ApiModel;

import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.EmailContentBoundary;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.hateoas.RepresentationModel;


import java.util.Map;

/**
 * <p>
 *     Email Content Boundary Mapper from delivery layer.
 *     This class use Builder Design Pattern with Lombok.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@ApiModel(value = "EmailContent")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class EmailContentBoundaryMapper extends RepresentationModel<EmailContentBoundaryMapper> implements
        EmailContentBoundary {

    private static final long serialVersionUID = 1583598780567470916L;

    private String template;
    private Map<String, Object> content;
}
