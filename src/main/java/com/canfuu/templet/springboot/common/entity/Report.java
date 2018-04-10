package com.canfuu.templet.springboot.common.entity;

import java.io.Serializable;

/**
 * In LoginTemplet_SpringBoot->com.canfuu.templet.entity
 * Create in 22:59 2018/2/8
 *
 * @author canfuu
 * @version v1.0
 * @descriotion 描述
 */
public class Report implements Serializable{

	private int status;
	private Object data;
	private Object msg;

	public Report(int status,Object o) {
		this.status = status;
		if(status==0){
			data=o;
		}else {
			msg=o;
		}
	}
	public Report(int status,Object data,Object msg) {
		this.status = status;
		this.data=data;
		this.msg=msg;
	}

	public int getStatus() {
		return status;
	}

	public Report setStatus(int status) {
		this.status = status;
		return this;
	}

	public Object getData() {
		return data;
	}

	public Report setData(Object data) {
		this.data = data;
		return this;
	}

	public Object getMsg() {
		return msg;
	}

	public Report setMsg(Object msg) {
		this.msg = msg;
		return this;
	}

	public Object toObj(){
		return status==0?data:msg;
	}

	@Override
	public String toString() {
		return "{" +
				"status:" + status +
				", data:" + data +
				", msg:" + msg +
				'}';
	}

}
