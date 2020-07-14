package com.lksun.lkschool.config.shiro;

import com.lksun.lkschool.common.utils.JwtTokenUtil;
import com.lksun.lkschool.entity.Administrators;
import com.lksun.lkschool.service.AdministratorsService;
import io.jsonwebtoken.Claims;
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

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    // 执行授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }

    // 必须重写 不然会有
    // Realm [com.lksun.lkschool.config.shiro.UserRealm@71100e5b] does not support authentication token [com.lksun.lkschool.config.shiro.JWTToken@7f71918d].  Please ensure that the appropriate Realm implementation is configured correctly or that the realm accepts AuthenticationTokens of this type.
    // 的错误

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    // 执行认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        String token = (String) authcToken.getPrincipal();
        System.out.println("token:"+token);
        Claims claims = null;
        try{
            claims = jwtTokenUtil.parseJWT(token);
            System.out.println("claims:"+claims);
            if (claims != null){
                System.out.println("ok");
                return new SimpleAuthenticationInfo(claims,token,this.getName());
            }
        }catch (Exception e){
            return null;
        }
//        try{
//            String username = token.getUsername().trim();
//            String password = String.valueOf(token.getPassword());
//
//            Administrators administrator = administratorsService.getByUsername(username);
//
//            if (administrator != null){
//                // 加密方式: 首先每个用户有独自的盐   md5(md5(明文密码).盐)
//                String passwordMd5 = DigestUtils.md5DigestAsHex(password.getBytes());
//                String s = DigestUtils.md5DigestAsHex((passwordMd5 + administrator.getSalt()).getBytes());
//                if (administrator.getPassword().equals(s)){
//                    return new SimpleAuthenticationInfo(administrator,password,this.getName());
//                }
//            }
//        }catch (Exception e){
//            return null;
//        }

        return null;
//


    }
}
