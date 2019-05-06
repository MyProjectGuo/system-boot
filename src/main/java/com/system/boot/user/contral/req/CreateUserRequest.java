package com.system.boot.user.contral.req;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class CreateUserRequest {

	@NotBlank(message = "用户名不能为空")
	@Length(min = 1, max = 20)
	private String userName;

}
