package com.kuang.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 *@author:C1q
 */
@Configuration
public class ShiroConfig {
    //shiroFilterFactoryBean
    //DefaultWebSecurityManager
    //创建realm对象


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(defaultWebSecurityManager);
        /*
         anon:  无需认证直接访问
         authc: 必须认证才可以访问
         user:  拥有资源权限才可以访问
         role:  拥有角色才可以访问
         */

        Map<String, String> filterMap = new LinkedHashMap<>();

//        filterMap.put("/user/add","authc");
//        filterMap.put("/user/update","authc");



       filterMap.put("/user/update","perms[user:update]");
       filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/*", "authc");

        //将链传下去
        factoryBean.setFilterChainDefinitionMap(filterMap);
        factoryBean.setLoginUrl("/toLogin");

        factoryBean.setUnauthorizedUrl("/noauth");


        //设置管理器
        return factoryBean;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    //整合thymeleaf

    @Bean
    public ShiroDialect getShiroDialect(){
        return  new ShiroDialect();
    }


}
