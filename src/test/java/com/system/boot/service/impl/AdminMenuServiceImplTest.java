package com.system.boot.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.system.boot.SystemBootApplication;
import com.system.boot.contral.resp.UserAdminMenuResponse;
import com.system.boot.service.IAdminMenuService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SystemBootApplication.class)
public class AdminMenuServiceImplTest {

	@Autowired
	private IAdminMenuService adminMenuService;

	@Test
	public void testQueryAdminMenuByUser() {
		List<UserAdminMenuResponse> list = adminMenuService.queryAdminMenuByUser(null);

		System.out.println(JSONObject.toJSONString(list));
	}

}
