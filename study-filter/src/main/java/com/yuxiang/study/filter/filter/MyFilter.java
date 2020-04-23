package com.yuxiang.study.filter.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author wangyx-l
 * @version 1.0
 * @date 2020/3/30 11:16 上午
 */
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("------------");
        chain.doFilter(request, response);
    }
}
