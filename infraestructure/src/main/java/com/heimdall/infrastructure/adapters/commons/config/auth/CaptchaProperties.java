/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package com.heimdall.infrastructure.adapters.commons.config.auth;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 * CaptchaProperties class.
 * This class contain all properties for Recaptcha Component.
 * </p>
 *
 * # Injected properties in .yml
 * google:
 *    recaptcha:
 *       url: ${GOOGLE_RECAPTCHA_LINK:https://www.google.com/recaptcha/api/siteverify}
 *       key: ${GOOGLE_RECAPTCHA_KEY:fenrirtest}
 *       secret: ${GOOGLE_RECAPTCHA_SECRET:fenrirtest}
 *
 * @author Felipe de Andrade Batista
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "google.recaptcha")
public class CaptchaProperties {
    private String url;
    private String key;
    private String secret;
}
