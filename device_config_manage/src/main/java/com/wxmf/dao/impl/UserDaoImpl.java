package com.wxmf.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wxmf.pojo.User;

import java.util.List;
import java.util.ArrayList;

import com.wxmf.utils.DBUtil;
import com.wxmf.dao.UserDao;

import java.sql.SQLException;

import com.wxmf.utils.PageParam;

public class UserDaoImpl implements UserDao {
    private QueryRunner queryRunner = DBUtil.getRunner();

    @Override
    public int insertUser(User user) throws SQLException {
        String sql = "insert into User (UserID, UserName, Password) values (?, ?, ?)";
        return queryRunner.update(sql, user.getUserID(), user.getUserName(), user.getPassword());
    }

    @Override
    public int batchInsertUser(List<User> userList) throws SQLException {
        Object[][] params = new Object[userList.size()][3];

        for (int i = 0; i < params.length; i++) {
            User user = userList.get(i);
            params[i][0] = user.getUserID();
            params[i][1] = user.getUserName();
            params[i][2] = user.getPassword();
        }

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < params[0].length; i++) {
            wenHao.append("?,");
        }
        String sql = "insert into User values(" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int deleteUserByUserID(Integer UserID) throws SQLException {
        String sql = "delete from User where UserID = ?";
        return queryRunner.update(sql, UserID);
    }

    @Override
    public int deleteUserByCondition(User user) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (user.getUserID() != null) {
            paramBuf.append(" UserID= ? and");
            paramValueList.add(user.getUserID());
        }
        if (user.getUserName() != null) {
            paramBuf.append(" UserName= ? and");
            paramValueList.add(user.getUserName());
        }
        if (user.getPassword() != null) {
            paramBuf.append(" Password= ? and");
            paramValueList.add(user.getPassword());
        }

        String sql = "delete from User where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.update(sql, paramValueList.toArray());
    }

    @Override
    public int batchDeleteUserByUserIDs(String UserIDs) throws SQLException {
        String[] split = UserIDs.split(",");
        Object[][] params = new Object[1][];

        StringBuilder wenHao = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            wenHao.append("?,");
        }
        params[0] = split;

        String sql = "delete from User where UserID in (" + wenHao.deleteCharAt(wenHao.length() - 1) + ")";

        queryRunner.batch(sql, params);
        return 1;  // 如果不抛出异常，就返回1，表示删除成功
    }

    @Override
    public int updateUser(User user) throws SQLException {
        String sql = "update User set UserName= ? , Password= ?  where UserID = ?";
        return queryRunner.update(sql, user.getUserName(), user.getPassword(), user.getUserID());
    }

    @Override
    public long selectCount() throws SQLException {
        String sql = "select count(*) from User";
        Long query = queryRunner.query(sql, new ScalarHandler<Long>());
        return query.intValue();
    }

    @Override
    public long selectCountByCondition(User user) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (user.getUserID() != null) {
            paramBuf.append(" UserID= ? and");
            paramValueList.add(user.getUserID());
        }
        if (user.getUserName() != null) {
            paramBuf.append(" UserName= ? and");
            paramValueList.add(user.getUserName());
        }
        if (user.getPassword() != null) {
            paramBuf.append(" Password= ? and");
            paramValueList.add(user.getPassword());
        }

        String sql = "select count(*) from User where " + paramBuf.substring(0, paramBuf.length() - 3);
        Long query = queryRunner.query(sql, new ScalarHandler<Long>(), paramValueList.toArray());
        return query.intValue();
    }

    @Override
    public User selectUserByUserID(Integer UserID) throws SQLException {
        String sql = "select UserID as UserID, UserName as UserName, Password as Password from User where  UserID = ?";
        return queryRunner.query(sql, new BeanHandler<>(User.class), UserID);
    }

    @Override
    public List<User> selectAllUser() throws SQLException {
        String sql = "select UserID as UserID, UserName as UserName, Password as Password from User";
        return queryRunner.query(sql, new BeanListHandler<>(User.class));
    }

    @Override
    public List<User> selectUserByCondition(User user) throws SQLException {
        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (user.getUserID() != null) {
            paramBuf.append(" UserID= ? and");
            paramValueList.add(user.getUserID());
        }
        if (user.getUserName() != null) {
            paramBuf.append(" UserName= ? and");
            paramValueList.add(user.getUserName());
        }
        if (user.getPassword() != null) {
            paramBuf.append(" Password= ? and");
            paramValueList.add(user.getPassword());
        }

        String sql = "select UserID as UserID, UserName as UserName, Password as Password  from User where " + paramBuf.substring(0, paramBuf.length() - 3);
        return queryRunner.query(sql, new BeanListHandler<>(User.class), paramValueList.toArray());
    }

    @Override
    public List<User> selectUserWithPagination(PageParam pageParam) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        String sql = "select UserID as UserID, UserName as UserName, Password as Password from User limit ?, ?";
        return queryRunner.query(sql, new BeanListHandler<>(User.class), (page - 1) * rows, rows);
    }

    @Override
    public List<User> selectUserWithPaginationByCondition(PageParam pageParam, User user) throws SQLException {
        int page = pageParam.getPage();
        int rows = pageParam.getRows();

        List<Object> paramValueList = new ArrayList<>();
        StringBuffer paramBuf = new StringBuffer("1=1 and");

        if (user.getUserID() != null) {
            paramBuf.append(" UserID= ? and");
            paramValueList.add(user.getUserID());
        }
        if (user.getUserName() != null) {
            paramBuf.append(" UserName= ? and");
            paramValueList.add(user.getUserName());
        }
        if (user.getPassword() != null) {
            paramBuf.append(" Password= ? and");
            paramValueList.add(user.getPassword());
        }

        String sql = "select UserID as UserID, UserName as UserName, Password as Password  from User where " + paramBuf.substring(0, paramBuf.length() - 3) + " limit ?, ?";

        paramValueList.add((page - 1) * rows);
        paramValueList.add(rows);
        return queryRunner.query(sql, new BeanListHandler<>(User.class), paramValueList.toArray());
    }

}