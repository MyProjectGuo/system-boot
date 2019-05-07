package com.system.boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.boot.dao.UserMapper;
import com.system.boot.model.User;
import com.system.boot.service.IUserDBService;

@Service("userDBService")
public class UserDBServiceImpl implements IUserDBService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserById(Long userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	@Transactional
	public boolean deleteUserById(Long userId) {

		return userMapper.deleteByPrimaryKey(userId) > 0;
	}

}
