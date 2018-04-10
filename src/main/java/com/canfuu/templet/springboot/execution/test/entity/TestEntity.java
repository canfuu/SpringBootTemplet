package com.canfuu.templet.springboot.execution.test.entity;


import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * In springboottest->com.canfuu.springboottest
 * <p>
 * Create in 22:34 2018/1/25
 *
 * @author canfuu
 * @version v1.0:say explain
 */
@Entity
public class TestEntity implements Serializable{
	@Id
	@GeneratedValue
	@NotNull
	private String username;

	@Length(message = "密码长度不符合要求",min=8, max=16)
	@JSONField(serialize = false)
	private String password;
	public TestEntity(){

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "TestEntity{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
