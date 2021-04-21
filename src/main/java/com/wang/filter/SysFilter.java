package com.wang.filter;

import com.wang.pojo.user;
import com.wang.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.ElementType;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/4/21 0021
 */
public class SysFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;

        //过滤器,从Session中获取用户
        user user = (com.wang.pojo.user) request.getSession().getAttribute(Constants.USER_SESSION);

        //用户已经被移除,或者注销,或者未登录
        if(user==null){
            response.sendRedirect("/smbms/jsp/common.error.jsp");
        }else {
            chain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() {

    }
}
