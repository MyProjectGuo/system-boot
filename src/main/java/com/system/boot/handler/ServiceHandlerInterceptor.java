package com.system.boot.handler;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ServiceHandlerInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(ServiceHandlerInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		try {
			String requestMethord = request.getRequestURI();// 请求方法
			if (requestMethord == null) {
				return false;
			}

			// 获取请求参数
			JSONObject parameterMap = JSON.parseObject(RequestWrapper.getBodyString(request));
			if (parameterMap != null) {
				logger.info("[REQUEST]：" + request.getServletPath() + " ，  body：" + parameterMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;

	}

	/**
	 * 获取客户端请求参数中所有的信息
	 * 
	 * @param request
	 * @return
	 */
	private Map<String, String> getAllRequestParam(final HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				// 如果字段的值为空，判断若值为空，则删除这个字段>
				if (null == res.get(en) || "".equals(res.get(en))) {
					res.remove(en);
				}
			}
		}
		return res;
	}

}
