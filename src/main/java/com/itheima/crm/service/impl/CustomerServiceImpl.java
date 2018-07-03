package com.itheima.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}
	
	@Override
	public PageBean<Customer> findByPage(DetachedCriteria criteria, Integer currPage, Integer pageSize) {
		// 响应的结果PageBean，Service就是要完成对数据的封装
		PageBean<Customer> pageBean = new PageBean<>();
		// 当前页
		pageBean.setCurrPage(currPage);
		// 当前页最多显示的记录数
		pageBean.setPageSize(pageSize);
		
		Integer totalCount = customerDao.findByCount(criteria);
		// 总记录数
		pageBean.setTotalCount(totalCount);
		// 总页数
		Double tc = totalCount.doubleValue();
		double tp = Math.ceil(tc/pageSize); // ,math.ceil(x)返回大于等于参数x的最小整数,即对浮点数向上取整.
		Integer totalPage = (int)tp;
		pageBean.setTotalPage(totalPage);
		
		// 查询的集合（分页）
		// 当前页从第几条开始检索   当前页最多显示的记录数
		Integer firstResult = (currPage-1)*pageSize;
		List<Customer> list = customerDao.findByPage(criteria,firstResult,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}
	
	@Override
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}
	
	@Override
	public void delete(Customer c) {
		customerDao.delete(c);
	}
	
	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
}
