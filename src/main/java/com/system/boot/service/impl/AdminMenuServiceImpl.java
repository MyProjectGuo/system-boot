package com.system.boot.service.impl;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.system.boot.contral.req.UpdateMenuInfoRequest;
import com.system.boot.contral.resp.AdminMenuInfoResponse;
import com.system.boot.contral.resp.AdminMenuInfoResponse.ParenterMenu;
import com.system.boot.contral.resp.UserAdminMenuResponse;
import com.system.boot.dao.MenuExample;
import com.system.boot.dao.MenuExample.Criteria;
import com.system.boot.dao.MenuMapper;
import com.system.boot.handler.ServiceException;
import com.system.boot.model.Menu;
import com.system.boot.model.User;
import com.system.boot.service.IAdminMenuService;
import com.system.boot.utils.Constants;
import com.system.boot.utils.SystemConstants;

@Service("adminMenuService")
public class AdminMenuServiceImpl implements IAdminMenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Autowired
	@Qualifier("stringRedisTemplate")
	private StringRedisTemplate template;

	
	ExecutorService exec = Executors.newCachedThreadPool();
	
	@Override
	public List<UserAdminMenuResponse> queryAdminMenuByUser(User user) {

		// 从redis中获取菜单
		List<UserAdminMenuResponse> list = getMenuByRedis(user);
		if (CollectionUtils.isNotEmpty(list)) {
			return list;
		}

		MenuExample example = new MenuExample();
		Criteria cri = example.createCriteria();
		cri.andParentMenuEqualTo(0L);
		cri.andDeletedEqualTo((short) 0);
		example.setOrderByClause("sort_number asc");
		// 父级菜单
		List<Menu> parentList = menuMapper.selectByExample(example);

		if (CollectionUtils.isNotEmpty(parentList)) {
			List<UserAdminMenuResponse> menuList = new ArrayList<UserAdminMenuResponse>();
			UserAdminMenuResponse userMenu = null;
			// 父级菜单 封装
			for (Menu menu : parentList) {
				userMenu = new UserAdminMenuResponse();
				userMenu.setHasThird(menu.getHasthird());
				userMenu.setIcon(menu.getIcon());
				userMenu.setMenuId(menu.getId());
				userMenu.setMenuName(menu.getMenuName());
				userMenu.setMenus(null);
				userMenu.setUrl(null);
				userMenu.setParentMenu(menu.getParentMenu());

				// 遍历查询子菜单
				List<Menu> subMenuList = querySubMenuByParentId(menu.getId());
				if (CollectionUtils.isNotEmpty(subMenuList)) {
					List<UserAdminMenuResponse.SubMenus> subList = new ArrayList<UserAdminMenuResponse.SubMenus>();
					UserAdminMenuResponse.SubMenus sub = null;
					for (Menu subMenu : subMenuList) {

						sub = new UserAdminMenuResponse.SubMenus();
						sub.setHasThird(subMenu.getHasthird());
						sub.setIcon(subMenu.getIcon());
						sub.setMenuId(subMenu.getId());
						sub.setMenuName(subMenu.getMenuName());
						sub.setUrl(subMenu.getUrl());
						sub.setParentMenu(subMenu.getParentMenu());
						subList.add(sub);
					}

					userMenu.setMenus(subList);
				}

				menuList.add(userMenu);

			}

			if (CollectionUtils.isNotEmpty(menuList)) {
				if (user != null) {
					template.opsForValue().set(SystemConstants.MENU_TOKEN_KEY_USERID + user.getId(), JSONObject.toJSONString(menuList), SystemConstants.MENU_TOKEN_TIME_DAYS, TimeUnit.DAYS);
				} else {
					template.opsForValue().set(SystemConstants.MENU_TOKEN_KEY_ALL, JSONObject.toJSONString(menuList), SystemConstants.MENU_TOKEN_TIME_DAYS, TimeUnit.DAYS);
				}
			}
			return menuList;
		}

		return null;
	}

	@Override
	public List<Menu> querySubMenuByParentId(Long parentId) {
		MenuExample example = new MenuExample();
		Criteria cri = example.createCriteria();
		cri.andParentMenuEqualTo(parentId);
		cri.andDeletedEqualTo((short) 0);
		example.setOrderByClause("sort_number asc");
		List<Menu> subMenuList = menuMapper.selectByExample(example);
		return CollectionUtils.isNotEmpty(subMenuList) ? subMenuList : null;
	}

	private List<UserAdminMenuResponse> getMenuByRedis(User user) {

		List<UserAdminMenuResponse> menuList = null;
		String menuStr = null;
		if (user == null) {
			menuStr = template.opsForValue().get(SystemConstants.MENU_TOKEN_KEY_ALL);
		} else {
			menuStr = template.opsForValue().get(SystemConstants.MENU_TOKEN_KEY_USERID + user.getId());
		}

		if (StringUtils.isNotBlank(menuStr)) {
			menuList = new ArrayList<UserAdminMenuResponse>();
			menuList = JSONObject.parseArray(menuStr, UserAdminMenuResponse.class);
		}

		return menuList;

	}

	@Override
	public AdminMenuInfoResponse querySubMenuById(Long id) {
		MenuExample example = new MenuExample();
		Criteria cri = example.createCriteria();
		cri.andIdEqualTo(id);
		Menu menu = menuMapper.selectByPrimaryKey(id);
		if (menu == null) {
			throw new ServiceException(Integer.valueOf(Constants.NO_FOUND.getCode()), Constants.NO_FOUND.getMsg());
		}
		AdminMenuInfoResponse response = new AdminMenuInfoResponse();
		response.setHasThird(menu.getHasthird());
		response.setIcon(menu.getIcon());
		response.setMenuId(id);
		response.setMenuName(menu.getMenuName());
		response.setUrl(menu.getUrl());
		response.setSortNumber(menu.getSortNumber());
		response.setParentMenu(menu.getParentMenu());
		if (menu.getParentMenu().longValue() != 0) {

			Menu parenterMenu = menuMapper.selectByPrimaryKey(menu.getParentMenu());
			ParenterMenu pm = new ParenterMenu();
			pm.setHasThird(parenterMenu.getHasthird());
			pm.setIcon(parenterMenu.getIcon());
			pm.setMenuId(parenterMenu.getId());
			pm.setMenuName(parenterMenu.getMenuName());
			pm.setUrl(parenterMenu.getUrl());
			response.setParenterMenu(pm);

		}

		return response;
	}

	@Override
	public List<Menu> queryParentMenu() {
		MenuExample example = new MenuExample();
		Criteria cri = example.createCriteria();
		cri.andParentMenuEqualTo(0L).andDeletedEqualTo((short) 0);
		List<Menu> list = menuMapper.selectByExample(example);
		return CollectionUtils.isNotEmpty(list) ? list : null;
	}

	@Override
	public boolean updateMenuByInfo(UpdateMenuInfoRequest request) {
		Menu menu = getMenuById(request.getMenuId());
		if (menu == null) {
			return false;
		}
		MenuExample example = new MenuExample();
		Criteria cri = example.createCriteria();
		cri.andIdEqualTo(menu.getId());
		if (request.getDeleted().shortValue() == 1) {
			menu.setDeleted((short) 1);
		} else {
			menu.setUpdatedTime(System.currentTimeMillis());
			menu.setIcon(request.getIcon());
			menu.setMenuName(URLDecoder.decode(request.getMenuName()));
			menu.setParentMenu(request.getParentMenu());
			menu.setSortNumber(request.getSortNumber());
			menu.setUrl(URLDecoder.decode(request.getUrl()));
		}
		
		/*exec.execute(new Runnable() {
			
			@Override
			public void run() {
				
				
			}
		}); */
		return menuMapper.updateByExampleSelective(menu, example) > 0;
	}

	private Menu getMenuById(Long id) {

		return menuMapper.selectByPrimaryKey(id);
	}

}
