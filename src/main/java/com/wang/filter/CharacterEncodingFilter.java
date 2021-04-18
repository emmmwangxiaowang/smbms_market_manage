package com.wang.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/4/18 0018
 */
public class CharacterEncodingFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        chain.doFilter(request,response);
    }

    public void destroy() {

    }
}
