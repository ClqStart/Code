package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@Controller
public class LoginController {

    @RequestMapping("/user/login")
    public  String  login(@PathParam("username") String username,
                          @PathParam("password") String password,
                          Model model,
                          HttpSession session){

        if(!StringUtils.isEmpty(username) && "123".equals(password)){
            session.setAttribute("loginUse",username);

            return "redirect:/main.html";
        }
        model.addAttribute("msg","用户名或者密码错误");
        return "index";
    }


    //推出登陆

    @GetMapping("/user/logout")
    public  String  logOut(HttpSession session){
        session.removeAttribute("loginUse");
        return "redirect:/index.html";

    }

}
