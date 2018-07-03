package com.itheima.crm.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.util.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class CustomerAction extends BaseAction<Customer> {
    @Autowired
    private CustomerService customerService;

    // 跳转到新增页面
    public String saveUI() {

        return "saveUI";
    }

    // 属性驱动的方式，获取上传的文件
    // 上传的文件
    private File upload;
    // 上传的文件名称
    private String uploadFileName;
    // 上传的文件类型
    private String uploadContentType;

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    // 保存
    public String save() throws IOException {
//		System.out.println("上传的文件："+upload);
//		System.out.println("上传的文件名："+uploadFileName);
//		System.out.println("上传的文件类型："+uploadContentType);
        if (upload != null) {
            String basePath = "C:\\upload";
            // 生成一个文件名
            String uuidFileName = UploadUtils.getUUIDFileName(uploadFileName);
            // 生成保存的文件路径（时间戳、hashcode）(/8/5)
            String path = UploadUtils.getPath(uuidFileName);
            // 创建文件夹
            String pathStr = basePath + path;
            File filePathStr = new File(pathStr);
            if (!filePathStr.exists()) {
                filePathStr.mkdirs();
            }
            // 文件上传
            // 目标文件
            File destFile = new File(pathStr + "/" + uuidFileName);
            // 将源文件拷贝到目标文件
            FileUtils.copyFile(upload, destFile);
            // 将路径设置到Customer对象中的cust_image下
            model.setCust_image(pathStr + "/" + uuidFileName);
        }
        customerService.save(model);
        return "save";
    }

    // 客户查询（分页查询）
    public String findByPage() {
        // 离线查询（查询所有）
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
        // 添加查询条件
        // 客户名称
        if (StringUtils.isNotBlank(model.getCust_name())) {
            criteria.add(Restrictions.like("cust_name", model.getCust_name(), MatchMode.ANYWHERE));
        }
        // 客户级别
        if (model.getBaseDictLevel() != null && StringUtils.isNotBlank(model.getBaseDictLevel().getDict_id())) {
            criteria.add(Restrictions.eq("baseDictLevel.dict_id", model.getBaseDictLevel().getDict_id()));
        }
        // 客户来源
        if (model.getBaseDictSource() != null && StringUtils.isNotBlank(model.getBaseDictSource().getDict_id())) {
            criteria.add(Restrictions.eq("baseDictSource.dict_id", model.getBaseDictSource().getDict_id()));
        }
        // 客户所属行业
        if (model.getBaseDictIndustry() != null && StringUtils.isNotBlank(model.getBaseDictIndustry().getDict_id())) {
            criteria.add(Restrictions.eq("baseDictIndustry.dict_id", model.getBaseDictIndustry().getDict_id()));
        }

        PageBean<Customer> pageBean = customerService.findByPage(criteria, currPage, pageSize);
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

    /**
     * 删除
     */
    public String delete() {
        // 使用id查询一下
        Customer c = customerService.findById(model.getCust_id());
        // 删除附件
        String cust_image = c.getCust_image();
        if (StringUtils.isNotBlank(cust_image)) {
            File file = new File(cust_image);
            if (file.exists()) {
                file.delete();
            }
        }

        // 删除 数据库
        customerService.delete(c);
        return "deleteSuccess";
    }

    // 跳转到修改页面
    public String updateUI() {
        // 使用id，查询对象
        Customer c = customerService.findById(model.getCust_id());
        // 表单回显，使用struts中的UI标签，从值栈的栈顶完成回显
        ServletActionContext.getContext().getValueStack().push(c);
        return "updateUI";
    }

    /**
     * 修改保存
     */
    public String update() throws IOException {
        // <s:hidden name="cust_image"></s:hidden>
        // 选择了一个新的附件
        if (upload != null) {
            // 删除之前的附件
            File file = new File(model.getCust_image());
            if (file.exists()) {
                file.delete();
            }
            // 上传一个新的附件
            String basePath = "C:\\upload";
            // 生成一个文件名
            String uuidFileName = UploadUtils.getUUIDFileName(uploadFileName);
            // 生成保存的文件路径（时间戳、hashcode）(/8/5)
            String path = UploadUtils.getPath(uuidFileName);
            // 创建文件夹
            String pathStr = basePath + path;
            File filePathStr = new File(pathStr);
            if (!filePathStr.exists()) {
                filePathStr.mkdirs();
            }
            // 文件上传
            // 目标文件
            File destFile = new File(pathStr + "/" + uuidFileName);
            // 将源文件拷贝到目标文件
            FileUtils.copyFile(upload, destFile);
            // 将路径设置到Customer对象中的cust_image下
            model.setCust_image(pathStr + "/" + uuidFileName);
        }
        customerService.update(model);
        return "updateSuccess";
    }

    /**
     * 文件下载
     */
    public String download() throws Exception {
        // 使用客户ID，查询客户信息
        Customer c = customerService.findById(model.getCust_id());
        // C:/upload/1/2/bacdc88b87864952bd5740563ec4e573.jpg
        String cust_image = c.getCust_image();
        // 获取文件名(bacdc88b87864952bd5740563ec4e573.jpg)
        String filename = cust_image.substring(cust_image.lastIndexOf("/") + 1);
        // 通过文件名获取类型
        String contentType = ServletActionContext.getServletContext().getMimeType(filename);
        ServletActionContext.getRequest().setAttribute("contentType", contentType);
        ServletActionContext.getRequest().setAttribute("filename", filename);

        // 将文件的输入流，存放到值栈（模型驱动）
        File file = new File(cust_image);
        InputStream inputStream = new FileInputStream(file);
        model.setInputStream(inputStream);
        return "download";
    }

    /**
     * 查询所有客户名字
     * @return
     */
    public String findAllCustomerName() throws IOException {
        List<Customer> list = customerService.findAll();
        // 如何转换成json，将list放置到栈顶
        ServletActionContext.getContext().getValueStack().push(list);
        return "successJson";
    }
}
