package com.lksun.lkschool.config.shiro;

import com.lksun.lkschool.common.utils.JwtTokenUtil;
import com.lksun.lkschool.service.AdministratorsService;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

// 自定义一个 Realm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    AdministratorsService administratorsService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    // 执行授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("baba");
        return info;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    // 执行认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        // 获取到Token值
        String token = (String) authcToken.getPrincipal();
        Claims claims = null;

        try{
            claims = jwtTokenUtil.parseJWT(token);
            if (claims != null){
                return new SimpleAuthenticationInfo(claims,token,this.getName());
            }
        }catch (Exception e){
            return null;
        }

        return null;

    }
}
