package com.system.boot.contral.req;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class QueryUserRequest implements Serializable{
	
	private String userName;
	
	private String startTime;
	
	private String endTime;
	
	@Length(max=11)
	private String mobile;
}
