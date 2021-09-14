/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package br.com.fenrir.auth.adapters.entrypoint.util;

import javax.servlet.http.HttpServletRequest;

import br.com.fenrir.auth.core.entity.domain.implementations.RequestProcessedResultsImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * Class to Help Capture Device Data.
 *
 * @author Felipe de Andrade Batista
 */
@Slf4j
public class UserDeviceUtils {

    private static final Integer DEFAULT_HTTP_PORT = 80;
    private static final Integer DEFAULT_HTTPS_PORT = 443;

    /**
     * Get {@link RequestProcessedResultsImpl} object.
     *
     * @param request {@link HttpServletRequest}
     * @return {@link RequestProcessedResultsImpl}
     */
    @SuppressWarnings({"UnusedAssignment", "IndexOfReplaceableByContains"})
    public static RequestProcessedResultsImpl details(HttpServletRequest request) {
        String forwardIp = request.getHeader("X-FORWARDED-FOR");
        String ip = forwardIp == null ? request.getRemoteAddr() : forwardIp;

        String userAgent = request.getHeader("User-Agent");
        UAgentInfo userAgentInfo = new UAgentInfo(request);
        String userAText = userAgentInfo.getUserAgent();

        String os = "";
        String browser = "";

        // log.info("User Agent for the request is===>" + browserDetails);
        // =================OS=======================
        if (userAgentInfo.detectWindows()) {
            os = "Windows";
        } else if (userAgentInfo.detectMac()) {
            os = "Mac";
        } else if (userAgentInfo.detectUnix()) {
            os = "Unix";
        } else if (userAgentInfo.detectAndroid()) {
            os = "Android";
        } else if (userAgentInfo.detectIphone()) {
            os = "IPhone";
        } else {
            os = "UnKnown, More-Info: " + userAgentInfo.getUserAgent();
        }
        // ===============Browser===========================
        if (userAgentInfo.detectMSIE()) {
            String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];

            browser = substring.split(" ")[0].replace("MSIE", "IE")
                    + "-"
                    + substring.split(" ")[1];
        } else if (userAgentInfo.detectSafari() && userAText.contains("version")) {
            browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0])
                    .split("/")[0] + "-"
                    + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0])
                            .split("/")[1];
        } else if (userAText.contains("opr") || userAgentInfo.detectOpera()) {
            if (userAgentInfo.detectOpera()) {
                browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0])
                        .split("/")[0] + "-"
                        + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0])
                                .split("/")[1];
            } else if (userAText.contains("opr")) {
                browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
                        .replace("OPR", "Opera");
            }
        } else if (userAgentInfo.detectChrome()) {
            browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0])
                    .replace("/", "-");
        } else if ((userAText.indexOf("mozilla/7.0") > -1)
                || (userAText.indexOf("netscape6") != -1)
                || (userAText.indexOf("mozilla/4.7") != -1)
                || (userAText.indexOf("mozilla/4.78") != -1)
                || (userAText.indexOf("mozilla/4.08") != -1)
                || (userAText.indexOf("mozilla/3") != -1)) {
            browser = "Netscape-?";

        } else if (userAgentInfo.detectFirefox()) {
            browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0])
                    .replace("/", "-");
        } else if (userAText.contains("rv")) {
            browser = "IE-" + userAText.substring(
                    userAText.indexOf("rv") + 3, userAText.indexOf(")"));
        } else {
            browser = "UnKnown, More-Info: " + userAgent;
        }

        // uri
        String uri = request.getScheme() + "://" + request.getServerName()
                + ("http".equals(request.getScheme()) && request.getServerPort() == DEFAULT_HTTP_PORT
                || "https".equals(request.getScheme()) && request.getServerPort() == DEFAULT_HTTPS_PORT ? ""
                : ":" + request.getServerPort())
                + request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");

        return new RequestProcessedResultsImpl(os, browser, ip, uri);
    }

    /**
     * Transform Data Request Details in String.
     *
     * @param request {@link HttpServletRequest}
     * @return Data Request Details.
     */
    public static String detailsAsString(HttpServletRequest request) {
        String r = "";
        RequestProcessedResultsImpl d = details(request);

        r = r + "OS" + " - " + d.getOperatingSystem() + " | " + "Browser" + " - " + d.getUri() + " | " + "IP address"
                + " - " + d.getIpAddress() + "  | " + "Browser" + " - " + d.getBrowserName() + " | ";

        return r;
    }
}
