package com.system.boot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.system.boot.contral.req.QueryUserRequest;
import com.system.boot.contral.req.UpdateMenuInfoRequest;
import com.system.boot.contral.req.UpdateUserRequest;
import com.system.boot.contral.req.UserAdminLoginRequest;
import com.system.boot.contral.req.UserLoginOutRequest;
import com.system.boot.handler.ResponseResult;
import com.system.boot.handler.ServiceException;
import com.system.boot.handler.annotation.LoginRequired;
import com.system.boot.model.User;
import com.system.boot.service.IAdminMenuService;
import com.system.boot.service.IUserAdminService;
import com.system.boot.service.IUserDBService;
import com.system.boot.utils.Constants;
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
	@Autowired
	private IUserAdminService userAdminService;
	@Autowired
	private IAdminMenuService adminMenuService;
	@Autowired
	private HazelcastInstance hazelcastInstance;
	
	

	@LoginRequired
	@RequestMapping(value = "user_admin/list", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public ResponseResult getUser(@RequestBody QueryUserRequest query, HttpServletRequest request) {

		return ResponseResultUtils.success(userDBService.getAllUser(query));

	}

	@LoginRequired
	@RequestMapping(value = { "user_admin/lock", "user_admin/resetPwd", "user_admin/save" }, produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public ResponseResult updateInfo(@RequestBody UpdateUserRequest request, HttpServletRequest req) {
		request.setIpAddress(IpUtil.getIpAddr(req));
		return ResponseResultUtils.success(userDBService.updateUserInfo(request));

	}

	@RequestMapping(value = "user_admin/login", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public ResponseResult login(@RequestBody UserAdminLoginRequest request, HttpServletRequest req) {

		return ResponseResultUtils.success(userAdminService.login(request));

	}

	@RequestMapping(value = "user_admin/loginout", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public ResponseResult login(@RequestBody UserLoginOutRequest request, HttpServletRequest req) {
		return ResponseResultUtils.success(userAdminService.loginOut(request));

	}

	@LoginRequired
	@RequestMapping(value = "user_admin/getMenu", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public ResponseResult getMenu(HttpServletRequest req) {

		User user = (User) req.getAttribute("user_info");
		return ResponseResultUtils.success(adminMenuService.queryAdminMenuByUser(user));

	}

	@LoginRequired
	@RequestMapping(value = "user_admin/getMenu/{id}", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
	public ResponseResult getMenu(@PathVariable Long id, HttpServletRequest req) {
		if (id == null) {
			throw new ServiceException(Integer.valueOf(Constants.NO_FOUND.getCode()), Constants.NO_FOUND.getMsg());
		}
		return ResponseResultUtils.success(adminMenuService.querySubMenuById(id));

	}

	@LoginRequired
	@RequestMapping(value = "user_admin/getParenterMenu", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public ResponseResult queryParentMenu(HttpServletRequest req) {
		return ResponseResultUtils.success(adminMenuService.queryParentMenu());

	}

	@LoginRequired
	@RequestMapping(value = {"user_admin/menu/delete","user_admin/menu/update"}, produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public ResponseResult delete(@RequestBody @Valid UpdateMenuInfoRequest request, HttpServletRequest req) {
		return ResponseResultUtils.success(adminMenuService.updateMenuByInfo(request));

	}
	@RequestMapping(value = {"test/hazelcastInstance"}, produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public ResponseResult hazelcastInstance( HttpServletRequest req) {
		
		 IMap<Object, Object>  map = hazelcastInstance.getMap("my-map");
		map.put("111", "测试");
		
		return ResponseResultUtils.success(map.get("111"));
		
	}
	
	
}
