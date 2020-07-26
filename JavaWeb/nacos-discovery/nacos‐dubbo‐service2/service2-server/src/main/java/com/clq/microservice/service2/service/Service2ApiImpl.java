package com.clq.microservice.service2.service;

/*
 *@author:C1q
 */
@org.apache.dubbo.config.annotation.Service
public class Service2ApiImpl implements Service2Api {
    public String dubboService2() {
        return "dubboService2";
    }
}