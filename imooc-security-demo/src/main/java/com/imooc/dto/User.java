package com.imooc.dto;

import io.swagger.annotations.ApiModelProperty;

public class User {

	@ApiModelProperty(value = "用户名")
	private String userName;
	
	@ApiModelProperty(value = "密码")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
