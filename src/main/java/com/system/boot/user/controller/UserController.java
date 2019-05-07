package com.system.boot.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.system.boot.handler.ResponseResult;
import com.system.boot.service.IUserDBService;
import com.system.boot.utils.ResponseResultUtils;

@RestController
@RequestMapping(value = "/user_admin", method = RequestMethod.POST)
public class UserController {

	@Autowired
	private IUserDBService userDBService;

	@RequestMapping("/getUserList")
	public ResponseResult getUserList(HttpServletRequest request) throws Exception {

		return ResponseResultUtils.success(userDBService.getAllUser());
	}
}
