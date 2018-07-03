package com.itheima.crm.web.action;

import com.itheima.crm.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.LinkManService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class LinkManAction extends BaseAction<LinkMan> {
@Autowired
    LinkManService linkManService;
@Autowired
    private CustomerService customerService;

    // 客户查询（分页查询）
    public String findByPage() {
        // 离线查询（查询所有）
        DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
        // 添加查询条件
        // 联系人姓名
        if (StringUtils.isNotBlank(model.getLkm_name())) {
            criteria.add(Restrictions.like("lkm_name", model.getLkm_name(), MatchMode.ANYWHERE));
        }
        // 性别
        if (StringUtils.isNotBlank(model.getLkm_gender())) {
            criteria.add(Restrictions.eq("lkm_gender", model.getLkm_gender()));
        }
        PageBean<LinkMan> pageBean = linkManService.findByPage(criteria, currPage, pageSize);
        // 放置到值栈（最简单放置到栈顶 ）
        /**
         * 放置到栈顶，OGNL表达式读取root栈，不需要添加#，直接读取5个属性就可以获取到值
         *  private Integer currPage; 	// 当前页
         private Integer pageSize; 	// 每页显示的记录数
         private Integer totalCount; // 总记录数
         private Integer totalPage; 	// 总页数
         private List<T> list;      	// 响应的结果
         */
        ServletActionContext.getContext().getValueStack().push(pageBean);
        return "findByPage";
    }

    public String saveUI() {
        // 直接完成查询：查询所有客户的信息。
        List<Customer> list = customerService.findAll();
        // 将list存入到值栈中，带到页面上:
        ActionContext.getContext().getValueStack().set("list", list);
        return "saveUI";
    }

    /**
     * 保存联系人的方法
     */
    public String save() {
// 调用业务层:
        linkManService.save(model);
        return "saveSuccess";
    }
    /**
     * 修改联系人回显
     */
    public String edit(){
        model=linkManService.findById(model.getLkm_id());
        List<Customer> list = customerService.findAll();
        // 将数据压入值栈：
        ActionContext.getContext().getValueStack().push(model);
        ActionContext.getContext().getValueStack().set("list", list);
        return "editSuccess";
    }

    /**
     * 修改联系人
     * @return
     */
    public String update(){
        linkManService.update(model);
        return "updateSuccess";
    }
    /**
     * 删除联系人
     */
    public String delete(){
        model = linkManService.findById(model.getLkm_id());
        linkManService.delete(model);
        return "deleteSuccess";
    }
}
