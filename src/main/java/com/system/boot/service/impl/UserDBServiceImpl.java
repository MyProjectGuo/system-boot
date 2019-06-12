package com.system.boot.service.impl;

import java.net.URLDecoder;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.boot.contral.req.QueryUserRequest;
import com.system.boot.contral.req.UpdateUserRequest;
import com.system.boot.contral.resp.QueryUserAdminResponse;
import com.system.boot.dao.UserExample;
import com.system.boot.dao.UserExample.Criteria;
import com.system.boot.dao.UserMapper;
import com.system.boot.model.User;
import com.system.boot.service.IUserDBService;
import com.system.boot.utils.Constants;
import com.system.boot.utils.PageInfo;

@Service("userDBService")
@Slf4j
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
	public QueryUserAdminResponse getAllUser(QueryUserRequest request) {
		QueryUserAdminResponse userList = new QueryUserAdminResponse();
		UserExample example = new UserExample();
		Criteria cri = example.createCriteria();
		if (StringUtils.isNotBlank(request.getLoginName())) {
			request.setLoginName(URLDecoder.decode(request.getLoginName()));
			cri.andLoginNameLike("%"+request.getLoginName()+"%");
		}
		if (StringUtils.isNotBlank(request.getMobile())) {
			cri.andMobileLike("%" + request.getMobile() + "%");
		}
		if(request.getUserStatus()!=null){
			
			cri.andUserStatusEqualTo(request.getUserStatus().byteValue());
		}
		
		PageInfo page = new PageInfo();
		page.setCurrentPage(request.getPage()==null?1:request.getPage());
		page.setPageSize(request.getLimit()==null?10:request.getLimit());
		
		int count = userMapper.countByExample(example);
		if (count == 0) {
			page.setTotalCount(0);
		}
		page.setTotalCount(count);
		page.calculatePageCount();
		example.setLimitEnd(page.getPageSize());
		example.setLimitStart(page.getLimitStart());
		example.setOrderByClause("updated_time desc ");
		List<User> list = userMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list)) {
			userList.setList(list);
		}
		userList.setPage(page);
		return userList;
	}

	@Override
	public boolean updateUserInfo(UpdateUserRequest request) {
		UserExample example = new UserExample();
		Criteria cri = example.createCriteria();
		if(StringUtils.isNotBlank(request.getLoginName())){
			request.setLoginName(URLDecoder.decode(request.getLoginName()));
		}
		if(StringUtils.isNotBlank(request.getEmail())){
			request.setEmail(URLDecoder.decode(request.getEmail()));
		}
		User user = null;
		if (request.getUserId() != null) {
			cri.andIdEqualTo(request.getUserId());
			user = getUserById(request.getUserId());
		}

		if (user == null && request.isAddUser()) {
			user = new User();
			user.setEmail(request.getEmail());
			user.setLoginName(request.getLoginName());
			user.setMobile(request.getMobile());
			user.setPasswd(DigestUtils.md5Hex(Constants.DEFAULT_PWD.getCode()));
			user.setUserStatus((byte) 1);
			user.setCreatedTime(System.currentTimeMillis());
			user.setUpdatedTime(System.currentTimeMillis());
			user.setRegisterIp(request.getIpAddress());
			return userMapper.insertSelective(user) > 0;
		}
		if (StringUtils.isNotBlank(request.getMobile())) {
			cri.andMobileEqualTo(request.getMobile());
		}
		if (request.getUserStatus() != null) {
			user.setUserStatus(request.getUserStatus().byteValue());
		}
		if (request.isResetPwd()) {
			user.setPasswd(DigestUtils.md5Hex(Constants.DEFAULT_PWD.getCode()));
		}
		if (StringUtils.isNotBlank(request.getLoginName())) {
			user.setLoginName(request.getLoginName());
		}
		if (StringUtils.isNotBlank(request.getEmail())) {
			user.setEmail(request.getEmail());
		}
		return userMapper.updateByExampleSelective(user, example) > 0;

	}

	@Override
	public User getUserByMobile(String mobile) {
		UserExample example = new UserExample();
		Criteria cri = example.createCriteria();
		cri.andMobileEqualTo(mobile);
		List<User> list = userMapper.selectByExample(example);
		return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
	}

	
	
}
