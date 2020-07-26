package com.kuang.controller;
/*
 *@author:C1q
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class control {

    @RequestMapping("/h1")
    public String hello(Model model){

        model.addAttribute("msg","hello Springmvc");

        return "test";
    }
    //原来的分格：http://localhost:8080/add?a=1&b=2
    //RestFul风格：

    @RequestMapping("/add")
    public  String  test1(int a,int b, Model model){

        int res = a + b;
        model.addAttribute("msg","结果为："+ res);

        return "test";
    }
    @RequestMapping("/add1/{a}/{b}")
    public  String  test2(@PathVariable int a, @PathVariable int b, Model model){

        int res = a + b;
        model.addAttribute("msg","结果为："+ res);

        return "test";
    }
    @RequestMapping( value = "/add2/{a}/{b}",method = RequestMethod.GET)
//    @RequestMapping( path = "/add2/{a}/{b}",method = RequestMethod.GET)
//    @GetMapping("/add2/{a}/{b}")
    public  String  test3(@PathVariable int a, @PathVariable int b, Model model){

        int res = a + b;
        model.addAttribute("msg","结果为："+ res);

        return "test";
    }
//    @RequestMapping( value = "/add/{a}/{b}",method = RequestMethod.POST)
    @PostMapping("/add/{a}/{b}")
    public  String  test4(@PathVariable int a, @PathVariable String b, Model model){

        String res = a + b;
        model.addAttribute("msg","结果为："+ res);

        return "test";
    }
    @GetMapping("/add/{a}/{b}")
    public  String  test5(@PathVariable int a, @PathVariable String b, Model model){

        String res = a + b;
        model.addAttribute("msg","结果为："+ res);

        return "test";
    }


}
