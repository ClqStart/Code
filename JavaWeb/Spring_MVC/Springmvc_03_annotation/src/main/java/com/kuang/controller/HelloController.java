package com.kuang.controller;
/*
 *@author:C1q
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("h1")
    public String hello(Model model) {
        model.addAttribute("msg", "hello,SpringMVCAnnotation"); //分装数据
        return "hello"; //会被视图解释器处理；
    }
}
