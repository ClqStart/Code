package com.kuang.controller;
/*
 *@author:C1q
 */

import com.kuang.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class controller1 {

    @GetMapping("/h1")
    public String  test(@RequestParam("name") String name, Model model){
        System.out.println("接收前端的参数"+name);
        model.addAttribute("msg",name);

        return "test";
    }
    @GetMapping("/h12")
    public String  tes2(User user, Model model){
        System.out.println(user);

        return "test";
    }
}
