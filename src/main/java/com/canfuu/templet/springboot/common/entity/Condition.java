package com.canfuu.templet.springboot.common.entity;

import java.io.Serializable;

/**
 * In YunSuDesignWeb->com.yunsudesign.server.execution.bean.entity
 * Create in 19:29 2018/4/10
 *
 * @author canfuu
 * @version v1.0
 * @descriotion 描述
 */
public class Condition implements Serializable{
	protected String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
