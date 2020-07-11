package com.lksun.lkschool.controller;

import com.lksun.lkschool.common.api.CommonResult;
import com.lksun.lkschool.dto.AdminLoginParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;


@RestController
public class AccountController {
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public CommonResult login(AdminLoginParam adminLoginParam){
        System.out.println(adminLoginParam);
        // 获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(adminLoginParam.getUsername(), adminLoginParam.getPassword());
        // 登录
        try {
            subject.login(token);
        } catch (Exception e){
            System.out.println("登录失败,账号或密码错误");
            return CommonResult.validateFailed("用户名或密码错误");
        }

        return CommonResult.success(adminLoginParam);
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(){
        return "这是注册页";
    }
}
