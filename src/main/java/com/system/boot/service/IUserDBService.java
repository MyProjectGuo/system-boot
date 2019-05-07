package com.system.boot.service;

import com.system.boot.model.User;

public interface IUserDBService {
	
	User getUserById(Long userId);
	
	boolean deleteUserById(Long userId);

}
