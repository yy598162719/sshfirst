package com.itheima.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.LinkManDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.LinkManService;

@Transactional
@Service
public class LinkManServiceImpl implements LinkManService {
@Autowired
	private LinkManDao linkManDao;

	@Override
	public PageBean<LinkMan> findByPage(DetachedCriteria criteria, Integer currPage, Integer pageSize) {
		// 响应的结果PageBean，Service就是要完成对数据的封装
		PageBean<LinkMan> pageBean = new PageBean<>();
		// 当前页
		pageBean.setCurrPage(currPage);
		// 当前页最多显示的记录数
		pageBean.setPageSize(pageSize);
		
		Integer totalCount = linkManDao.findByCount(criteria);
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
		List<LinkMan> list = linkManDao.findByPage(criteria,firstResult,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public void save(LinkMan linkMan) {
		 linkManDao.save(linkMan);
	}

	@Override
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
	}

	@Override
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}

	@Override
	public LinkMan findById(Long lkm_id) {
		return linkManDao.findById(lkm_id);
	}

}
