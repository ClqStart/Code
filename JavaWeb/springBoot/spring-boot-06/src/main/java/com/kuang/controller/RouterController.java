package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *@author:C1q
 */
@Controller
public class RouterController {


    @RequestMapping("/tologin")
    public  String  toLogin(){
        return "login";
    }

    @RequestMapping("/welcome")
    public  String  welcome(){
        return "index";
    }
}
