package com.lksun.lkschool;

import com.lksun.lkschool.common.utils.JwtTokenUtil;
import com.lksun.lkschool.entity.Administrators;
import com.lksun.lkschool.service.AdministratorsService;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LkschoolApplicationTests {

    @Autowired
    AdministratorsService administratorsService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Test
    void create() {
        Administrators admin = administratorsService.getByUsername("admin");
        String jwt = jwtTokenUtil.createJWT(admin);
        System.out.println(jwt);


        Claims claims = jwtTokenUtil.parseJWT(jwt);
        System.out.println(claims.get("nickname"));
        System.out.println(claims);
    }



}
