package com.wang.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.wang.pojo.role;
import com.wang.pojo.user;
import com.wang.service.role.RoleService;
import com.wang.service.role.RoleServiceImpl;
import com.wang.service.user.UserService;
import com.wang.service.user.UserServiceImpl;
import com.wang.util.Constants;
import com.wang.util.PageSupport;

import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
       }else if(method.equals("query")&&method!=null){
           this.query(req,resp);
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


    //重点、难点
    private void query(HttpServletRequest req, HttpServletResponse resp) {
        //从请求中获取参数
        String queryName = req.getParameter("queryName");
        String pageIndex = req.getParameter("pageIndex");

        String temp = req.getParameter("queryUserRole");
        int queryUserRole = 0; //初始化赋值,避免查询时出现异常

        //页面大小
        int pageSize = Constants.PAGE_SIZE;
        //当前页码
        int currentPageNo = 1;

        //打印输出获取到的参数
        System.out.println("queryName -------->"+queryName);
        System.out.println("queryUserRole -------->"+queryUserRole);
        System.out.println("pageIndex ---------> " + pageIndex);

        //判断是否传入指定用户名
        if(queryName == null){
            //未传入则赋值为空字符串,避免查询报错
            queryName = "";
        }
        //判断是否限定了用户角色条件
        if(temp != null && !temp.equals("")){
            queryUserRole = Integer.parseInt(temp);
        }
        //判断传入页码是否正确
        if (pageIndex != null){
            try {
                currentPageNo = Integer.parseInt(pageIndex);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                try {
                    resp.sendRedirect("error.jsp");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        //获取符合条件的用户个数
        UserService userService = new UserServiceImpl();
        int totalCount = userService.getUserCount(queryName, queryUserRole);

        //计算数据显示的总页数
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalPageCount(totalCount);

        int totalPageCount = totalCount/pageSize+1; //总页数

        //控制首页和尾页
        if(currentPageNo < 1){ //如果页码小于1,则显示第一页
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){ //如果页码大于最大页码数.则显示最后一页
            currentPageNo = totalPageCount;
        }

        //将查到的用户数据存到Attribute中
        List<user> userList = userService.getUserList(queryName, queryUserRole, currentPageNo, pageSize);
        Iterator<user> iterator = userList.iterator();
        /*while (iterator.hasNext()){
            user user = iterator.next();
            System.out.println("用户:"+user);
        }*/


        //将查询到的角色数据存到Attribute中
        RoleService roleService = new RoleServiceImpl();
        List<role> roleList = roleService.getRoleList();
        /*for(role role : roleList){
            System.out.println("用户角色:"+role);
        }*/

        //将需要的参数存到Attribute中
        req.setAttribute("userList", userList);
        req.setAttribute("roleList", roleList);
        req.setAttribute("queryUserName", queryName);
        req.setAttribute("queryUserRole", queryUserRole);
        req.setAttribute("totalPageCount", totalPageCount);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("currentPageNo", currentPageNo);

        //转发:url不变
        try {
            req.getRequestDispatcher("userlist.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
