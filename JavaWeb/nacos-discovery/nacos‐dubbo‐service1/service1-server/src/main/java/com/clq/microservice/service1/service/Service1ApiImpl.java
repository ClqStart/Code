package com.clq.microservice.service1.service;

/*
 *@author:C1q
 */

import com.clq.microservice.service1.api.Service1Api;
import com.clq.microservice.service2.service.Service2Api;
import org.apache.dubbo.config.annotation.Reference;

@org.apache.dubbo.config.annotation.Service
public class Service1ApiImpl implements Service1Api {
    @Reference
    Service2Api service2Api;

    public String dubboService1() {
        String s = service2Api.dubboService2();
        return "dubboService1|" + s;
    }
}