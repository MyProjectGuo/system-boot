package com.system.boot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.system.boot.contral.req.QueryUserRequest;
import com.system.boot.contral.req.UpdateUserRequest;
import com.system.boot.handler.ResponseResult;
import com.system.boot.service.IUserDBService;
import com.system.boot.utils.IpUtil;
import com.system.boot.utils.ResponseResultUtils;

/**
 * 后台 adimn ，用户管理
 * 
 * @date 2019年6月11日11:01:18
 * @author guoqingbin
 *
 */
@RestController
public class UserAdminController {

	@Autowired
	private IUserDBService userDBService;

	@RequestMapping(value = "user_admin/list", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public ResponseResult getUser(@RequestBody QueryUserRequest query) {

		return ResponseResultUtils.success(userDBService.getAllUser(query));

	}

	@RequestMapping(value = {"user_admin/lock","user_admin/resetPwd","user_admin/save"},  produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public ResponseResult updateInfo(@RequestBody UpdateUserRequest request , HttpServletRequest req) {
		request.setIpAddress(IpUtil.getIpAddr(req));
		return ResponseResultUtils.success(userDBService.updateUserInfo(request));

	}

}
