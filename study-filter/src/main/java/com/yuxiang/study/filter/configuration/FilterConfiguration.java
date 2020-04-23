package com.yuxiang.study.filter.configuration;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author wangyx-l
 * @version 1.0
 * @date 2020/3/30 11:14 上午
 */
@Configuration
@EnableAspectJAutoProxy
public class FilterConfiguration {

    /*@Bean
    Filter filter() {
        return new MyFilter();
    }*/

    @Bean
    DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator =  new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(Boolean.TRUE);
        return defaultAdvisorAutoProxyCreator;
    }
}
