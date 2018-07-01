package com.ssh.dao.impl;

import com.ssh.dao.CustomerDao;
import com.ssh.domain.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

    //提供sessionfacoty注入的set方法，不然会报错（sessionFactory' or 'hibernateTemplate' is required）
    @Resource
    public void setSessionFacotry(SessionFactory sessionFacotry) {
        super.setSessionFactory(sessionFacotry);
    }
    @Override
    public void add(Customer customer) {
        //通过hibernate模板操作数据库
        getHibernateTemplate().save(customer);
    }

    @Override
    public Integer findCount(DetachedCriteria detachedCriteria) {
        // 设置查询的方式为查询数量
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if(list.size() > 0){
            return list.get(0).intValue();
        }
        return null;
    }

    @Override
    public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        // 清空查询方式，使其按照默认的查询方式进行
        detachedCriteria.setProjection(null);
        return (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
    }

    @Override
    public Customer findById(Long cust_id) {
        return getHibernateTemplate().get(Customer.class,cust_id);
    }

    @Override
    public void delete(Customer customer) {
        getHibernateTemplate().delete(customer);
    }

    @Override
    public void edit(Customer customer) {
        getHibernateTemplate().update(customer);
    }

    @Override
    public List<Customer> findAll() {
        return (List<Customer>) this.getHibernateTemplate().find("from Customer");
    }
}
