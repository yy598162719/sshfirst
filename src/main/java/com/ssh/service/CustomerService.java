package com.ssh.service;

import com.ssh.domain.Customer;
import com.ssh.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerService {
    void add(Customer customer);

    PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

    Customer findById(Long cust_id);

    void delete(Customer customer);

    void edit(Customer customer);

    List<Customer> findAll();
}
