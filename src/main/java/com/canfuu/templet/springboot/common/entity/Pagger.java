package com.canfuu.templet.springboot.common.entity;

import java.io.Serializable;

/**
 * In YunSuDesignWeb->com.yunsudesign.server.execution.bean.entity
 * Create in 19:28 2018/4/10
 *
 * @author canfuu
 * @version v1.0
 * @descriotion 描述
 */
public class Pagger extends Page implements Serializable{
	private Integer page;
	private Integer num;
	private Condition condition;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}
}
