package com.kuang.contriller;

/*
 *@author:C1q
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class logincontroller {

    @RequestMapping("/main")
    public  String main(){
        return "main";
    }
    @RequestMapping("/goLogin")
    public  String login(){
        return "login";
    }

    @RequestMapping("/login")
    public String  login(HttpSession session, String username, String password, Model model){
        if(!(username.equals("clq") && password.equals("123456"))){
            return "login";
        }
        session.setAttribute("userLoginInfo",username);
        model.addAttribute("username",username);
        return "main";
    }

    @RequestMapping("/goOut")
    public String  goOut(HttpSession session){
       session.removeAttribute("userLoginInfo");
        return "login";
    }

}
