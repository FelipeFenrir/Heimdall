/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.implementations;

import br.com.fenrir.auth.adapters.entrypoint.boundary.mapper.EmailContentBoundary;

//import io.swagger.annotations.ApiModel;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.hateoas.RepresentationModel;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *     Email Content Boundary Mapper from delivery layer.
 *     This class use Builder Design Pattern with Lombok.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
//@ApiModel(value = "EmailContent")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterEmailContentBoundaryMapper extends RepresentationModel<RegisterEmailContentBoundaryMapper>
        implements EmailContentBoundary {

    private static final long serialVersionUID = 1583598780567470916L;

    private static final String TEMPLATE = "USER_REGISTRATION";

    private String title;
    private String message;
    private String link;
    private String template;

    public void setTitle(String title) {
        this.title = title;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setLink(String link) {
        this.link = link;
    }

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
