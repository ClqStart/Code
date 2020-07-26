package com.clq.controller;

/*
 *@author:C1q
 */

import com.clq.dao.SysUserRepository;
import com.clq.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Controller
public class SysUserController {

    @Autowired
    SysUserRepository sysUserRepository;

    @RequestMapping("/user_list")
    public   String  userList(Map<String,Object> map ){
        List<SysUser> list = sysUserRepository.findAll();
        map.put("users",list);

        return "user_list";
    }
}
