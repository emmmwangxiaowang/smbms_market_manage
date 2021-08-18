package com.wang.servlet.user;

import com.wang.pojo.user;
import com.wang.service.user.UserService;
import com.wang.service.user.UserServiceImpl;
import com.wang.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/4/20 0020
 */
public class LoginServlet extends HttpServlet {
    private static final Logger logger= LoggerFactory.getLogger(LoginServlet.class);
    //Servlet:控制层,调用业务层代码
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet--start...");
        logger.info("服务启动");

        //获取用户名和密码
        String userCode=req.getParameter("userCode");
        logger.info("get userCode",userCode);
        String userPassword=req.getParameter("userPassword");
        logger.info("getPass",userPassword);

        //和数据库中的密码进行对比,调用业务层
        UserService userService=new UserServiceImpl();
        //这里已经把登陆的人给查出来了
        user user=userService.login(userCode,userPassword);
        System.out.println(user);
        //查有此人,可以登录
        if(user!=null){
            //将用户的信息放到Session中
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            //跳转到主页
            resp.sendRedirect("jsp/frame.jsp");
        }else {
            //没有此人,无法登录,转发到登录页面
            System.out.println("没有此人");
            req.setAttribute("error","用户名或密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
