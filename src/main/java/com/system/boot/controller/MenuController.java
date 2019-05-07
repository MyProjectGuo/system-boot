package com.system.boot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单跳转 控制层
 * 
 * @author guoqingbin
 *
 */
@Controller
public class MenuController {

	@RequestMapping("/welcome")
	public String welcome(HttpServletRequest requst , HttpServletResponse response) {
		return "/welcome";
	}

	@RequestMapping("/memberList")
	public String memberList(HttpServletRequest requst , HttpServletResponse response) {
		return "/memberList";
	}
}
