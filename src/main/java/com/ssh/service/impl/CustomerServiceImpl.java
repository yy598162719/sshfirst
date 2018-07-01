package com.ssh.service.impl;

import com.ssh.dao.CustomerDao;
import com.ssh.dao.impl.CustomerDaoImpl;
import com.ssh.domain.Customer;
import com.ssh.domain.PageBean;
import com.ssh.service.CustomerService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Override
    public void add(Customer customer) {
        customerDao.add(customer);
    }

    @Override
    public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        PageBean<Customer> pageBean = new PageBean<Customer>();
        // 设置当前页数:
        pageBean.setCurrPage(currPage);
        // 设置每页显示记录数：
        pageBean.setPageSize(pageSize);
        // 设置总记录数:
        Integer totalCount = customerDao.findCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        // 设置总页数：
        double tc = totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        // 设置每页显示数据的集合:
        // 设置当前页从第几条开始检索。
        Integer begin = (currPage - 1) * pageSize;
        List<Customer> list = customerDao.findByPage(detachedCriteria, begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public Customer findById(Long cust_id) {
        return customerDao.findById(cust_id);

    }

    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    @Override
    public void edit(Customer customer) {
        customerDao.edit(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    public void setCustomerDao(CustomerDao customerDao) {
    }
}
