package com.system.boot.handler;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName = "serviceFilter", urlPatterns = "/*")
public class ServiceFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		// 防止流读取一次后就没有了, 所以需要将流继续写出去
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		ServletRequest requestWrapper = new RequestWrapper(httpServletRequest);
		requestWrapper.setCharacterEncoding("UTF-8");
		servletResponse.setCharacterEncoding("UTF-8");
		filterChain.doFilter(requestWrapper, servletResponse);
	}

}
