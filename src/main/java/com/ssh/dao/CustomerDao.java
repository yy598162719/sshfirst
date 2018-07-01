package com.ssh.dao;



import com.ssh.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerDao {

    void add(Customer customer);

    Integer findCount(DetachedCriteria detachedCriteria);

    List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

    Customer findById(Long cust_id);

    void delete(Customer customer);

    void edit(Customer customer);

    List<Customer> findAll();
}
