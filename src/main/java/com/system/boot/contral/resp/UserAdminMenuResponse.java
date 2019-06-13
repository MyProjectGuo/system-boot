package com.system.boot.contral.resp;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserAdminMenuResponse implements Serializable {

	private static final long serialVersionUID = 1324485947507642816L;

	private Long menuId;

	private String icon;

	private String menuName;

	private String hasThird;

	private String url;

	private List<SubMenus> menus;

	@Data
	public static class SubMenus {

		private Long menuId;

		private String icon;

		private String menuName;

		private String hasThird;

		private String url;

	}

}
