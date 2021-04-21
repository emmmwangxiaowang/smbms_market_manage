package com.wang.dao.user;

import com.wang.pojo.user;

import com.wang.pojo.user;

import java.sql.Connection;
import java.sql.SQLException;

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
}
