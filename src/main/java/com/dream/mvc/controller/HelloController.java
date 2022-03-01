package com.dream.mvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    // @RequestMapping 注解的作用就是将请求和处理请求的控制器方法关联起来，建立映射关系
    // springMVC接收到指定请求，就会来找到在映射关系中对应的控制器方法来处理这个请求
    // @RequestMapping标识一个类: 设置映射请求的请求路径的初始信息
    // @RequestMapping标识一个方法: 设置映射请求路径的具体信息
    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

    @RequestMapping("/target")
    public String toTargetPage(){
        return "target";
    }

    @RequestMapping(value = "/test",produces="text/html;charset=UTF-8")
    public String Test(HttpServletRequest request){
        request.setAttribute("info","abcd");
        return "test";
    }
}
