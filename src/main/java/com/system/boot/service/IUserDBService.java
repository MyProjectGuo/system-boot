package com.system.boot.service;

import com.system.boot.contral.req.QueryUserRequest;
import com.system.boot.contral.req.UpdateUserRequest;
import com.system.boot.contral.resp.QueryUserAdminResponse;
import com.system.boot.model.User;

public interface IUserDBService {
	
	User getUserById(Long userId);
	
	boolean deleteUserById(Long userId);
	
	QueryUserAdminResponse getAllUser(QueryUserRequest request);
	
	boolean updateUserInfo(UpdateUserRequest request);

}
