package com.itheima.crm.web.action;

import com.alibaba.fastjson.JSONArray;
import com.itheima.crm.domain.Customer;
import org.apache.struts2.ServletActionContext;

import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	@Autowired
	private UserService userService;
	
	// 跳转到注册页面
	public String regist(){
		return "regist";
	}

	// 注册保存
	public String registSave(){
		userService.save(model);
		return "registSave";
	}
	
	// 登录
	public String login(){
		// 使用struts2的校验机制addActionError或者addFieldError
		// 使用登录名和密码，查询用户信息
		User existUser = userService.findUserByUserCodeAndPassword(model);
		// 说明当前用户名和密码输入错误
		if(existUser==null){
			this.addActionError("输入的用户名和密码错误");
			return "login";
		}
		else{
			if(!existUser.getUser_state().equals("1")){
				this.addActionError("您操作的用户已经登录权限已经过期");
				return "login";
			}
			else{
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
	public String findAllUserName() throws IOException {
		// 调用业务层:
		List<User> list = userService.findAll();
		// 将list放置到栈顶
		ServletActionContext.getContext().getValueStack().push(list);
		return "successJson";
	}
}
