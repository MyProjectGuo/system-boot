package com.system.boot.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.boot.dao.UserExample;
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
	public boolean deleteUserById(Long userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getAllUser() {
		UserExample example = new UserExample();
		example.createCriteria().andUserStatusEqualTo((byte) 1);
		List<User> list = userMapper.selectByExample(example);
		return CollectionUtils.isEmpty(list) ? null : list;
	}

}
