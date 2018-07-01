package com.ssh.service;

import com.ssh.domain.Customer;
import com.ssh.domain.LinkMan;
import com.ssh.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface LinkManService {
    PageBean<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

    void save(LinkMan linkMan);
}
