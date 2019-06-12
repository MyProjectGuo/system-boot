package com.system.boot.handler.aop;

import java.lang.reflect.Method;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.system.boot.handler.ResponseResult;
import com.system.boot.handler.annotation.LoginRequired;
import com.system.boot.model.User;
import com.system.boot.utils.Constants;
import com.system.boot.utils.ResponseResultUtils;

/**
 * aop 登录拦截校验
 * 
 * @date 2019年6月12日11:49:18
 * @author guoqingbin
 *
 */
@Aspect
@Component
@Slf4j
public class ControllerInterceptor {

	@Autowired
	@Qualifier("stringRedisTemplate")
	private StringRedisTemplate template;

	/**
	 * 定义拦截规则：拦截com.huidong.qzh.standard包下面的所有类中，有@RequestMapping注解的方法。
	 */
	@Pointcut("execution(* com.system.boot..*(..))" + " && ( @annotation(org.springframework.web.bind.annotation.RequestMapping )" + " || @annotation(org.springframework.web.bind.annotation.GetMapping) " + " || @annotation(org.springframework.web.bind.annotation.PostMapping ))")
	public void controllerMethodPointcut() {
	}

	/**
	 * 拦截器具体实现
	 *
	 * @param pjp
	 * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
	 */
	@Around("controllerMethodPointcut()")
	// 指定拦截器规则；也可以直接把“execution(* com.xjj.........)”写进这里
	public Object Interceptor(ProceedingJoinPoint pjp) {
		long beginTime = System.currentTimeMillis();
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod(); // 获取被拦截的方法
		String methodName = method.getName(); // 获取被拦截的方法名

		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();

		Object result = null;

		if (isLoginRequired(method)) {
			ResponseResult loginResult = isLogin(request);
			if (loginResult.getCode() != 200) {
				result = loginResult;
			}
		}
		if (result == null) {
			try {
				result = pjp.proceed();
			} catch (Throwable throwable) {
				throwable.printStackTrace();
				result = ResponseResultUtils.fail(throwable.getMessage());
			}
		}
		return result;
	}

	/**
	 * 判断一个方法是否需要登录
	 *
	 * @param method
	 *            方法
	 * @return
	 */
	private boolean isLoginRequired(Method method) {
		boolean result = false;
		if (method.isAnnotationPresent(LoginRequired.class)) {
			result = method.getAnnotation(LoginRequired.class).loginRequired();
		}
		return result;
	}

	// 判断是否已经登录
	private ResponseResult isLogin(HttpServletRequest request) {
		String token = request.getHeader("token");
		if (StringUtils.isBlank(token) || !"null".equals(token)) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("token")) {
						token = cookie.getValue();
						break;
					}
				}
			}

			if (StringUtils.isBlank(token)) {
				return ResponseResultUtils.fail(Integer.valueOf(Constants.NO_LOGIN.getCode()), Constants.NO_LOGIN.getMsg());
			}

			String userToken = template.opsForValue().get(token);

			if (StringUtils.isBlank(userToken)) {
				return ResponseResultUtils.fail(Integer.valueOf(Constants.NO_LOGIN.getCode()), Constants.NO_LOGIN.getMsg());
			}
			log.info("登录校验用户信息：{}" , JSONObject.toJSONString(userToken));
			request.setAttribute("user_info", JSONObject.parseObject(userToken, User.class));
			return ResponseResultUtils.success("success");
		}
		return ResponseResultUtils.fail(Integer.valueOf(Constants.NO_LOGIN.getCode()), Constants.NO_LOGIN.getMsg());
	}

}
