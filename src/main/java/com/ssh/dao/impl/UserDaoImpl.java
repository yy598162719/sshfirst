package com.ssh.dao.impl;

import com.ssh.dao.UserDao;
import com.ssh.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.util.List;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    @Resource
    public void setSessionFacotry(SessionFactory sessionFacotry) {
        super.setSessionFactory(sessionFacotry);
    }

    @Override
    public void regist(User user) {
        getHibernateTemplate().save(user);
    }

    // 用户登录的方法
    public User login(User user) {
        List<User> list = (List<User>) this.getHibernateTemplate().find(
                "from User where userCode = ? and userPassword = ? and userState = ?", user.getUserCode(),
                user.getUserPassword(), "1"); // 1表示可以使用
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}

