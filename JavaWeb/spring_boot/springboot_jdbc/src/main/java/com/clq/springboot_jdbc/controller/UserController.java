package com.clq.springboot_jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/*
 *@author:C1q
 */
@RestController
public class UserController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping(value = "/userList")
    public List<Map<String,Object>> userList(){
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from rds_sys.sys_menu");
        return  list;
    }

}

