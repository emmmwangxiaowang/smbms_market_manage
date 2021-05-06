package com.wang.dao.user;

import com.wang.pojo.user;

import com.wang.pojo.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/4/19 0019
 */
public interface UserDao {

    //得到要登录的用户
    public user getLoginUser(Connection connection,String userCode)throws SQLException;

    //修改当前用户密码
    public int updatePwd(Connection connection ,int id,String password)throws SQLException;

    //根据用户名或者角色查询用户总数
    public int getUserCount(Connection connection,String userName,int userRole) throws SQLException;

    //获取用户列表
    public List<user> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws Exception;
}
