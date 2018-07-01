package com.ssh.dao;

import com.ssh.domain.Customer;
import com.ssh.domain.LinkMan;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface LinkManDao {
    Integer findCount(DetachedCriteria detachedCriteria);

    List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

    void save(LinkMan linkMan);
}
