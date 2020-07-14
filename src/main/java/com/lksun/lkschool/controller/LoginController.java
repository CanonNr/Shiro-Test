package com.lksun.lkschool.controller;

import com.lksun.lkschool.common.api.CommonResult;
import com.lksun.lkschool.common.utils.JwtTokenUtil;
import com.lksun.lkschool.dto.AdminLoginParam;
import com.lksun.lkschool.entity.Administrators;
import com.lksun.lkschool.service.AdministratorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    AdministratorsService administratorsService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public CommonResult login(@RequestBody AdminLoginParam adminLoginParam){
        String username = adminLoginParam.getUsername();
        String password = adminLoginParam.getPassword();
        Administrators administrator = administratorsService.getByUsername(username);
        if (administrator != null){
            // 加密方式: 首先每个用户有独自的盐   md5(md5(明文密码).盐)
            String passwordMd5 = DigestUtils.md5DigestAsHex(password.getBytes());
            String s = DigestUtils.md5DigestAsHex((passwordMd5 + administrator.getSalt()).getBytes());
            if (administrator.getPassword().equals(s)){
                String jwt = jwtTokenUtil.createJWT(administrator);
                return CommonResult.success(jwt);
            }
        }
        return CommonResult.validateFailed("用户名或密码错误");
    }
}
