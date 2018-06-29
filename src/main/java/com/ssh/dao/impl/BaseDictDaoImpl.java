package com.ssh.dao.impl;

import com.ssh.dao.BaseDictDao;
import com.ssh.domain.BaseDict;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class BaseDictDaoImpl extends HibernateDaoSupport implements BaseDictDao {
    public void setSessionFactory(org.springframework.orm.hibernate5.LocalSessionFactoryBean sessionFactory) {
    }

    @Override
    public List<BaseDict> findByTypeCode(String dict_type_code) {
        return (List<BaseDict>) getHibernateTemplate().find("from BaseDict where dict_type_code = ?",
                dict_type_code);
    }
}
