package com.lksun.lkschool.controller;

import com.lksun.lkschool.common.api.CommonResult;
import com.lksun.lkschool.common.utils.JwtTokenUtil;
import com.lksun.lkschool.dto.AdminLoginParam;
import com.lksun.lkschool.entity.Administrators;
import com.lksun.lkschool.service.AdministratorsService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class UserController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    AdministratorsService administratorsService;
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public CommonResult login(@RequestHeader HttpHeaders headers){
        String token = headers.get("authorization").get(0);

        if (!jwtTokenUtil.isVerify(token)){
            return CommonResult.validateFailed("登录已失效");
        }

        Claims claims = jwtTokenUtil.parseJWT(token);
        Integer id = (Integer) claims.get("id");
        Administrators administrators = administratorsService.getById(id);
        return CommonResult.success(administrators);

    }
}
