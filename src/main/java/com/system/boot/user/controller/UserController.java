package com.system.boot.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.boot.handler.ResponseResult;
import com.system.boot.service.IUserDBService;
import com.system.boot.user.contral.req.CreateUserRequest;
import com.system.boot.utils.ResponseResultUtils;

@RestController
public class UserController {

	@Autowired
	//@Qualifier("userDBService")
	private IUserDBService userDBService;

	@RequestMapping("/unlogin")
	public ResponseResult unlogin(@RequestBody @Valid CreateUserRequest request) throws Exception {

		return ResponseResultUtils.success(userDBService.deleteUserById(2L));
	}

}
