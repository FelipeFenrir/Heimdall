/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.implementations;

import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.EmailBoundary;
import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.EmailContentBoundary;

import io.swagger.annotations.ApiModel;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.hateoas.RepresentationModel;

/**
 * <p>
 *     Email Boundary Mapper from delivery layer.
 *     This class use Builder Design Pattern with Lombok.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@ApiModel(value = "Email")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class EmailBoundaryMapper extends RepresentationModel<EmailBoundaryMapper> implements EmailBoundary {

    private static final long serialVersionUID = 4917355201896819571L;

    private String to;
    private String from;
    private String subject;
    private EmailContentBoundary emailContent;
}
