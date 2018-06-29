package com.ssh.web.action;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.domain.Customer;
import com.ssh.domain.PageBean;
import com.ssh.service.CustomerService;
import org.hibernate.criterion.DetachedCriteria;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
    // 模型驱动使用的对象
    private Customer customer = new Customer();

    @Override
    public Customer getModel() {
        return customer;
    }

    // 按名称自动装配:
    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * 编写save方法：
     */
    public String add() {
        System.out.println("Action中的add方法执行了...");
        customerService.add(customer);
        return NONE;
    }
    public String saveUI() {
        return "saveUI";
    }
    // 接收当前页数:
    private Integer currPage = 1;

    public void setCurrPage(Integer currPage) {
        if(currPage == null){
            currPage = 1;
        }else{
            this.currPage = currPage;
        }
    }

    // 接收每页显示记录数:
    private Integer pageSize = 3;

    public void setPageSize(Integer pageSize) {
        if(pageSize == null){
            this.pageSize = 3;
        }else{
            this.pageSize = pageSize;
        }
    }

    /**
     * 分页查询客户数据：
     */
    public String findByPage(){
        // 调用业务层完成查询：
        // 创建一个离线条件查询对象:
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);

        // 调用业务层查询:
        PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria,currPage,pageSize);
        // 将pageBean带到页面，将pageBean放置到栈顶 ：
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findByPage";
    }
    }
