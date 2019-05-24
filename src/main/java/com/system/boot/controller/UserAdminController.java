package com.system.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.system.boot.contral.req.QueryUserRequest;
import com.system.boot.handler.ResponseResult;
import com.system.boot.service.IUserDBService;
import com.system.boot.utils.ResponseResultUtils;

@RestController
public class UserAdminController {

	@Autowired
	private IUserDBService userDBService;

	@RequestMapping(value = "getUser_admin", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public ResponseResult getUser(@RequestBody QueryUserRequest query) {

		return ResponseResultUtils.success(userDBService.getAllUser(query));

	}

}
