package com.itheima.crm.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
@Data
public class Customer {

	private Long cust_id;
	private String cust_name;
//	private String cust_source;
//	private String cust_industry;
//	private String cust_level;
	// 在多的一端的配置，是一个对象
	private BaseDict baseDictSource; // 客户来源
	private BaseDict baseDictIndustry; // 客户所属行业
	private BaseDict baseDictLevel;// 客户级别
	private String cust_phone;
	private String cust_mobile;
	
	// 上传的图片路径（用于存放文件资料，做下载）
	private String cust_image;
	
	// 一的的一端
	Set<LinkMan> linkMans = new HashSet<>();

	private InputStream inputStream;


}
