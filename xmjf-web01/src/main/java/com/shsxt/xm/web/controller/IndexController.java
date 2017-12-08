package com.shsxt.xm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    //返回主试图
    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());
        return "index";
    }
    //返回主试图
    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());
        return "login";
    }
    //返回主试图
    @RequestMapping("/register")
    public String register(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());
        return "register";
    }
}
