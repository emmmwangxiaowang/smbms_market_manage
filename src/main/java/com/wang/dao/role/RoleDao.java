package com.wang.dao.role;

import com.wang.pojo.role;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/5/6 0006
 */
public interface RoleDao {
    //获取角色列表
    public List<role> getRoleList(Connection connection)throws SQLException;
}
