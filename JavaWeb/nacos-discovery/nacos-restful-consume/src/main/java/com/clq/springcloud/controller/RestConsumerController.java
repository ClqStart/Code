package com.clq.springcloud.controller;

import com.clq.microservice.service1.api.Service1Api;
import com.clq.microservice.service2.service.Service2Api;
import org.apache.dubbo.config.ConfigCenterConfig;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/*
 *@author:C1q
 */
@RestController
public class RestConsumerController {

    @Autowired
    LoadBalancerClient loadBalancerClient;
    String serviceId = "nacos-restful-provider";

//    @Value("${provider.address}")
//    private String providerAddress;

    @GetMapping(value = "/service")
    public String service() {
        RestTemplate restTemplate = new RestTemplate();
//调用服务

        ServiceInstance serviceInstance = loadBalancerClient.choose(serviceId);
        URI uri = serviceInstance.getUri();
        String providerResult = restTemplate.getForObject(uri + "/service"
                , String.class);
        return "consumer invoke | " + providerResult;
    }


    @org.apache.dubbo.config.annotation.Reference
    private Service2Api service2Api;

    @GetMapping(value = "/service2")
    public String service2() {
//远程调用service2
        String providerResult = service2Api.dubboService2();
        return "consumer dubbo invoke | " + providerResult;
    }

    @Reference
    private Service1Api service1Api;

    @GetMapping(value = "/service3")
    public String service1() {
        String providerResult = service1Api.dubboService1();
        return "consumer dubbo invoke | " + providerResult;
    }
    //value 不具备动态刷新功能
    @Value("${common.name}")
    private String common_name;

    @GetMapping(value = "/configs")
    public String getvalue() {
        return common_name;
    }

    @Autowired
    ConfigurableApplicationContext configurableApplicationContext;

    @GetMapping(value = "/configUp")
    public  String  getvalues(){
        return  configurableApplicationContext.getEnvironment().getProperty("common.name");
    }


}