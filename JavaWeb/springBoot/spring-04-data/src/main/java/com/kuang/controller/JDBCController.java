package com.kuang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/*
 *@author:C1q
 */
@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @GetMapping("/userList")
    public List<Map<String,Object>> userList(){
        String  sql = "select * from user";
        List<Map<String, Object>>List_maps = jdbcTemplate.queryForList(sql);
        return List_maps;
    }

    @GetMapping("/addUser")
    public  String  addUser(){
        String  sql = "insert into user(id,name,pwd) values (5,'clq','11110')";
        int update = jdbcTemplate.update(sql);
        if(update>0){
            return "ok";
        }
        return "update-fail";
    }

    @GetMapping("/updateUser/{id}")
    public  String  updateUser(@PathVariable("id") int id){
        String  sql = "update mybatis.user set name=?,pwd=? where id="+id;
        Object[] objects = new Object[2];
        objects[0]="小明";
        objects[1]="3333";
        int update = jdbcTemplate.update(sql,objects);
        if(update>0){
            return "updateUser_ok";
        }
        return "fail";
    }


    @GetMapping("/deleteUser/{id}")
    public  String  deleteUser(@PathVariable("id") int id){
        String  sql = "delete from mybatis.user where id=?";
        int update = jdbcTemplate.update(sql,id);
        if(update>0){
            return "ok";
        }
        return "fail";
    }





}
