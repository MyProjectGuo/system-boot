package com.system.boot.utils;

public enum Constants {
	
	DEFAULT_PWD				("123456" , "默认密码"),
	NO_LOGIN				("904" , "登录失效"),	
	NO_FOUND				("404" , "资源未找到"),	
	SERVICE_ERROR			("500" , "服务器内部错误"),
	VALIDATE_ERROR			("801" , "数据校验失败"),
	LOGIN_PWSSWORD_ERROR	("401" , "用户名或者密码为空");
	
	
	
	
	
	private String code;
	private String msg;

	private Constants(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
