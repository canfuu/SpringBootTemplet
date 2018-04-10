package com.canfuu.templet.springboot.execution.test.dao;


import com.canfuu.templet.springboot.execution.test.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * In springboottest->com.canfuu.springboottest
 *
 * Create in 22:34 2018/1/25
 *
 * @author canfuu
 * @version v1.0:say explain
 */

public interface TestRepository extends JpaRepository<TestEntity,String> {
}
