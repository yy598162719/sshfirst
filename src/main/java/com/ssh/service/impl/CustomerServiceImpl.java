package com.ssh.service.impl;

import com.ssh.dao.CustomerDao;
import com.ssh.dao.impl.CustomerDaoImpl;
import com.ssh.domain.Customer;
import com.ssh.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Override
    public void add(Customer customer) {
        customerDao.add(customer);
    }

    public void setCustomerDao(CustomerDaoImpl customerDao) {
    }
}
