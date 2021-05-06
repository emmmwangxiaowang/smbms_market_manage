package com.wang.servlet.user;

import com.alibaba.fastjson.JSONArray;
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
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/4/21 0021
 */

//实现servlet复用
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String method= req.getParameter("method");
       if(method.equals("savepwd")&&method!=null){
           this.updatePwd(req,resp);
       }else if(method.equals("pwdmodify")&&method!=null){
            this.pwdModify(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    //修改密码
    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从session里面那id;
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);

        String newpassword = req.getParameter("newpassword");
        boolean flag=false;

        if(o!=null&& !StringUtils.isNullOrEmpty(newpassword)){
            UserService userService=new UserServiceImpl();
            flag = userService.updatePwd(((user) o).getId(), newpassword);
            if(flag){
                req.setAttribute("message","修改密码成功,请重新登录");
                //密码修改成功,移除当前session
                System.out.println("USER-SESSION"+req.getSession().getAttribute(Constants.USER_SESSION));
                req.getSession().removeAttribute(Constants.USER_SESSION);
                System.out.println("USER-SESSION"+req.getSession().getAttribute(Constants.USER_SESSION));
            }else{
                req.setAttribute("message","修改修改失败");
                //密码修改成功,移除当前session
            }
        }else{
            req.setAttribute("message","新密码有问题");
        }

        req.getRequestDispatcher("frame.jsp").forward(req,resp);

    }

    //验证旧密码,session中有用户的密码
    public void pwdModify(HttpServletRequest req, HttpServletResponse resp){
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword=req.getParameter("oldpassword");

        //万能的map:结果集
        HashMap<String, String> resultMap = new HashMap<>();

        //session失效了,过期了
        if(o==null){
            resultMap.put("result","sessionerror");
        }else if(StringUtils.isNullOrEmpty(oldpassword)){//输入的密码为空
            resultMap.put("result","error");
        }else{
            //session中用户的密码
            String userPassword = ((user) o).getUserPassword();
            System.out.println("userPassword"+userPassword);
            System.out.println(userPassword==oldpassword);
            if(oldpassword.equals(userPassword)){
                resultMap.put("result","true");
            }else{
                //密码输入错误
                resultMap.put("result","false");
            }
        }

        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            //JSONArray 阿里巴巴工具类 --转换格式
            /*
            resultMap={"result","sessionerror"}
            Json格式=={key:value}
             */
            //将map转化为json传递给前端ajax
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
