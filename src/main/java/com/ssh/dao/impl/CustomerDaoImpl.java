package com.ssh.dao.impl;

import com.ssh.dao.CustomerDao;
import com.ssh.domain.Customer;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;


public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {


    @Override
    public void add(Customer customer) {
        System.out.println("DAO中的add方法执行了...");
        getHibernateTemplate().save(customer);
    }
}
