package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.BaseDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 通用的DAO的实现类
 * @param <T>
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    // 方案二：使用泛型转换，使用jdk提供的api
    Class entityClass;
    public BaseDaoImpl(){
        // 只要加载spring容器，就会创建每个Dao的子类，通过子类调用父类，执行父类的构造方法
        Class childClass = this.getClass(); // CustomerDaoImpl<Customer>
        Type type = childClass.getGenericSuperclass(); // BaseDaoImpl<Customer>
        ParameterizedType parameterizedType = (ParameterizedType)type; // BaseDaoImpl<Customer>
        entityClass = (Class) parameterizedType.getActualTypeArguments()[0];  //Customer
    }

    @Override
    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    @Override
    public T findById(Serializable id) {
        T entity = (T) this.getHibernateTemplate().get(entityClass, id); // ?
        return entity;
    }

    @Override
    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    @Override
    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    @Override
    public List<T> findAll() {
        List<T> list = (List<T>) this.getHibernateTemplate().find("from "+entityClass.getSimpleName()); //?
        return list;
    }

    @Override
    public Integer findByCount(DetachedCriteria criteria) {
        criteria.setProjection(Projections.rowCount()); // 等同于select count(*)
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
        if(list!=null && list.size()>0){
            return list.get(0).intValue();
        }
        return 0; // 没有数据
    }

    @Override
    public List<T> findByPage(DetachedCriteria criteria, Integer firstResult, Integer pageSize) {
        criteria.setProjection(null);// 清空之前的count(*)的配置，变成默认
        List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(criteria, firstResult, pageSize);
        return list;
    }

}