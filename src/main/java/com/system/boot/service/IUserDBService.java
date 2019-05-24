package com.system.boot.service;

import java.util.List;

import com.system.boot.contral.req.QueryUserRequest;
import com.system.boot.model.User;

public interface IUserDBService {
	
	User getUserById(Long userId);
	
	boolean deleteUserById(Long userId);
	
	List<User> getAllUser(QueryUserRequest request);

}
