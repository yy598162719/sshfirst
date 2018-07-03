package com.itheima.crm.domain;

import lombok.Data;

@Data
public class User {
	private Long user_id;
	private String user_code;
	private String user_name;
	private String user_password;
	private String user_state;
}
