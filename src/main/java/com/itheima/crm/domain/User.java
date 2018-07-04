package com.itheima.crm.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="sys_user")
public class User {
	@Id
	private Long user_id;
	private String user_code;
	private String user_name;
	private String user_password;
	private String user_state;
}
