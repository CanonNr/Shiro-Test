package com.lksun.lkschool.common.utils;

import com.lksun.lkschool.entity.Administrators;
import com.lksun.lkschool.service.AdministratorsService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

// 自定义一个 Realm
public class UserRealm extends AuthorizingRealm {
    @Autowired
    AdministratorsService administratorsService;

    // 执行授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }

    // 执行认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
//        String username = (String) authcToken.getPrincipal();
//        System.out.println(username);

//        UsernamePasswordToken user = (UsernamePasswordToken) authcToken;
//        System.out.println("用户名：" + user.getUsername());
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        try{
            String username = token.getUsername().trim();
            String password = String.valueOf(token.getPassword());

            Administrators administrator = administratorsService.getByUsername(username);

            if (administrator != null){
                // 加密方式: 首先每个用户有独自的盐   md5(md5(明文密码).盐)
                String passwordMd5 = DigestUtils.md5DigestAsHex(password.getBytes());
                String s = DigestUtils.md5DigestAsHex((passwordMd5 + administrator.getSalt()).getBytes());
                if (administrator.getPassword().equals(s)){
                    return new SimpleAuthenticationInfo(administrator,password,this.getName());
                }
            }
        }catch (Exception e){
            return null;
        }

        return null;



    }
}
