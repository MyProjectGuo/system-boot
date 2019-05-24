package com.system.boot.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.boot.contral.req.QueryUserRequest;
import com.system.boot.dao.UserExample;
import com.system.boot.dao.UserExample.Criteria;
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
	public List<User> getAllUser(QueryUserRequest request) {
		UserExample example = new UserExample();
		Criteria cri = example.createCriteria();
		//cri.andUserStatusEqualTo((byte) 1);
		if (StringUtils.isNotBlank(request.getUserName())) {
			cri.andLoginNameEqualTo(request.getUserName());
		}
		if (StringUtils.isNotBlank(request.getMobile())) {
			cri.andMobileLike("%" + request.getMobile() + "%");
		}

		if (StringUtils.isNotBlank(request.getStartTime()) && StringUtils.isNotBlank(request.getEndTime())) {
			
			
			

		}

		List<User> list = userMapper.selectByExample(example);
		return CollectionUtils.isEmpty(list) ? null : list;
	}

}
