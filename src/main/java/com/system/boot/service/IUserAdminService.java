package com.system.boot.service;

import com.system.boot.contral.req.UserAdminLoginRequest;
import com.system.boot.contral.req.UserLoginOutRequest;
import com.system.boot.contral.resp.UserLoginResponse;

public interface IUserAdminService {
	
	UserLoginResponse login(UserAdminLoginRequest request);
	
	boolean loginOut(UserLoginOutRequest request);

}
