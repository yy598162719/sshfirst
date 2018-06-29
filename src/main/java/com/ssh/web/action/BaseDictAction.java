package com.ssh.web.action;

import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.domain.BaseDict;
import com.ssh.service.BaseDictService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {
    private BaseDict baseDict = new BaseDict();
    @Autowired
    private BaseDictService baseDictService;

    public String findByTypeCode() throws IOException {
        // 调用业务层:
        List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());
        // 将list转成JSON
        String jsonString = JSONArray.toJSONString(list);
        System.out.println(jsonString);
        // 获得Response:
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonString);
        return NONE;
    }

    @Override
    public BaseDict getModel() {
        return baseDict;
    }

    public void setBaseDictService(com.ssh.service.impl.BaseDictServiceImpl baseDictService) {
    }
}
