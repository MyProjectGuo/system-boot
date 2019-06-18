package com.system.boot.contral.resp;

import java.io.Serializable;

import lombok.Data;

@Data
public class AdminMenuInfoResponse implements Serializable {

	private static final long serialVersionUID = -4319009264365558350L;

	private Long menuId;

	private String icon;

	private String menuName;

	private String hasThird;

	private String url;

	private Integer sortNumber;
	
	private Long parentMenu;
	
	private ParenterMenu parenterMenu;
	
	

	@Data
	public static class ParenterMenu {

		private Long menuId;

		private String icon;

		private String menuName;

		private String hasThird;

		private String url;

	}

}
