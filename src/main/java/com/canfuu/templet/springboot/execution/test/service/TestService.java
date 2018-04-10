package com.canfuu.templet.springboot.execution.test.service;

import com.canfuu.templet.springboot.execution.test.dao.TestRepository;
import com.canfuu.templet.springboot.execution.test.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * In springboottest->com.canfuu.springboottest
 *
 * Create in 22:34 2018/1/25
 *
 * @author canfuu
 * @version v1.0:say explain
 */
@Service
@CacheConfig(cacheNames = "TestService" )
public class TestService {

	@Autowired
	private TestRepository testRepository;

	@Transactional
	@Cacheable(key = "#root.method.name +'::'+ #id")
	public List<TestEntity> test(String id){
		List<TestEntity> list = new ArrayList<>();
		System.out.println("第一次访问");
		System.out.println(testRepository.getOne(id));
		System.out.println("第二次访问");
		System.out.println(testRepository.getOne(id));
		list.add(testRepository.getOne(id));
		return list;
	}

	@Transactional
	@CachePut(key = "#root.method.name +'::'+#testEntity.toString()")
	public List<TestEntity> test1(TestEntity testEntity){
		TestEntity entity = testRepository.getOne(testEntity.getUsername());
		entity.setPassword(testEntity.getPassword());
		return testRepository.findAll();
	}
}
