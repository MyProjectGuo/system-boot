package com.system.boot;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Controller
public class SystemBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemBootApplication.class, args);
	}
	
	@RequestMapping("/")
	String index(Map<String, String> map){
		map.put("hello", "你好啊！");
		try {
			throw new Exception();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/index";
	}
	

}
