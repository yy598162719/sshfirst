package com.itheima.crm.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.itheima.crm.utils.MD5Utils;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void save(User user) {
		user.setUser_state("1"); // 在用，1,定义常量
		// 密码加密（MD5）
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		userDao.save(user);
	}
	
	@Override
	public User findUserByUserCodeAndPassword(User user) {
		// 对登录的密码，完成Md5的加密
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		return userDao.findUserByUserCodeAndPassword(user);
	}
}
