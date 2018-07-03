package com.itheima.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	
	@Override
	public User findUserByUserCodeAndPassword(User user) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code = ? and user_password = ?", user.getUser_code(),user.getUser_password());
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
