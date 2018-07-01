package com.ssh.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LinkMan {
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
    // 联系人的客户对象
    private Customer customer;
}
