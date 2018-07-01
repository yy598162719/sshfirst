package com.ssh.dao.impl;

import com.ssh.dao.LinkManDao;
import com.ssh.domain.Customer;
import com.ssh.domain.LinkMan;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {
    public void setSessionFactory(org.springframework.orm.hibernate5.LocalSessionFactoryBean sessionFactory) {
    }

    @Override
    public Integer findCount(DetachedCriteria detachedCriteria) {
        // 设置查询的方式为查询数量
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0) {
            return list.get(0).intValue();
        }
        return null;
    }

    @Override
    public List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        // 清空查询方式，使其按照默认的查询方式进行
        detachedCriteria.setProjection(null);
        return (List<LinkMan>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
    }

    @Override
    public void save(LinkMan linkMan) {
        getHibernateTemplate().save(linkMan);
    }
}
