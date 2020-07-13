package com.lksun.lkschool.config.shiro;

import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JWTFilter extends BasicHttpAuthenticationFilter {

    // 首先判断请求头有没有Authorization字段
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        return authorization != null;
    }



    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String url = WebUtils.toHttp(request).getRequestURI();

        if (this.isLoginRequest(request, response)) {
            return true;
        }
        boolean allowed = false;
        try {
            allowed = executeLogin(request, response);
        } catch (IllegalStateException e) {
            //not found any token
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return allowed || super.isPermissive(mappedValue);
    }



}
