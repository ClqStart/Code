package com.clq.springcloud;

/*
 *@author:C1q
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringRestConsumerBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(SpringRestConsumerBootstrap.class, args);
    }
}
