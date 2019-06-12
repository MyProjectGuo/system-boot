package com.system.boot.handler.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解校验
 * @date 2019年6月12日11:36:35
 * @author guoqingbin
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LoginRequired {
	
	
	/**
     * 是否需要登录，缺省为需要
     * @return
     */
    boolean loginRequired() default true;

}
