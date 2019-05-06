package com.system.boot.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 2108497067236861424L;
	
	private Integer code;

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(Integer code, String msg) {
		super(msg);
		this.code = code;
	}
}
