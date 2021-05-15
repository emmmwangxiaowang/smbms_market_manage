package com.wang.dao.role;

import com.wang.dao.BaseDao;
import com.wang.pojo.role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/5/6 0006
 */
public class RoleDaoImpl implements RoleDao {
    //获取角色列表
    public List<role> getRoleList(Connection connection) throws SQLException {

        PreparedStatement pstm=null;
        ResultSet rs=null;
        ArrayList<role> roleList = new ArrayList<>();

        if(connection!=null){
            String sql="select * from smbms_role";
            Object[] params={};
            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);

            while (rs.next()){
                role _role =new role();
                _role.setId(rs.getInt("id"));
                _role.setRoleName(rs.getString("roleName"));
                _role.setRoleCode(rs.getString("roleCode"));
                roleList.add(_role);
            }
            BaseDao.closeResource(null,pstm,rs);
        }
        return roleList;
    }
}
