package com.me.ssm.system;

/**
 * Created by 不语恋 on 2017/4/27.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

/**
 * 导入必需的 java 库
 * 这个过滤器负责总体的权限管理，是从宏观角度观测的
 * 实现 Filter 类
 */
public class AuthenticationFilter implements Filter {
    private Logger logger= LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {

        String url = ((HttpServletRequest) request).getRequestURI();
        String query = ((HttpServletRequest) request).getQueryString();

        //此处写总体的url权限控制代码

        if (query != null) {
            url += "?" + query;
        }
        logger.debug(url);
        if (url.startsWith("/")) {
            // 把请求传回过滤链
            chain.doFilter(request, response);
        }
    }
    /** 在 Filter 实例被 Web 容器从服务移除之前调用 */
    @Override
    public void destroy() {

    }
}
