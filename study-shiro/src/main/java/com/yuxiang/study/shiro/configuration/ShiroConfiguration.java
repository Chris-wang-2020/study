package com.yuxiang.study.shiro.configuration;

import com.yuxiang.study.shiro.shiro.filter.LoginFilter;
import com.yuxiang.study.shiro.shiro.filter.SessionSessionFilter;
import com.yuxiang.study.shiro.shiro.filter.TokenFilter;
import com.yuxiang.study.shiro.shiro.realm.SessionShareRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyx-l
 * @version 1.0
 * @date 2020/3/27 2:29 下午
 */
@Configuration
public class ShiroConfiguration {

    //注册Filter
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = new HashMap<>(5);
        filterMap.put("login", new LoginFilter());
        filterMap.put("session", new SessionSessionFilter());
        filterMap.put("token", new TokenFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        Map<String, String> filterChainDefinitionMap = new HashMap<>(5);
        filterChainDefinitionMap.put("/token", "token");
        filterChainDefinitionMap.put("/session", "session");
        filterChainDefinitionMap.put("/go", "anon");
        filterChainDefinitionMap.put("/**", "login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    // 设置拦截链
    /*@Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        // logged in users with the 'admin' role
        chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");

        // logged in users with the 'document:read' permission
        chainDefinition.addPathDefinition("/docs/**", "authc, perms[document:read]");

        // all other paths require a logged in user
        chainDefinition.addPathDefinition();

        chainDefinition.addPathDefinition("/**", "myFilter");

        return chainDefinition;
    }*/

    @Bean
    Realm realm() {
        return new SessionShareRealm();
    }

}
