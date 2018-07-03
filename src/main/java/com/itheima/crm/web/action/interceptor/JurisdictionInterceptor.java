package com.itheima.crm.web.action.interceptor;

import com.itheima.crm.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

public class JurisdictionInterceptor  extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
       //判断session中是否已经有用户对象
        User user = (User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
        if(user!=null){
            //放行
            return invocation.invoke();
        }else{
            //获得ActionSupprort类
            ActionSupport actionSupport = (ActionSupport) invocation.getAction();
            actionSupport.addActionError("请登录！否则没有权限访问！");
            return actionSupport.LOGIN;
        }
    }
}
