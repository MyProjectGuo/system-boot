package com.system.boot.user.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.boot.handler.ResponseResult;
import com.system.boot.user.contral.req.CreateUserRequest;
import com.system.boot.utils.ResponseResultUtils;
import com.system.boot.utils.ValidatorUtil;

@RestController
public class UserController {
	
	@RequestMapping("/unlogin")
	public ResponseResult unlogin(@RequestBody @Valid CreateUserRequest request) throws Exception {
		ValidatorUtil.validate(request);
		return ResponseResultUtils.success(request);
	}
	
}
