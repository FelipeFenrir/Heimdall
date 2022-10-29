package com.heimdall;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.util.TimeZone;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.FilterType;

@Slf4j
@NoArgsConstructor
@SpringBootApplication
//@ComponentScan(excludeFilters = {
//    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
//        MultiTenantConstants.class,
//        MultiTenantFilter.class,
//        TenantIdentifierResolver.class,
//        TokenMultiTenantFilter.class
//    })
//}, basePackages = {"br.com.fenrir"})
public class Application {

//    private static final String TIME_ZONE = "Etc/UTC";

//    @Autowired
//    private Environment environment;

    public static void main(String[] args) {
        log.info("Initializing Application Context.");
        SpringApplication.run(Application.class, args);
        log.info("Ready-to-use.");
    }

//    @PostConstruct
//    void started() {
//        TimeZone.setDefault(TimeZone.getTimeZone(TIME_ZONE));
//
//        EnvUtil.setServerPort(environment.getProperty("server.port"));
//        EnvUtil.setHttpPort(environment.getProperty("server.http.port"));
//        EnvUtil.setApplicationPath(environment.getProperty("server.servlet.context-path"));
//        EnvUtil.setHostAddress(InetAddress.getLoopbackAddress().getHostAddress());
//        EnvUtil.setHostName(InetAddress.getLoopbackAddress().getHostName());
//    }
}
