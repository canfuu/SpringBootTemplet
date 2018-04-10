package com.canfuu.templet.springboot.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * In SpringBoot->com.canfuu.templet.springboot.common.aspect
 * Create in 14:00 2018/3/19
 *
 * @author canfuu
 * @version v1.0
 * @descriotion 描述
 */
@Aspect
@Component
public class ControllerAspect {
	public static Logger logger = LoggerFactory.getLogger(ControllerAspect.class);
	@Pointcut("execution(public * com.yunsudesign.server.execution.function.*.controller.*Controller.*(..))")
	public void log(){
	}

	@Before("log()")
	public void doBefore(JoinPoint joinPoint){
		ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		logger.info("Linker + 1");
		logger.info("url={}",request.getRequestURL());
		logger.info("ip={}",request.getRemoteAddr());
		logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName());
		logger.info("args={}",joinPoint.getArgs());
	}

	@AfterReturning(returning = "o",pointcut = "log()")
	public void doAfterReturning(Object o){
		logger.info("response={}",o);
	}
}
