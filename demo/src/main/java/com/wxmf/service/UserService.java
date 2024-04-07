package com.wxmf.service;

import com.wxmf.pojo.User;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    int addUser(User user) throws SQLException;
    
    int batchAddUser(List<User> userList) throws SQLException;

    int deleteUserByUserID(Integer UserID) throws SQLException;

	int deleteUser(User user) throws SQLException;
	
	int batchDeleteUserByUserIDs(String UserIDs) throws SQLException;
	
    int updateUser(User user) throws SQLException;

    User getUserByUserID(Integer UserID) throws SQLException;

    List<User> getAllUser() throws SQLException;

	List<User> getUser(User user) throws SQLException;
	
    PageBean<User> getUserWithPagination(PageParam pageParam) throws SQLException;

	PageBean<User> getUserWithPagination(PageParam pageParam, User user) throws SQLException;
  
}