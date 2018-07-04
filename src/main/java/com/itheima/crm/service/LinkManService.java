package com.itheima.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;

public interface LinkManService {

	PageBean<LinkMan> findByPage(DetachedCriteria criteria, Integer currPage, Integer pageSize);

    void save(LinkMan linkMan);

    LinkMan findById(Long lkm_id);

    void update(LinkMan linkMan);

    void delete(LinkMan linkMan);
}
