package com.lksun.lkschool.config;

import com.lksun.lkschool.common.utils.realm.UserRealm;
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

        // 过滤器
        HashMap<String, String> filterMap = new HashMap<>();
        filterMap.put("/login","anon");
        filterMap.put("/baba","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
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
