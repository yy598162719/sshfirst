package com.ssh.service;

import com.ssh.domain.Customer;
import com.ssh.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface CustomerService {
    void add(Customer customer);

    PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);
}
