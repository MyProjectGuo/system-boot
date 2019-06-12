package com.system.boot.contral.resp;

import java.io.Serializable;

import com.system.boot.model.User;

import lombok.Data;

@Data
public class UserLoginResponse implements Serializable {

	private static final long serialVersionUID = 2885171108294194967L;

	/**
	 * 用户名 us_user.login_name
	 *
	 * @mbggenerated
	 */
	private String loginName;

	/**
	 * 电子邮件 us_user.email
	 *
	 * @mbggenerated
	 */
	private String email;

	/**
	 * 手机号码 us_user.mobile
	 *
	 * @mbggenerated
	 */
	private String mobile;

	/**
	 * 邮件绑定状态 0未绑定 1绑定 us_user.email_status
	 *
	 * @mbggenerated
	 */
	private Byte emailStatus;

	/**
	 * 手机绑定状态 0未绑定 1绑定 us_user.mobile_status
	 *
	 * @mbggenerated
	 */
	private Byte mobileStatus;

	/**
	 * 注册IP地址 us_user.register_ip
	 *
	 * @mbggenerated
	 */
	private String registerIp;

	/**
	 * 用户状态 1激活 0未激活 us_user.user_status
	 *
	 * @mbggenerated
	 */
	private Byte userStatus;

	/**
	 * 最后更新时间 us_user.updated_time
	 *
	 * @mbggenerated
	 */
	private Long updatedTime;

	/**
	 * 注册时间 us_user.created_time
	 *
	 * @mbggenerated
	 */
	private Long createdTime;

	/**
	 * 登录token
	 * 
	 */
	private String token;

	public static UserLoginResponse toUserResponse(User user) {
		UserLoginResponse response = new UserLoginResponse();
		
		response.setCreatedTime(user.getCreatedTime());
		response.setEmail(user.getEmail());
		response.setEmailStatus(user.getEmailStatus());
		response.setLoginName(user.getLoginName());
		response.setMobile(user.getMobile());
		response.setMobileStatus(user.getMobileStatus());
		response.setRegisterIp(user.getRegisterIp());
		response.setUpdatedTime(user.getUpdatedTime());
		response.setUserStatus(user.getUserStatus());
		
		return response;
	}
}
