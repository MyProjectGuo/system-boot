package com.system.boot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.system.boot.contral.resp.UserAdminMenuResponse;
import com.system.boot.dao.MenuExample;
import com.system.boot.dao.MenuExample.Criteria;
import com.system.boot.dao.MenuMapper;
import com.system.boot.model.Menu;
import com.system.boot.model.User;
import com.system.boot.service.IAdminMenuService;
import com.system.boot.utils.SystemConstants;

@Service("adminMenuService")
public class AdminMenuServiceImpl implements IAdminMenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Autowired
	@Qualifier("stringRedisTemplate")
	private StringRedisTemplate template;

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

}
