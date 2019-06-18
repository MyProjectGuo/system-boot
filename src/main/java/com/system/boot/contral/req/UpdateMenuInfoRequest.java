package com.system.boot.contral.req;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UpdateMenuInfoRequest implements Serializable{
	
	private static final long serialVersionUID = -2288465573506698165L;
	
	@NotNull
	@Min(value=1)
	private Long menuId;

	@NotBlank
	@Length(max=20)
	private String icon;

	@NotBlank
	@Length(max=50)
	private String menuName;

	@NotBlank
	@Length(max=20)
	private String hasThird;

	@NotBlank
	@Length(max=20)
	private String url;

	@NotNull
	@Min(value=1)
	private Integer sortNumber;
	
	@NotNull
	@Min(value=1)
	private Long parentMenu;
	
	private Short deleted=0;
	

}
