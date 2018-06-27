package com.ssh.dao.impl;

import com.ssh.dao.CustomerDao;
import com.ssh.domain.Customer;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

    //提供sessionfacoty注入的set方法，不然会报错（sessionFactory' or 'hibernateTemplate' is required）
    @Resource
    public void setSessionFacotry(SessionFactory sessionFacotry) {
        super.setSessionFactory(sessionFacotry);
    }
    @Override
    public void add(Customer customer) {
        System.out.println("DAO中的add方法执行了...");

        //通过hibernate模板操作数据库
        getHibernateTemplate().save(customer);
    }
}
