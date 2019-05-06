package com.system.boot.utils;

import com.system.boot.handler.ResponseResult;
import com.system.boot.handler.ResponseResultEnum;

public class ResponseResultUtils {
	public static ResponseResult success(Object data) {
		return ResponseResult.builder().code(ResponseResultEnum.SUCCESS.getCode()).message(ResponseResultEnum.SUCCESS.getMsg()).data(data).build();
	}

	public static ResponseResult success(Integer code, String msg) {
		if (null == msg)
			msg = ResponseResultEnum.SUCCESS.getMsg();
		if (null == code)
			code = ResponseResultEnum.SUCCESS.getCode();
		return ResponseResult.builder().code(code).message(msg).build();
	}

	public static ResponseResult success(ResponseResultEnum resultEnum) {
		return success(resultEnum.getCode(), resultEnum.getMsg());
	}

	public static ResponseResult fail(String msg) {
		if (null == msg)
			msg = ResponseResultEnum.FAIL.getMsg();
		return ResponseResult.builder().code(ResponseResultEnum.FAIL.getCode()).message(msg).build();
	}

	public static ResponseResult fail(Integer code, String msg) {
		if (null == msg)
			msg = ResponseResultEnum.FAIL.getMsg();
		if (null == code)
			code = ResponseResultEnum.FAIL.getCode();
		return ResponseResult.builder().code(code).message(msg).build();
	}

	public static ResponseResult fail(ResponseResultEnum resultEnum) {
		return fail(resultEnum.getCode(), resultEnum.getMsg());
	}
}
