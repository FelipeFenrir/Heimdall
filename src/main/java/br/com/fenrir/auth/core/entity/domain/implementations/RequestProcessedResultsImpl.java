/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package br.com.fenrir.auth.core.entity.domain.implementations;

import br.com.fenrir.auth.core.entity.domain.RequestProcessedResults;

/**
 * <p>
 *     Request Results Domain.
 * </p>
 * @author Felipe de Andrade Batista
 */
public class RequestProcessedResultsImpl implements RequestProcessedResults {

    private String browserName;
    private String ipaddress;
    private String operatingSystem;
    private String uri;

    /**
     * <p>
     *      Construct Method.
     * </p>
     * @param operatingSystem Name of Operation System.
     * @param browserName Name of Browser (Internet Browser).
     * @param ipaddress IP Address of Request.
     * @param uri URL of Request.
     */
    public RequestProcessedResultsImpl(String operatingSystem, String browserName,
                                       String ipaddress, String uri) {
        super();
        this.operatingSystem = operatingSystem;
        this.browserName = browserName;
        this.ipaddress = ipaddress;
        this.uri = uri;
    }

    /**
     * Get Browser Name.
     * @return Name of Browser (Internet Browser).
     */
    public String getBrowserName() {
        return browserName;
    }

    /**
     * Get IP Address of Request.
     * @return IP (String).
     */
    public String getIpAddress() {
        return ipaddress;
    }

    /**
     * Get Operating System of Request.
     * @return Name of Operation System.
     */
    public String getOperatingSystem() {
        return operatingSystem;
    }

    /**
     * Get URI.
     * @return URI.
     */
    public String getUri() {
        return uri;
    }

    /**
     * Set Browser Name.
     * @param browserName Name of Browser (Internet Browser).
     */
    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    /**
     * Set IP Address of Request.
     * @param iPAddress IP Address of Request.
     */
    public void setIpAddress(String iPAddress) {
        this.ipaddress = iPAddress;
    }

    /**
     * Set Operating System of Request.
     * @param operatingSystem Name of Operation System.
     */
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    /**
     * Set URI.
     * @param uRI URL of Request.
     */
    public void setUri(String uRI) {
        this.uri = uRI;
    }
}
