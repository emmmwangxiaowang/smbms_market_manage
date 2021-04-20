package com.wang.dao.user;

import com.wang.pojo.user;

import com.wang.pojo.user;

import java.sql.Connection;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/4/19 0019
 */
public interface UserDao {

    //得到要登录的用户
    public user getLoginUser(Connection connection,String userCode);
}
