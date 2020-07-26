package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/*
 *@author:C1q
 */

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(HttpSession session, String username, String password) {
        session.setAttribute("userLogInfo", username);
        return "main";

    }
}
