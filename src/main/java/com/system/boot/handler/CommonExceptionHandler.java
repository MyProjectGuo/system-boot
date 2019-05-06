package com.system.boot.handler;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseResult defaultException(Exception e) {

		if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {

			return ResponseResult.builder().code(ResponseResultEnum.NOT_FOUND.getCode()).message(e.getMessage()).build();
		}

		return ResponseResult.builder().code(ResponseResultEnum.EXCEPTION.getCode()).message(ResponseResultEnum.EXCEPTION.getMsg()).build();

	}

	@ExceptionHandler(value = ServiceException.class)
	@ResponseBody
	public ResponseResult serviceException(HttpServletRequest request, ServiceException e) {
		e.printStackTrace();
		Integer code = e.getCode();
		String message = e.getMessage();

		if (e.getCode() == null) {
			code = ResponseResultEnum.EXCEPTION.getCode();
		}
		if (e.getMessage() == null) {
			message = ResponseResultEnum.EXCEPTION.getMsg();
		}
		return ResponseResult.builder().code(code).message(message).build();
	}

	@ExceptionHandler(BindException.class)
	@ResponseBody
	public ResponseResult handleBindException(BindException ex) {
		// 校验 除了 requestbody 注解方式的参数校验 对应的 bindingresult 为
		// BeanPropertyBindingResult
		FieldError fieldError = ex.getBindingResult().getFieldError();
		log.info("必填校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
		return ResponseResult.builder().code(501).message(fieldError.getDefaultMessage()).build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseResult methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		// 校验 除了 requestbody 注解方式的参数校验 对应的 bindingresult 为
		// BeanPropertyBindingResult
		FieldError fieldError = ex.getBindingResult().getFieldError();
		log.error("必填校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
		return ResponseResult.builder().code(501).message(fieldError.getDefaultMessage()).build();
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseBody
	public ResponseResult noHandlerFoundException(NoHandlerFoundException ex) {
		return ResponseResult.builder().code(404).message(ex.getMessage()).build();
	}

}