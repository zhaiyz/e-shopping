package com.shopping.service.impl;

import java.util.List;

import com.shopping.dao.UserDao;
import com.shopping.dao.impl.UserDaoImpl;
import com.shopping.service.UserService;
import com.shopping.vo.UserVo;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();

	public Boolean addUser(UserVo user) {
		return userDao.addUser(user);
	}

	public List<UserVo> findAllUser() {
		return userDao.findAllUser();
	}

	public UserVo findUserById(int id) {
		return userDao.findUserById(id);
	}

	public Boolean modifyUser(UserVo user) {
		return userDao.modifyUser(user);
	}

	public Boolean removeUserById(int id) {
		return userDao.removeUserById(id);
	}
}
