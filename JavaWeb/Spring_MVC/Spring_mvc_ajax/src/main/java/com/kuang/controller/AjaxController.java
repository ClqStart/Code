package com.kuang.controller;

/*
 *@author:C1q
 */

import com.kuang.dojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AjaxController {

    @RequestMapping("t1")
    public String test() {
        return "hello";
    }

    @RequestMapping("a1")
    public void a1(String name, HttpServletResponse response) throws IOException {
        if ("clq".equals(name)) {
            response.getWriter().print("true");
        } else {
            response.getWriter().print("false");
        }
    }

    @RequestMapping("/a2")
    public List<User> a2() {
        List<User> userList = new ArrayList<User>();

        userList.add(new User("大一", 1, "男"));
        userList.add(new User("大二", 1, "女"));
        userList.add(new User("大三", 1, "男"));
        return userList;
    }

    @RequestMapping("/a3")
    public  String a3(String name,String pwd){
        String msg = "";
        if(name !=null){
            if("admin".equals(name)){
                msg="ok";
            }else {
                msg = "用户名有误";
            }
        }
        if(pwd !=null){
            if("123456".equals(pwd)){
                msg ="ok";
            }else {
                msg ="密码有误";
            }
        }
        return msg;
    }
}
