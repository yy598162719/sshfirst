package com.itheima.crm.web.action;

import com.itheima.crm.domain.PageBean;
import com.itheima.crm.domain.SaleVisit;
import com.itheima.crm.service.SaleVisitService;
import com.opensymphony.xwork2.ActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
@Scope("prototype")
public class SaleVisitAction extends BaseAction<SaleVisit> {
    @Autowired
    private SaleVisitService saleVisitService;

    // 接收拜访截止时间:
    private Date visit_end_time;

    public void setVisit_end_time(Date visit_end_time) {
        this.visit_end_time = visit_end_time;
    }

    public Date getVisit_end_time() {
        return visit_end_time;
    }
    public String findByPage() {
        //创建离线查询条件
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
        if(model.getVisit_time()!=null){
            //筛选大于等于起始时间的
            detachedCriteria.add(Restrictions.ge("visit_time",model.getVisit_time()));
        }
        if (model.getVisit_nexttime() != null) {
            //筛选小于等于最后时间的
            detachedCriteria.add(Restrictions.le("visit_time", model.getVisit_time()));
        }
        // 调用业务层查询;
        PageBean<SaleVisit> pageBean = saleVisitService.findByPage(detachedCriteria, currPage, pageSize);
        //放入值栈
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findByPage";
    }

    /**
     * 返回添加页面
     * @return
     */
    public String saveUI() {
        return "saveUI";
    }

    /**
     * 添加客户拜访记录的方法:save
     */
    public String save() {
        saleVisitService.save(model);
        return "saveSuccess";
    }
}
