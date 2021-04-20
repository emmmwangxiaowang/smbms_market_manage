package com.wang.service.user;

import com.wang.pojo.user;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/4/19 0019
 */
public interface UserService {
    //用户登录
    public user login(String userCode,String password);
}
