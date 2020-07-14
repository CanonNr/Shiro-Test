package com.lksun.lkschool.controller;

import com.lksun.lkschool.common.api.CommonResult;
import com.lksun.lkschool.service.AdministratorsService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class UserController {


    @Autowired
    AdministratorsService administratorsService;

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public CommonResult info(@RequestHeader HttpHeaders headers){
        Subject subject = SecurityUtils.getSubject();
        System.out.println("----------------------------");
        System.out.println(subject.getPrincipal());
        System.out.println(subject.isAuthenticated());
        System.out.println("----------------------------");

        return CommonResult.success(123);

    }
}
