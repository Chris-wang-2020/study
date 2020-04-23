package com.yuxiang.study.filter.advisor;

import com.yuxiang.study.filter.advice.MyInterceptor;
import com.yuxiang.study.filter.anotation.MyAnotation;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author wangyx-l
 * @version 1.0
 * @date 2020/3/30 2:58 下午
 */
@Component
public class MyAdvisor extends StaticMethodMatcherPointcutAdvisor {

    public MyAdvisor() {
        setAdvice(new MyInterceptor());
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {

        if(method.isAnnotationPresent(MyAnotation.class)) {
            return true;
        } else {
            return false;
        }
    }
}
