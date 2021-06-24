package com.xxc.config;

import com.xxc.authentication.JWTFilter;
import com.xxc.realm.BackManagerRealm;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;

/**
 * @author xxc
 * @date 2020/7/28 - 10:53
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        bean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        /*
        anon:无需认证就可以访问
        authc：必须认证了才能访问
        user：必须拥有了记住我功能才能用
        perms：拥有对某个资源的权限才能访问
        role：拥有某个角色权限才能访问
         */
        // 在 Shiro过滤器链上加入 JWTFilter
        LinkedHashMap<String, Filter> filters = new LinkedHashMap<>();
        filters.put("jwt", new JWTFilter());
        bean.setFilters(filters);

        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        // 所有请求都要经过 jwt过滤器
        filterMap.put("/static/**", "anon");
        filterMap.put("/**", "jwt");


        bean.setFilterChainDefinitionMap(filterMap);

        return bean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("backManagerRealm") BackManagerRealm backManagerRealm) {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //关联realm
        securityManager.setRealm(backManagerRealm);

        return securityManager;
    }

    @Bean(name = "backManagerRealm")
    public BackManagerRealm backManagerRealm() {
        return new BackManagerRealm();
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
