package com.lksun.lkschool.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "这是登录页";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(){
        return "这是注册页";
    }
}
