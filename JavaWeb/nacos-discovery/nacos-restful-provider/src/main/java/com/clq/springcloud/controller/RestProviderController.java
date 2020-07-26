package com.clq.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *@author:C1q
 */
@RestController
public class RestProviderController {
    @GetMapping(value = "/service") //暴露服务
    public String service(){
        System.out.println("provider invoke");
        return "provider invoke";
    }
}
