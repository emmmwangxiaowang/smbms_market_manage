package com.wang.dao.user;

import com.mysql.cj.util.StringUtils;
import com.wang.dao.BaseDao;
import com.wang.pojo.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/4/19 0019
 */
public class UserDaoImpl implements UserDao
{

    //得到登录的用户
    public user getLoginUser(Connection connection, String userCode)
    {

        PreparedStatement pstm = null;
        ResultSet         rs   = null;
        user              user = null;

        if (connection != null)
        {
            String   sql    = "select * from smbms_user where userCode=?";
            Object[] params = {userCode};
            try
            {
                rs = BaseDao.execute(connection, sql, params, rs, pstm);

                if (rs.next())
                {
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

            } catch (SQLException e)
            {
                e.printStackTrace();
            }

        }

        return user;
    }

    //修改当前用户密码
    public int updatePwd(Connection connection, int id, String password) throws SQLException
    {

        PreparedStatement pstm    = null;
        int               execute = 0;
        if (connection != null)
        {
            String sql = "update smbms_user set userPassword=? where id = ?";

            Object params[] = {password, id};
            execute = BaseDao.executeUpdate(connection, sql, params, pstm);
            BaseDao.closeResource(null, pstm, null);
        }

        return execute;
    }

    public int getUserCount(Connection connection, String userName, int userRole) throws SQLException
    {

        PreparedStatement pstm  = null;
        ResultSet         rs    = null;
        int               count = 0;

        if (connection != null)
        {
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_user u,smbms_role r where u.userRole=r.id");
            ArrayList <Object> list = new ArrayList <>();//存放参数

            if (!StringUtils.isNullOrEmpty(userName))
            {
                sql.append(" and u.userName like ?");
                list.add("%" + userName + "%");
            }

            if (userRole > 0)
            {
                sql.append(" and u.userRole=?");
                list.add(userRole);
            }

            //怎么把list转换为数组
            Object[] params = list.toArray();

            System.out.println("UserDaoImpl->getUserCount:" + sql.toString());

            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);

            if (rs.next())
            {
                count = rs.getInt("count");//从结果集中获取数量
            }

            BaseDao.closeResource(null, pstm, rs);
        }

        return count;
    }

    //根据条件查询用户列表
    public List <user> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws Exception
    {
        PreparedStatement pstm     = null;
        ResultSet         rs       = null;
        List <user>       userList = new ArrayList <user>();
        if (connection != null)
        {
            StringBuffer sql = new StringBuffer();
            sql.append("select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.userRole = r.id ");
            List <Object> list = new ArrayList <Object>();
            if (!StringUtils.isNullOrEmpty(userName))
            {
                sql.append(" and u.userName like ?");
                list.add("%" + userName + "%");
            }
            if (userRole > 0)
            {
                sql.append(" and u.userRole =?");
                list.add(userRole);
            }
            //在数据库中，分页使用 limit startIndex，pageSize:总数
            //当前页（当前页-1）*页面大小
            //0，  5      1    0       01234
            //5，  5      2    5       26789
            //10， 5      3    10
            sql.append(" order by creationDate DESC limit ?,?");
            currentPageNo = (currentPageNo - 1) * pageSize;
            list.add(currentPageNo);
            list.add(pageSize);

            Object[] params = list.toArray();
            //System.out.println("sql---->" + sql.toString());
            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);
            while (rs.next())
            {
                user _user = new user();
                _user.setId(rs.getInt("id"));
                _user.setUserCode(rs.getString("userCode"));
                _user.setUserName(rs.getString("userName"));
                _user.setGender(rs.getInt("gender"));
                _user.setBirthday(rs.getDate("birthday"));
                _user.setPhone(rs.getString("phone"));
                _user.setUserRole(rs.getInt("userRole"));
                _user.setUserRoleName(rs.getString("userRoleName"));
                userList.add(_user);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return userList;
    }

}