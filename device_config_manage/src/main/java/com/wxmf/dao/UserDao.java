package com.wxmf.dao;

import com.wxmf.pojo.User;
import java.util.List;
import java.sql.SQLException;
import com.wxmf.utils.PageParam;

public interface UserDao {
    int insertUser(User user) throws SQLException;

	int batchInsertUser(List<User> userList) throws SQLException;

    int deleteUserByUserID(Integer UserID) throws SQLException;

	int deleteUserByCondition(User user) throws SQLException;

	int batchDeleteUserByUserIDs(String UserIDs) throws SQLException;

    int updateUser(User user) throws SQLException;

    long selectCount() throws SQLException;
    
    long selectCountByCondition(User user) throws SQLException;

    User selectUserByUserID(Integer UserID) throws SQLException;

    List<User> selectAllUser() throws SQLException;

	List<User> selectUserByCondition(User user) throws SQLException;

    List<User> selectUserWithPagination(PageParam pageParam) throws SQLException;

    List<User> selectUserWithPaginationByCondition(PageParam pageParam, User user) throws SQLException;
    
}