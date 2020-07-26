package com.kuang.contriller;

/*
 *@author:C1q
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/a1")
    public  String  test(){
        System.out.println("TestController的==>test()执行了");
        return "ok";
    }
}
