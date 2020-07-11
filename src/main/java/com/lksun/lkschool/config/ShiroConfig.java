package com.lksun.lkschool.config;

import com.lksun.lkschool.common.utils.UserRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultSecurityManager);

        // 设置过滤器
        HashMap<String, String> filterMap = new HashMap<>();
        filterMap.put("/login","anon");  // login页面允许未登录访问
        filterMap.put("/baba","authc");  // baba页面则不允许未登录访问
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

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
