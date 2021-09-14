/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.adapters.commons.config.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.lang.NonNull;
import org.springframework.security.access.annotation.Jsr250MethodSecurityMetadataSource;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

/**
 * This class ignore the ROLE_ prefix with Spring Security 4.
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class DefaultRolesPrefixPostProcessor implements BeanPostProcessor, PriorityOrdered {

    /**
     * Post processor of Bean executes after initialization.
     * @param bean Bean.
     * @param beanName Name of Bean.
     * @return Bean.
     * @throws BeansException Bean error Exception.
     */
    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName)
            throws BeansException {

        // remove this if you are not using JSR-250
        if (bean instanceof Jsr250MethodSecurityMetadataSource) {
            ((Jsr250MethodSecurityMetadataSource) bean).setDefaultRolePrefix(null);
        }

        if (bean instanceof DefaultMethodSecurityExpressionHandler) {
            ((DefaultMethodSecurityExpressionHandler) bean).setDefaultRolePrefix(null);
        }
        if (bean instanceof DefaultWebSecurityExpressionHandler) {
            ((DefaultWebSecurityExpressionHandler) bean).setDefaultRolePrefix(null);
        }
        if (bean instanceof SecurityContextHolderAwareRequestFilter) {
            ((SecurityContextHolderAwareRequestFilter) bean).setRolePrefix("");
        }
        return bean;
    }

    /**
     * Post processor of Bean executes before initialization.
     * @param bean Bean.
     * @param beanName Name of Bean.
     * @return Bean.
     * @throws BeansException Bean error Exception.
     */
    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull  String beanName)
            throws BeansException {
        return bean;
    }

    /**
     * Get priority order of Bean.
     * @return Priority.
     */
    @Override
    public int getOrder() {
        return PriorityOrdered.HIGHEST_PRECEDENCE;
    }
}