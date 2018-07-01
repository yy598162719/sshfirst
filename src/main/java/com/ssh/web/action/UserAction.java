package com.ssh.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.domain.User;
import com.ssh.service.UserService;
import com.ssh.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;

public class UserAction extends ActionSupport implements ModelDriven<User> {

    private UserService userService;
    // 模型驱动使用的对象
    @Autowired
    private User user;

    @Override
    public User getModel() {
        return user;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String regist() {
        return "regist";
    }

    public String registSave() {
        user.setUserState("1");//1表示正在使用0表示被封号了
        user.setUserPassword(MD5Utils.md5(user.getUserPassword()));
        userService.regist(user);
        return "registSave";
    }

    public String login() {
        // 接收参数：
        // 调用业务层:
        User existUser = userService.login(user);
        // 判断是否登录成功:
        if (existUser == null) {
            // 登录失败:
            this.addActionError("用户名或密码错误！");
            return LOGIN;
        } else {
            // 登录成功:
            ActionContext.getContext().getSession().put("existUser", existUser);
            return SUCCESS;
        }
    }
}
