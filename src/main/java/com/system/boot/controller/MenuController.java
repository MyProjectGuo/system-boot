package com.system.boot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 菜单跳转 控制层
 * 
 * @author guoqingbin
 *
 */
@Controller
public class MenuController {

	@RequestMapping("/welcome.html")
	public String welcome(HttpServletRequest requst, HttpServletResponse response) {
		return "admin/welcome";
	}

	@RequestMapping("/memberlist.html")
	public String memberList(HttpServletRequest requst, HttpServletResponse response) {
		return "admin/memberlist";
	}

	@RequestMapping("/admin_login.html")
	public String login(HttpServletRequest requst, HttpServletResponse response) {
		return "admin/login";
	}

	@RequestMapping("/404.html")
	public String noHandle(HttpServletRequest requst, HttpServletResponse response) {
		return "admin/404";
	}

	@RequestMapping(value={"/index.html","/"})
	public String menu(HttpServletRequest requst, HttpServletResponse response) {
		return "admin/index";
	}

	@RequestMapping(value = "/user_admin_getUserList", method = RequestMethod.GET)
	public String getUserList(HttpServletRequest request, HttpServletResponse response) {

		return "/admin/user-list";
	}

}
