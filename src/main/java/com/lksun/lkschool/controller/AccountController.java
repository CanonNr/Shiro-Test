package com.lksun.lkschool.controller;

import com.lksun.lkschool.common.api.CommonResult;
import com.lksun.lkschool.config.shiro.UserRealm;
import com.lksun.lkschool.entity.Administrators;
import com.lksun.lkschool.service.AdministratorsService;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    AdministratorsService administratorsService;

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public CommonResult<Object> info(@RequestHeader HttpHeaders headers){
        Claims claims = (Claims)SecurityUtils.getSubject().getPrincipal();
        Integer user_id = (Integer) claims.get("id");
        Administrators administrator = administratorsService.getById(user_id);
        return CommonResult.success(administrator);
    }
}
