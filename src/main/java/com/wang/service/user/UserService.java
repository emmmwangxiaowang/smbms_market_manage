package com.wang.service.user;

import com.mysql.cj.util.StringUtils;
import com.wang.pojo.user;

import java.sql.Connection;
import java.util.List;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/4/19 0019
 */
public interface UserService {
    //用户登录
    public user login(String userCode,String password);

    //根据用户id修改密码
    public boolean updatePwd(int id, String pwd);

    //查询记录数
    public int getUserCount(String userName,int userRole);

    public List<user> getUserList(String queryUserName,int queryUserRole,int currentPageNo,int pageSize);
}
