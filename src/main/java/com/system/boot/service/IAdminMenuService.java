package com.system.boot.service;

import java.util.List;

import com.system.boot.contral.resp.UserAdminMenuResponse;
import com.system.boot.model.Menu;
import com.system.boot.model.User;

public interface IAdminMenuService {
	
	List<UserAdminMenuResponse> queryAdminMenuByUser(User user);
	
	List<Menu> querySubMenuByParentId(Long parentId);

}
