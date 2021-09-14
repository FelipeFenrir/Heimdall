/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fenrir.auth.adapters.commons.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Config class to Eureka (Service Discovery) - Server.
 *
 * @author Felipe de Andrade Batista
 */
@Slf4j
@Configuration
@EnableDiscoveryClient
//@EnableFeignClients
//@EnableHystrix
//@ConditionalOnRibbonAndEurekaEnabled
@ConditionalOnProperty(name = "eureka.client.enabled", havingValue = "true")
public class EurekaConfig {

    {
        log.info("INFO: Initialyzing EurekaConfig.");
    }
}
