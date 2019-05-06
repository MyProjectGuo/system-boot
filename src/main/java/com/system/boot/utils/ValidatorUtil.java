package com.system.boot.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;

import com.system.boot.handler.ServiceException;

public class ValidatorUtil {

	private static Validator validator = ((HibernateValidatorConfiguration) Validation.byProvider(HibernateValidator.class).configure()).failFast(true).buildValidatorFactory().getValidator();

	/**
	 * 实体校验
	 * 
	 * @param obj
	 * @throws CommonException
	 */
	public static <T> void validate(T obj) throws ServiceException {
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj, new Class[0]);
		if (constraintViolations.size() > 0) {
			ConstraintViolation<T> validateInfo = (ConstraintViolation<T>) constraintViolations.iterator().next();
			// validateInfo.getMessage() 校验不通过时的信息，即message对应的值
			throw new ServiceException(501, validateInfo.getMessage());
		}
	}

}
