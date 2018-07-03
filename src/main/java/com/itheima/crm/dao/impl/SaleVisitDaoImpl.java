package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.SaleVisitDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.SaleVisit;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SaleVisitDaoImpl  extends BaseDaoImpl<SaleVisit> implements SaleVisitDao {
    @Autowired
    public void setDi(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
}
