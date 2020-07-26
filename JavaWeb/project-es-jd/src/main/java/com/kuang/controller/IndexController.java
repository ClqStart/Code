package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *@author:C1q
 */

@Controller
public class IndexController {

    @GetMapping({"/","/index"})
    public  String  index(){
        return "index";
    }
}
