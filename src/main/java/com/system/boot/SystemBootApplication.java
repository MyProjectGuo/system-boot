package com.system.boot;

import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
@MapperScan("com.system.boot")
@EnableTransactionManagement
public class SystemBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemBootApplication.class, args);
	}

	@RequestMapping("/")
	String index(Map<String, String> map) {
		return "/index";
	}

}
