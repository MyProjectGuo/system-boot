package com.system.boot.contral.req;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.Length;

@SuppressWarnings("serial")
@Getter
@Setter
public class QueryUserRequest implements Serializable{
	
	private String loginName;
	
	private String startTime;
	
	private String endTime;
	
	@Length(max=11)
	private String mobile;
	
	@Min(value=1)
	private Integer page;
	
	@Min(value=1)
	private Integer limit;
	
	@Max(value=1)
	@Min(value=0)
	private Integer userStatus;
	
	
}
