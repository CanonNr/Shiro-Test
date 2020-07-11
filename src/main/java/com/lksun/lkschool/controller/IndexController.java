package com.lksun.lkschool.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "这是首页";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String user(){
        return "这是test页";
    }
}
