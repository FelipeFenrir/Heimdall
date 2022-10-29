/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.boundary.dto.implementations;

import com.heimdall.entrypoint.boundary.dto.IEmailContentDto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.springframework.hateoas.RepresentationModel;

import java.util.Map;

@Schema(name = "EmailContent")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class EmailContentDto extends RepresentationModel<EmailContentDto> implements IEmailContentDto {

    private String template;
    private Map<String, Object> content;
}
