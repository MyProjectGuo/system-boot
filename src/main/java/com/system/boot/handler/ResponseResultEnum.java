package com.system.boot.handler;

public enum ResponseResultEnum {
	
	SUCCESS(200, "success"), 
	FAIL(100, "fail"), 
	EXCEPTION(300, "system error"), 
	UNLOGIN(201, "log out"),
	NOT_FOUND(404, "not found");

	private Integer code;
	private String msg;

	private ResponseResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
