package com.system.boot.utils;

public enum Constants {
	
	DEFAULT_PWD("123456" , "默认密码");
	
	
	
	
	
	
	
	
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
