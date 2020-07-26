package com.kuang.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kuang.pojo.User;
import com.kuang.utils.JsonUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 *@author:C1q
 */
//@Controller
@RestController  //表示不走视图解释器
public class JsonContraoller {

    @RequestMapping(value = "/json1",produces = "application/json;charset=utf-8")
//    @ResponseBody //它就不会走视图解释器，会直接返回一个字符串
    public  String Json() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        User user = new User(3, "陈", 18);
        String str = mapper.writeValueAsString(user);

        return str;
    }
    @RequestMapping("/j2")
//    @ResponseBody //它就不会走视图解释器，会直接返回一个字符串
    public  String Json1() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        User user = new User(3, "陈", 18);
        String str = mapper.writeValueAsString(user);

        return str;
    }
    @RequestMapping("/j3")
    public  String Json2() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<User> list = new ArrayList<User>();
        User user1 = new User(1, "陈1", 11);
        User user2 = new User(2, "陈2", 12);
        User user3 = new User(3, "陈3", 13);
        User user4 = new User(4, "陈4", 14);
        User user5 = new User(5, "陈5", 15);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);

        String str = mapper.writeValueAsString(list);

        return str;
    }
    @RequestMapping("/j8")
    public  String Json7() throws JsonProcessingException {
        List<User> list = new ArrayList<User>();
        User user1 = new User(1, "陈1", 11);
        User user2 = new User(2, "陈2", 12);
        User user3 = new User(3, "陈3", 13);
        User user4 = new User(4, "陈4", 14);
        User user5 = new User(5, "陈5", 15);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);

       return JsonUtils.getJson(list);
    }

    @RequestMapping("/j4")
    public  String Json3() throws JsonProcessingException {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return  new ObjectMapper().writeValueAsString(ft.format(date));
    }


    @RequestMapping("/j5")
    public  String Json4() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(ft);

        return  mapper.writeValueAsString(date);
    }
       @RequestMapping("/j6")
    public  String Json5() throws JsonProcessingException {
        Date date = new Date();
        return JsonUtils.getJson(date);
    }

    @RequestMapping("/j7")
    public  String Json6() throws JsonProcessingException {
        User user = new User(3, "陈", 18);
        return JsonUtils.getJson(user);
    }




}
