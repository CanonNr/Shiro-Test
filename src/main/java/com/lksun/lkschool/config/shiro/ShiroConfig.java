package com.lksun.lkschool.config.shiro;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Autowired
    UserRealm userRealm;
    @Autowired
    DefaultSecurityManager defaultSecurityManager;


    // 创建 ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(){
        // 实例化一个对象
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultSecurityManager);

        // 自定义过滤器
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        // 设置路由过滤规则
        HashMap<String, String> filterRuleMap  = new HashMap<>();
        // 所有路由都要经过JWT的过滤
        filterRuleMap.put("/**","jwt");
        // 登录相关的则跳过
        filterRuleMap .put("/api/v1/login/**","anon");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterRuleMap );

        // 配置未登录跳转页
        shiroFilterFactoryBean.setLoginUrl("/login");
        return shiroFilterFactoryBean;
    }


    // 创建 ShiroWebSecurityManager
    @Bean(name = "defaultSecurityManager")
    public DefaultWebSecurityManager setDefaultSecurityManager(){
        // 注意不要new错了 DefaultWebSecurityManager 、 DefaultSecurityManager 傻傻分不清楚
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        // 设置域
        defaultSecurityManager.setRealm(userRealm);
        return defaultSecurityManager;
    }

    // 创建 Realm
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

}
