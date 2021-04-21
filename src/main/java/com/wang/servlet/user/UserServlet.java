package com.wang.servlet.user;

import com.mysql.cj.util.StringUtils;
import com.wang.pojo.user;
import com.wang.service.user.UserService;
import com.wang.service.user.UserServiceImpl;
import com.wang.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/4/21 0021
 */
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String method= req.getParameter("method");
       if(method.equals("savepwd")&&method!=null){
           this.updatePwd(req,resp);
       }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从session里面那id;
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);

        String newpassword = req.getParameter("newpassword");
        boolean flag=false;

        if(o!=null&& !StringUtils.isNullOrEmpty(newpassword)){
            UserService userService=new UserServiceImpl();
            boolean b = userService.updatePwd(((user) o).getId(), newpassword);
            if(flag){
                req.setAttribute("message","修改密码成功,请重新登录");
                //密码修改成功,移除当前session
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }else{
                req.setAttribute("message","修改修改失败");
                //密码修改成功,移除当前session
            }
        }else{
            req.setAttribute("message","新密码有问题");
        }

        req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);

    }
}
