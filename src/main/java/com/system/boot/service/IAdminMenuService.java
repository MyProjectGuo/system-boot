package com.system.boot.service;

import java.util.List;

import com.system.boot.contral.req.UpdateMenuInfoRequest;
import com.system.boot.contral.resp.AdminMenuInfoResponse;
import com.system.boot.contral.resp.UserAdminMenuResponse;
import com.system.boot.model.Menu;
import com.system.boot.model.User;

public interface IAdminMenuService {
	
	List<UserAdminMenuResponse> queryAdminMenuByUser(User user);
	
	List<Menu> querySubMenuByParentId(Long parentId);

	AdminMenuInfoResponse querySubMenuById(Long id);
	
	List<Menu> queryParentMenu();
	
	boolean updateMenuByInfo(UpdateMenuInfoRequest request);
}
