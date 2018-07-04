package com.itheima.crm.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

@Data
public class Customer {
    @Id
    private Long cust_id;
    private String cust_name;
    //	private String cust_source;
//	private String cust_industry;
//	private String cust_level;
    // 在多的一端的配置，是一个对象
    @ManyToOne
    @JoinColumn(name = "cust_source")
    private BaseDict baseDictSource; // 客户来源
    @ManyToOne
    @JoinColumn(name = "cust_industry")
    private BaseDict baseDictIndustry; // 客户所属行业
    @ManyToOne
    @JoinColumn(name = "cust_level")
    private BaseDict baseDictLevel;// 客户级别
    private String cust_phone;
    private String cust_mobile;
    // 上传的图片路径（用于存放文件资料，做下载）
    private String cust_image;

    // 一的的一端
    @OneToMany(mappedBy = "customer")
    Set<LinkMan> linkMans = new HashSet<>();
    @Transient//不生成有关表的数据
    private InputStream inputStream;
}
