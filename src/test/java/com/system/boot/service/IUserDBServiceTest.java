package com.system.boot.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.system.boot.SystemBootApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SystemBootApplication.class)
public class IUserDBServiceTest {

	@Autowired
	@Qualifier("stringRedisTemplate")
	private StringRedisTemplate template;
	
	@Autowired
	private IUserDBService userDBService;

	@Test
	public void testGetUserById() {
		
		template.opsForValue().set("1461346131dfsdjl223", JSONObject.toJSONString(userDBService.getUserById(1L)));
		System.out.println(template.opsForValue().get("1461346131dfsdjl223"));
	}

}
