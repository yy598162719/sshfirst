package com.itheima.crm.web.action;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import org.apache.struts2.ServletActionContext;

import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.service.BaseDictService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDictAction extends BaseAction<BaseDict>{
	@Autowired
	BaseDictService baseDictService;

	// 使用ajax，使用数据字典的编号，查询数据字典，返回对应字典编号的集合列表
	public String findByDictTypeCode() throws IOException{
		List<BaseDict> list = baseDictService.findByDictTypeCode(model.getDict_type_code());
		// 将list集合转换成json字符串
		JSONArray jsonArray = (JSONArray) JSONArray.toJSON(list);
		System.out.println(jsonArray.toString());
		// json的字符串响应到页面
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString()); // json字符串中文
		// 不需要返回页面
		return NONE;
	}
}
