package com.itheima.crm.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.crm.dao.LinkManDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.LinkMan;
import org.springframework.stereotype.Repository;

@Repository
public class LinkManDaoImpl extends BaseDaoImpl<LinkMan> implements LinkManDao {
    @Autowired
    public void setDi(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

}
