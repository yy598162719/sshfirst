package com.ssh.web.action;

import java.io.File;
import java.io.IOException;

import com.opensymphony.xwork2.ActionContext;
import com.ssh.service.CustomerService;
import com.ssh.utils.UploadUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import com.ssh.domain.Customer;
import com.ssh.domain.PageBean;
import com.ssh.utils.UploadUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
    // 文件上传的三个属性:
    private File upload; // 代表的是上传文件
    private String uploadFileName; // 代表的是文件名称
    private String uploadContentType; // 代表的是文件类型

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    // 模型驱动使用的对象
    @Autowired
    private Customer customer;

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
    public String add() throws IOException {
        if (upload != null) {
            String path = "d://upload";
            //处理上传图片
            String upload = UploadUtils.upload(path, this.upload, uploadFileName);
            //设置cusomer的图片路径
            customer.setCust_image(upload);
        }
        customerService.add(customer);
        return "addSuccess";
    }

    public String saveUI() {
        return "saveUI";
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
        // 调用业务层完成查询：
        // 创建一个离线条件查询对象:
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
        // 设置名称
        if (customer.getCust_name() != null && StringUtils.isNotBlank(customer.getCust_name())) {
            detachedCriteria.add(Restrictions.like("cust_name", customer.getCust_name(), MatchMode.ANYWHERE));
        }
        // 设置客户的来源
        if (customer.getBaseDictSource() != null && StringUtils.isNotBlank(customer.getBaseDictSource().getDict_id())) {
            detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
        }
        // 设置客户的级别
        if (customer.getBaseDictLevel() != null && StringUtils.isNotBlank(customer.getBaseDictLevel().getDict_id())) {
            detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
        }
        // 设置客户的行业
        if (customer.getBaseDictIndustry() != null && StringUtils.isNotBlank(customer.getBaseDictIndustry().getDict_id())) {
            detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
        }
        // 调用业务层查询:
        PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria, currPage, pageSize);
        // 将pageBean带到页面，将pageBean放置到栈顶 ：
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findByPage";
    }

    /**
     * 根据id删除一个客户
     */
    public String delete() {
        //先根据id查询出要删除的客户
        customer = customerService.findById(customer.getCust_id());
        String cust_image = customer.getCust_image();
        if (cust_image != null) {
            //删除图片
            File file = new File(cust_image);
            if (file.exists()) {
                file.delete();
            }
        }
        customerService.delete(customer);
        return "deleteSuccess";
    }

    /**
     * 修改客户的回显
     */
    public String update() {
        customer = customerService.findById(customer.getCust_id());
        ActionContext.getContext().getValueStack().push(customer);
        return "updateUI";
    }

    /**
     * 修改客户
     */
    public String edit() throws IOException {
        if (upload != null) {
            //如果用户更新了图片
            String cust_image = customer.getCust_image();
            if (cust_image != null) {
                //删除旧图片
                File file = new File(cust_image);
                if (file.exists()) {
                    file.delete();
                }
            }
            //上传新图片
            String path = "d:/upload";
            String upload = UploadUtils.upload(path, this.upload, uploadFileName);
            customer.setCust_image(upload);
        }
        //修改客户
        customerService.edit(customer);
        return "updateSuccess";
    }
}
