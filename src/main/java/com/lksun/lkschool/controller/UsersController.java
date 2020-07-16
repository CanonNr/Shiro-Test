package com.lksun.lkschool.controller;

import com.lksun.lkschool.common.api.CommonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UsersController {

    @RequestMapping(value = "",method = RequestMethod.GET)
    @RequiresRoles("baba")
    @RequiresPermissions("baba")
    public CommonResult<Object> getUser(){
        return CommonResult.success("baba");
    }
}
