package com.itheima.crm.domain;

import lombok.Data;

import javax.persistence.*;

@Table(name = "cst_linkman")
@Data
@Entity
public class LinkMan {
    @Id
    private Long lkm_id;
    private String lkm_name;
    private String lkm_gender;
    private String lkm_phone;
    private String lkm_mobile;
    private String lkm_email;
    private String lkm_qq;
    private String lkm_position;
    private String lkm_memo;
    private String lkm_hobby;
    // 多对一
    @ManyToOne
    @JoinColumn(name = "lkm_cust_id")//外键的名字
    private Customer customer;


}
