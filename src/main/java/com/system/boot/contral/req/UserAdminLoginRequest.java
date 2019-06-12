package com.system.boot.contral.req;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class UserAdminLoginRequest implements Serializable{
	
	@NotBlank(message="请输入登录用户")
	@Length(max=11)
	private String userName;
	
	@NotBlank(message="请输入登录密码")
	@Length(max=10)
	private String userPwd;
	
	//@NotBlank(message="请输入验证码")
	@Length(max=6)
	private String imgCode;

}
