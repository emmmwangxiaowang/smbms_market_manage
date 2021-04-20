package com.wang.service.user;

import com.wang.dao.BaseDao;
import com.wang.dao.user.UserDao;
import com.wang.dao.user.UserDaoImpl;
import com.wang.pojo.user;

import org.junit.Test;

import java.sql.Connection;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/4/19 0019
 */
public class UserServiceImpl implements UserService {

    //业务层都会调用dao层,所以我们要引入dao层;
    private UserDaoImpl userDao;

    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    public user login(String userCode, String password) {
        Connection connection=null;
        user user=null;

        connection= BaseDao.getConnection();
        user= userDao.getLoginUser(connection, userCode);
        BaseDao.closeResource(connection,null,null);

        return user;
    }

    @Test
    public void test(){
        UserServiceImpl userService=new UserServiceImpl();
        user admin=userService.login("admin","1234567");
        System.out.println(admin.getUserPassword());
    }
}
