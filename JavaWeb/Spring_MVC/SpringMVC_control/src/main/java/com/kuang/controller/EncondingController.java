package com.kuang.controller;
/*
 *@author:C1q
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EncondingController {

    @PostMapping("/e/t1")
    public String test1(String name ,Model model){
        model.addAttribute("msg",name);
        System.out.println(name);
        return "test";
    }
}
