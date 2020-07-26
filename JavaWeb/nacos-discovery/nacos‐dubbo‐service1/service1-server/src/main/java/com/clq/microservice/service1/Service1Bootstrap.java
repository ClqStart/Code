package com.clq.microservice.service1;

/*
 *@author:C1q
 */

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDubbo
@EnableDiscoveryClient
public class Service1Bootstrap{
    public static void main(String[] args) {
        SpringApplication.run(Service1Bootstrap.class, args);
    }
}
