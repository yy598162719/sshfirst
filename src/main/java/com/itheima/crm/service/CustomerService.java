package com.itheima.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;

import java.util.List;

public interface CustomerService {

	void save(Customer customer);

	PageBean<Customer> findByPage(DetachedCriteria criteria, Integer currPage, Integer pageSize);

	Customer findById(Long cust_id);

	void delete(Customer c);

	void update(Customer customer);


	List<Customer> findAll();
}
