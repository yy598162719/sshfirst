package com.itheima.crm.web.action;

import com.alibaba.fastjson.JSONArray;
import com.itheima.crm.domain.Customer;
import org.apache.struts2.ServletActionContext;

import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/")
public class UserAction extends BaseAction<User> {
    @Autowired
    private UserService userService;

    /**
     * <!-- 用户登录 -->
     * <action name="user_*" class="userAction" method="{1}">
     * <result name="regist">/regist.jsp</result>
     * <result name="registSave" type="redirect">/login.jsp</result>
     * <result name="loginSuccess" type="redirect">/index.jsp</result>
     * <interceptor-ref name="privilegeInterceptor">
     * <param name="excludeMethods">regist,login,registSave</param>
     * </interceptor-ref>
     * <interceptor-ref name="defaultStack"/>
     * </action>
     *
     * @return
     */
    // 跳转到注册页面
    @Action(value = "user_regist", results = {@Result(name = "regist", location = "/regist.jsp")})
    public String regist() {
        return "regist";
    }

    // 注册保存
    @Action(value = "user_registSave", results = {@Result(name = "registSave", location = "/login.jsp",type="redirect")})
    public String registSave() {
        userService.save(model);
        return "registSave";
    }

    // 登录
    @Action(value = "user_login", results = {@Result(name = "login", location = "/login.jsp",type="redirect"),
            @Result(name="loginSuccess",location = "/index.jsp",type="redirect")})
    public String login() {
        // 使用struts2的校验机制addActionError或者addFieldError
        // 使用登录名和密码，查询用户信息
        User existUser = userService.findUserByUserCodeAndPassword(model);
        // 说明当前用户名和密码输入错误
        if (existUser == null) {
            this.addActionError("输入的用户名和密码错误");
            return "login";
        } else {
            if (!existUser.getUser_state().equals("1")) {
                this.addActionError("您操作的用户已经登录权限已经过期");
                return "login";
            } else {
                // 存放到Session
                ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
            }
        }
        return "loginSuccess";
    }

    /**
     * 查询所有业务员名字
     * @return
     */
    @Action(value = "user_ findAllUserName", results = {@Result(name = "successJson", type="json")})//全局响应
    public String findAllUserName() throws IOException {
        // 调用业务层:
        List<User> list = userService.findAll();
        // 将list放置到栈顶
        ServletActionContext.getContext().getValueStack().push(list);
        return "successJson";
    }
}
