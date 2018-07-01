package com.ssh.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Data
public class Customer {
    private Long Cust_id;
    private String cust_name;
    //    private String cust_source;
    //    private String cust_industry;
    //    private String cust_level;
    // 放的是一的一方的对象:
    private BaseDict baseDictSource;
    private BaseDict baseDictIndustry;
    private BaseDict baseDictLevel;
    private String cust_phone;
    private String cust_mobile;
    private String cust_image;
    // 联系人的集合
    private Set<LinkMan> linkMans = new HashSet<>();
}
