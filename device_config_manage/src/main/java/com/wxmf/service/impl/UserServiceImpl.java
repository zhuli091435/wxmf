package com.wxmf.service.impl;

import com.wxmf.pojo.User;
import java.util.List;
import com.wxmf.service.UserService;
import com.wxmf.dao.UserDao;
import com.wxmf.dao.impl.UserDaoImpl;
import com.wxmf.utils.PageBean;
import com.wxmf.utils.PageParam;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public int addUser(User user) throws SQLException{
        int res = userDao.insertUser(user);
        return res;
    }

	@Override
    public int batchAddUser(List<User> userList) throws SQLException{
		int res = userDao.batchInsertUser(userList);
		return res;
	}

    @Override
    public int deleteUserByUserID(Integer UserID) throws SQLException{
        int res = userDao.deleteUserByUserID(UserID);
        return res;
    }
    
 	@Override
	public int deleteUser(User user) throws SQLException{
	 	int res = userDao.deleteUserByCondition(user);
        return res;
	}
	
	@Override
	public int batchDeleteUserByUserIDs(String UserIDs) throws SQLException{
		int res = userDao.batchDeleteUserByUserIDs(UserIDs);
        return res;
	}
	
    @Override
    public int updateUser(User user) throws SQLException{
        int res = userDao.updateUser(user);
        return res;
    }

    @Override
    public User getUserByUserID(Integer UserID) throws SQLException{
        User res = userDao.selectUserByUserID(UserID);
        return res;
    }

    @Override
    public List<User> getAllUser() throws SQLException{
        List<User> res = userDao.selectAllUser();
        return res;
    }

	@Override
	public 	List<User> getUser(User user) throws SQLException{
		List<User> res = userDao.selectUserByCondition(user);
		return res;
	}
	
    @Override
    public PageBean<User> getUserWithPagination(PageParam pageParam) throws SQLException{
        long count = userDao.selectCount();
        PageBean<User> res = new PageBean<>(pageParam, (int)count);
        List<User> data = userDao.selectUserWithPagination(pageParam);
        res.setRecords(data);
        return res;
    }

	@Override
	public PageBean<User> getUserWithPagination(PageParam pageParam, User user) throws SQLException{
        long count = userDao.selectCountByCondition(user);
        PageBean<User> res = new PageBean<>(pageParam, (int)count);		
		List<User> data = userDao.selectUserWithPaginationByCondition(pageParam,user);
		res.setRecords(data);
        return res;
	}
  
}