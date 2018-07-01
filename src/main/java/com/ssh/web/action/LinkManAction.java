package com.ssh.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.domain.Customer;
import com.ssh.domain.LinkMan;
import com.ssh.domain.PageBean;
import com.ssh.service.CustomerService;
import com.ssh.service.LinkManService;
import com.ssh.service.impl.LinkManServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
    @Autowired
    private LinkManService linkManService;
@Autowired
    private CustomerService customerService;

    @Autowired
    private LinkMan linkMan;
    @Override
    public LinkMan getModel() {
        return linkMan;
    }
    // 接收当前页数:
    private Integer currPage = 1;

    public void setCurrPage(Integer currPage) {
        if (currPage == null) {
            currPage = 1;
        } else {
            this.currPage = currPage;
        }
    }

    // 接收每页显示记录数:
    private Integer pageSize = 3;

    public void setPageSize(Integer pageSize) {
        if (pageSize == null) {
            this.pageSize = 3;
        } else {
            this.pageSize = pageSize;
        }
    }

    /**
     * 分页查询客户数据：
     */
    public String findByPage() {
        // 创建一个离线条件查询对象:
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
        // 设置名称
        if (linkMan.getLkm_name() != null && StringUtils.isNotBlank(linkMan.getLkm_name())) {
            detachedCriteria.add(Restrictions.like("lkm_name", linkMan.getLkm_name(), MatchMode.ANYWHERE));
        }
        // 性别
        if(StringUtils.isNotBlank(linkMan.getLkm_gender())){
            detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
        }
        // 调用业务层查询:
        PageBean<LinkMan> pageBean = linkManService.findByPage(detachedCriteria, currPage, pageSize);
        // 将pageBean带到页面，将pageBean放置到栈顶 ：
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findByPage";
    }
    /**
     * 跳转到添加页面的方法:saveUI
     */
    public String saveUI(){
        // 直接完成查询：查询所有客户的信息。
        List<Customer> list = customerService.findAll();
        // 将list存入到值栈中，带到页面上:
        ActionContext.getContext().getValueStack().set("list", list);
        return "saveUI";
    }
    public String save() {
        // 调用业务层:
        linkManService.save(linkMan);
        return "saveSuccess";
    }

}
