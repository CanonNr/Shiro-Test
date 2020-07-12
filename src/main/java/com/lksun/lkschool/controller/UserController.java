package com.lksun.lkschool.controller;

import com.lksun.lkschool.common.api.CommonResult;
import com.lksun.lkschool.common.utils.JwtTokenUtil;
import com.lksun.lkschool.dto.AdminLoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public CommonResult login(@RequestHeader HttpHeaders headers){
        String token = headers.get("authorization").get(0);

        if (!jwtTokenUtil.isVerify(token)){
            return CommonResult.validateFailed("登录已失效");
        }

        return null;

    }
}
