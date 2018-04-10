package com.canfuu.templet.springboot.common.advice;


import com.canfuu.templet.springboot.common.entity.Report;
import com.canfuu.templet.springboot.common.factory.ReportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * In SpringBoot->com.canfuu.templet.springboot.common.advice
 * Create in 14:00 2018/3/19
 *
 * @author canfuu
 * @version v1.0
 * @descriotion 描述
 */
@RestControllerAdvice
@EnableCaching
public class ControllerExceptionAdvice {
	private Logger logger = LoggerFactory.getLogger(ControllerExceptionAdvice.class);
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Report handle(Exception e){
		logger.error("Server Error in "+e.getMessage(),e);

		return ReportFactory.S_500_ERROR.report(e.getMessage());
	}
}
