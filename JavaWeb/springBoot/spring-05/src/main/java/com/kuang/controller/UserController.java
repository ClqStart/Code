package com.kuang.controller;

/*
 *@author:C1q
 */

import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;


    @RequestMapping("/queryUser")
    public List<User> queryUser(){
        List<User> users = userMapper.queryUser();
        for (User user : users) {
            System.out.println(user);
        }

        return  users;
    }

    //根据id选择用户
    @GetMapping("/selectUserById")
    public String selectUserById(){
        User user = userMapper.queryUserById(1);
        System.out.println(user);
        return "ok";
    }
    //添加一个用户
    @GetMapping("/addUser")
    public String addUser(){
        userMapper.addUser(new User(5,"阿毛","456789"));
        return "ok";
    }
    //修改一个用户
    @GetMapping("/updateUser")
    public String updateUser(){
        userMapper.UpdateUser(new User(5,"阿毛","421319"));
        return "ok";
    }
    //根据id删除用户
    @GetMapping("/deleteUser")
    public String deleteUser(){
        userMapper.deleteUser(5);
        return "ok";
    }
}
