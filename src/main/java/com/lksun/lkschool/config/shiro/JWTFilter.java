package com.lksun.lkschool.config.shiro;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends BasicHttpAuthenticationFilter {

    // 首先判断请求头有没有Authorization字段
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        return authorization != null;
    }


    /**
     *
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        // 获取到header
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Authorization");
        // 将token封装到JWTToken
        JWTToken token = new JWTToken(authorization);
        // 如果没有抛出异常则代表登入成功，返回true
        getSubject(request, response).login(token);
        return true;
    }


    // 如果isAccessAllowed返回true则onAccessDenied方法不会继续执行
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        String url = WebUtils.toHttp(request).getRequestURI();
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
            }catch(Exception e) {
//                System.out.println(e.getMessage());
            }
        }
        return true;



    }

    /**
     * 将非法请求跳转到 /401
     */
    private void response401(ServletRequest req, ServletResponse resp) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            httpServletResponse.sendRedirect("/401");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }





}
