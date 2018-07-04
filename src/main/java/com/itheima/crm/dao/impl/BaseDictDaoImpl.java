package com.itheima.crm.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.crm.dao.BaseDictDao;
import com.itheima.crm.domain.BaseDict;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {
	@Autowired
	public void setDi(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	@Override
	public List<BaseDict> findByDictTypeCode(String dict_type_code) {
		List<BaseDict> list = (List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dict_type_code = ? order by dict_sort asc", dict_type_code);
		return list;
	}
}
