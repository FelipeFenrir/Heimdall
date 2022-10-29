/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entrypoint.boundary.dto.implementations;

import com.heimdall.entrypoint.boundary.dto.IEmailContentDto;

//import io.swagger.annotations.ApiModel;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.util.HashMap;
import java.util.Map;

//@ApiModel(value = "EmailContent")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class RegisterEmailContentDto extends RepresentationModel<RegisterEmailContentDto> implements IEmailContentDto {

    private static final String TEMPLATE = "USER_REGISTRATION";

    private String title;
    private String message;
    private String link;
    private String template;

    @Override
    public String getTemplate() {
        return TEMPLATE;
    }

    @Override
    public Map<String, Object> getContent() {
        final HashMap<String, Object> content = new HashMap<>();
        content.put("title", title);
        content.put("message", message);
        content.put("link", link);
        return content;
    }
}
