package com.me.ssm.System;

/**
 * Created by 不语恋 on 2017/4/27.
 */

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

//导入必需的 java 库
//这个过滤器负责总体的权限管理，是从宏观角度观测的
//实现 Filter 类
public class AuthenticationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {

        String url = ((HttpServletRequest) request).getRequestURI();
        String query = ((HttpServletRequest) request).getQueryString();

        //此处写总体的url权限控制代码

        if (query != null) {
            url += "?" + query;
        }
        System.out.println(url);
        if (url.startsWith("/")) {
            // 把请求传回过滤链
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
        /* 在 Filter 实例被 Web 容器从服务移除之前调用 */
    }
}
