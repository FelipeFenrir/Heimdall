/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.entity.domain;
/**
 * <p>
 *     Request Results Domain.
 * </p>
 * <p>
 *     This class implements Proxy Design Pattern.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
public interface RequestProcessedResults {
    /**
     * Get Browser Name.
     * @return Name of Browser (Internet Browser).
     */
    String getBrowserName();

    /**
     * Get IP Address of Request.
     * @return IP (String).
     */
    String getIpAddress();

    /**
     * Get Operating System of Request.
     * @return Name of Operation System.
     */
    String getOperatingSystem();

    /**
     * Get URI.
     * @return URI.
     */
    String getUri();
}
