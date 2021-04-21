package com.wang.dao.user;

import com.wang.dao.BaseDao;
import com.wang.pojo.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/4/19 0019
 */
public class UserDaoImpl implements UserDao {

    //得到登录的用户
    public user getLoginUser(Connection connection, String userCode) {

        PreparedStatement pstm = null;
        ResultSet rs = null;
        user user = null;


        if (connection != null) {
            String sql = "select * from smbms_user where userCode=?";
            Object[] params = {userCode};
            try {
                rs = BaseDao.execute(connection, sql, params, rs, pstm);

                if (rs.next()) {
                    user = new user();
                    user.setId(rs.getInt("id"));
                    user.setUserCode(rs.getString("userCode"));
                    user.setUserName(rs.getString("userName"));
                    user.setUserPassword(rs.getString("userPassword"));
                    user.setGender(rs.getInt("gender"));
                    user.setBirthday(rs.getDate("birthday"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setUserRole(rs.getInt("userRole"));
                    user.setCreateBy(rs.getInt("createdBy"));
                    user.setCreationDate(rs.getTimestamp("creationDate"));
                    user.setModifyBy(rs.getInt("modifyBy"));
                    user.setModifyDate(rs.getTimestamp("modifyDate"));

                }

                BaseDao.closeResource(null, pstm, rs);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return user;
    }

    //修改当前用户密码
    public int updatePwd(Connection connection, int id, String password) throws SQLException {

        PreparedStatement pstm=null;
        int execute=0;
        if(connection!=null){
            String sql="update smbms_user set userPassword=? where id = ?";

            Object params[] ={password,id};
            execute = BaseDao.executeUpdate(connection, sql, params,pstm);
            BaseDao.closeResource(null,pstm,null);
        }

        return execute;
    }
}