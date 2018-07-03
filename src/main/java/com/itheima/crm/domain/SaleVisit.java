package com.itheima.crm.domain;

import lombok.Data;

import java.util.Date;
@Data
public class SaleVisit {
    private Long visit_id;
    private Date visit_time;
    private String visit_addr;
    private String visit_detail;
    private Date visit_nexttime;

    // 放置业务员对象: 与用户多对一
    private User user;
    // 放置拜访客户对象: 与客户多对一
    private Customer customer;
}