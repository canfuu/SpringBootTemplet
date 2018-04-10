package com.canfuu.templet.springboot.execution.test.controller;

import com.canfuu.templet.springboot.common.entity.Report;
import com.canfuu.templet.springboot.common.factory.ReportFactory;
import com.canfuu.templet.springboot.execution.test.entity.TestEntity;
import com.canfuu.templet.springboot.execution.test.service.TestService;
import net.sf.ehcache.Ehcache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * In springboottest->com.canfuu.springboottest
 * <p>
 * Create in 22:32 2018/1/25
 *
 * @author canfuu
 * @version v1.0:say explain
 */
@RestController
public class TestController {
	public static Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private TestService testService;

	@PostMapping(path = "/test")
	public Report test(@Valid @RequestBody TestEntity testEntity, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){//表示上面testEntity中有数据不符合要求
			return ReportFactory.C_103_DATA_ERROR.report(bindingResult.getFieldError().getField()+"->"+bindingResult.getFieldError().getDefaultMessage());//这里可以返回错误代码
		}
		System.out.println(testEntity);
		ArrayList<TestEntity> e;
		System.out.println(e= (ArrayList<TestEntity>) testService.test(testEntity.getUsername()));
		return ReportFactory.S_0_OK.report(e);
	}

	@GetMapping(path = "/test1")
	public List<TestEntity> test1(@Valid TestEntity testEntity, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){//表示上面testEntity中有数据不符合要求
			System.out.println(bindingResult.getFieldError().getDefaultMessage());
			//会获得如 "密码长度必须为8-16位 字符串信息"
			return null;//这里可以返回错误代码
		}
		return testService.test1(testEntity);
	}
}
