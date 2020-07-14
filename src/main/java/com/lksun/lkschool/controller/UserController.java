package com.lksun.lkschool.controller;

import com.lksun.lkschool.common.api.CommonResult;
import com.lksun.lkschool.common.utils.JwtTokenUtil;
import com.lksun.lkschool.dto.AdminLoginParam;
import com.lksun.lkschool.entity.Administrators;
import com.lksun.lkschool.service.AdministratorsService;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class UserController {


    @Autowired
    AdministratorsService administratorsService;
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public CommonResult login(@RequestHeader HttpHeaders headers){
        SecurityUtils.getSubject();
        return CommonResult.success("测试");

    }
}
