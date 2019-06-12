package com.system.boot.contral.req;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.system.boot.utils.PageInfo;

import lombok.Data;


@Data
public class UpdateUserRequest implements Serializable{

	private static final long serialVersionUID = -4292061654143223377L;

	@NotBlank(message="userId 不能为空")
	private Long userId ;
	
	@Max(value=1)
	@Min(value=0)
	private Integer userStatus;
	
	private boolean resetPwd=false;
	
	@Length(max=8)
	private String loginName;
	
	@Length(max=11)
	private String mobile;
	
	@Length(max=20)
	private String email;
	
	private boolean addUser=false;
	
	private String ipAddress;
	
	private Integer page;
	private Integer limit;
	
}
