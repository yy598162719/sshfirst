package com.itheima.crm.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    protected T model; // 泛型，不能new？

    // 使用构造方法，泛型转换
    public BaseAction(){
        // 只要加载spring容器，每次调用Action的时候，就会创建每个Action的子类，通过子类调用父类，执行父类的构造方法
        Class childClass = this.getClass(); // CustomerAction<Customer>
        Type type = childClass.getGenericSuperclass(); // BaseAction<Customer>
        ParameterizedType parameterizedType = (ParameterizedType)type; // BaseAction<Customer>
        Class entityClass = (Class) parameterizedType.getActualTypeArguments()[0];  //Customer
        try {
            model = (T) entityClass.newInstance();// 实例化
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public T getModel() {
        return model;
    }

    // 属性 驱动，接收2个参数
    // 当前页（默认是1）
    protected Integer currPage = 1;
    public void setCurrPage(Integer currPage) {
        if(currPage==null){
            currPage = 1;
        }
        this.currPage = currPage;

    }
    // 当前页显示的记录数（每页显示3条）
    protected Integer pageSize = 3;

    public void setPageSize(Integer pageSize) {
        if(pageSize==null){
            pageSize = 3;
        }
        this.pageSize = pageSize;
    }
}