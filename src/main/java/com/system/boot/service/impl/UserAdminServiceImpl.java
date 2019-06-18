package com.system.boot.service.impl;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.system.boot.contral.req.UserAdminLoginRequest;
import com.system.boot.contral.req.UserLoginOutRequest;
import com.system.boot.contral.resp.UserLoginResponse;
import com.system.boot.handler.ServiceException;
import com.system.boot.model.User;
import com.system.boot.service.IUserAdminService;
import com.system.boot.service.IUserDBService;
import com.system.boot.utils.Constants;
import com.system.boot.utils.SystemConstants;

@Service("userAdminService")
public class UserAdminServiceImpl implements IUserAdminService {

	@Autowired
	private IUserDBService userDBService;

	@Autowired
	@Qualifier("stringRedisTemplate")
	private StringRedisTemplate template;

	ExecutorService exec = Executors.newCachedThreadPool();
	
	@Override
	public UserLoginResponse login(UserAdminLoginRequest request) {

		String token = null;

		User user = userDBService.getUserByMobile(request.getUserName());
		if (user == null) {
			throw new ServiceException(Integer.valueOf(Constants.NO_FOUND.getCode()), "用户不存在");
		}
		if (StringUtils.isBlank(user.getPasswd())) {
			throw new ServiceException(Integer.valueOf(Constants.LOGIN_PWSSWORD_ERROR.getCode()), "请联系管理员设置密码");

		}
		if (!DigestUtils.md5Hex(request.getUserPwd()).equals(user.getPasswd())) {
			throw new ServiceException(Integer.valueOf(Constants.VALIDATE_ERROR.getCode()), "密码错误");
		}
		token = UUID.randomUUID().toString();
		template.opsForValue().set(token, JSONObject.toJSONString(user), 72, TimeUnit.HOURS);

		UserLoginResponse response = UserLoginResponse.toUserResponse(user);
		response.setToken(token);
		return response;
	}

	@Override
	public boolean loginOut(UserLoginOutRequest request) {
		exec.execute(new Runnable() {
			@Override
			public void run() {
				String userToken = template.opsForValue().get(request.getLoginToken());
				User user = JSONObject.parseObject(userToken, User.class);
				if (user == null) {
					template.delete(SystemConstants.MENU_TOKEN_KEY_ALL);
				} else {
					template.delete(SystemConstants.MENU_TOKEN_KEY_USERID + user.getId());
				}
				template.delete(request.getLoginToken());
			}
		});

		return true;

	}

	
	
}
