package com.system.boot.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ServiceHandlerInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/").addResourceLocations("classpath:/templates/");
		//super.addResourceHandlers(registry);
	}
}
