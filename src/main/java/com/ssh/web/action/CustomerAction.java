package com.ssh.web.action;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.domain.Customer;
import com.ssh.service.CustomerService;

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
}