package com.itheima.crm.service.impl;

import com.itheima.crm.dao.SaleVisitDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.domain.SaleVisit;
import com.itheima.crm.service.SaleVisitService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class SaleVisitServiceImpl implements SaleVisitService {
    @Autowired
    SaleVisitDao saleVisitDao;

    @Override
    public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        // 响应的结果PageBean，Service就是要完成对数据的封装
        PageBean<SaleVisit> pageBean = new PageBean<>();
        // 当前页
        pageBean.setCurrPage(currPage);
        // 当前页最多显示的记录数
        pageBean.setPageSize(pageSize);

        Integer totalCount = saleVisitDao.findByCount(detachedCriteria);
        // 总记录数
        pageBean.setTotalCount(totalCount);
        // 总页数
        Double tc = totalCount.doubleValue();
        double tp = Math.ceil(tc / pageSize); // ,math.ceil(x)返回大于等于参数x的最小整数,即对浮点数向上取整.
        Integer totalPage = (int) tp;
        pageBean.setTotalPage(totalPage);

        // 查询的集合（分页）
        // 当前页从第几条开始检索   当前页最多显示的记录数
        Integer firstResult = (currPage - 1) * pageSize;
        List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, firstResult, pageSize);
        pageBean.setList(list);

        return pageBean;
    }

    @Override
    public void save(SaleVisit model) {
        saleVisitDao.save(model);
    }
}
