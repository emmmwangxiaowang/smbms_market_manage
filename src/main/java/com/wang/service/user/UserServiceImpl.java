package com.wang.service.user;

import com.wang.dao.BaseDao;
import com.wang.dao.user.UserDao;
import com.wang.dao.user.UserDaoImpl;
import com.wang.pojo.user;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

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
        if(!user.getUserPassword().equals(password)){
            return null;
        }
        BaseDao.closeResource(connection,null,null);

        return user;
    }

    public boolean updatePwd(int id,String pwd){
        Connection connection=null;
        //判断标志,密码是否修改成功
        boolean flag=false;


        //修改密码
        try {
            connection = BaseDao.getConnection();
            if(userDao.updatePwd(connection,id,pwd)>0){
                flag=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }


}
