package com.kuang.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/*
 *@author:C1q
 */
@Controller
public class Mycontroller {

    @RequestMapping({"/", "/index"})
    public String toIndex(Model model) {

        model.addAttribute("msg", "hello,Shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add() {
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update() {
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model) {
        Subject currentUser = SecurityUtils.getSubject();


        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            currentUser.login(token);
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg","密码错误");
            return  "login";
        }
    }

    @RequestMapping("/noauth")
    @ResponseBody
    public  String  unauthorized(){
        return "未经授权不允许访问";
    }

    @RequestMapping("/logOut")
    public  String  logOut(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "login";
    }
}