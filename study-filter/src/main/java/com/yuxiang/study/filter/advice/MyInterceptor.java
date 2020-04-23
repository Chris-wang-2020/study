package com.yuxiang.study.filter.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author wangyx-l
 * @version 1.0
 * @date 2020/3/30 3:05 下午
 */
public class MyInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("*************");
        return invocation.proceed();
    }
}
