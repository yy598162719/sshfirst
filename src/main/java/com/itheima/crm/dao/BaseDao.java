package com.itheima.crm.dao;

import org.hibernate.criterion.DetachedCriteria;
import java.io.Serializable;
import java.util.List;

/**
 * 通用的DAO的接口
 */
public interface BaseDao<T> {
    public void save(T t);
    public void update(T t);
    public void delete(T t);
    public T findById(Serializable id);
    public Integer findByCount(DetachedCriteria detachedCriteria);
    public List<T> findByPage(DetachedCriteria detachedCriteria, Integer firstResult, Integer pageSize);
    public List<T> findAll();
}