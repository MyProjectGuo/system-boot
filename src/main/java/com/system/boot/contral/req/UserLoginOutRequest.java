package com.system.boot.contral.req;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserLoginOutRequest implements Serializable{
	
	private static final long serialVersionUID = -3146837386199959596L;
	
	@NotBlank(message="用户未登录不用退出")
	@Length(max=50)
	private String loginToken;

}
