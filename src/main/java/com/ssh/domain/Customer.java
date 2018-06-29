package com.ssh.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Customer {
    private Long cust_id;
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
}
