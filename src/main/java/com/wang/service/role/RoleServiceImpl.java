package com.wang.service.role;

import com.wang.dao.BaseDao;
import com.wang.dao.role.RoleDao;
import com.wang.dao.role.RoleDaoImpl;
import com.wang.pojo.role;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/5/6 0006
 */
public class RoleServiceImpl implements RoleService{

    //引入Dao
    private RoleDao roleDao;

    public RoleServiceImpl(){
        roleDao=new RoleDaoImpl();
    }

    public List<role> getRoleList() {

        Connection connection = null;
        List<role> roleList=null;

        try {
            connection=BaseDao.getConnection();
            roleList = roleDao.getRoleList(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }

        return roleList;
    }

    @Test
    public void test(){
        RoleServiceImpl roleService=new RoleServiceImpl();
        List<role> roleList=roleService.getRoleList();
        for (role role : roleList) {
            System.out.println(role.getRoleName());
        }
    }
}
