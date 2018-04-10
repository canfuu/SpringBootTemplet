package com.canfuu.templet.springboot.common.entity;

import com.canfuu.templet.springboot.common.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * In YunSuDesignWeb->com.yunsudesign.server.execution.bean.entity
 * Create in 17:19 2018/4/10
 *
 * @author canfuu
 * @version v1.0
 * @descriotion 描述
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Page implements Serializable{
	protected String SESSIONID;

	public void setSESSIONID(String SESSIONID) {
		this.SESSIONID = SESSIONID;
	}

	public String getSESSIONID() {
		return SESSIONID;
	}


	public Object newPage(Class pageClass,PageType pageType) {
		Object page = null;
		try {
			page = pageClass.newInstance();
			HashMap<String, String> thisGetMethod = new HashMap<>(16);
			for (Field field : this.getClass().getDeclaredFields()) {
				thisGetMethod.put(field.getName(), "get" + StringUtil.captureName(field.getName()));
			}
			for (Field f : page.getClass().getDeclaredFields()) {
				f.setAccessible(true);
				String fieldName = f.getName();
				String fieldNameTransed = transPageFieldName(fieldName, page.getClass(),pageType);
				if (thisGetMethod.containsKey(fieldNameTransed)) {
					Method setMethod;
					setMethod = page.getClass().getDeclaredMethod("set" + StringUtil.captureName(fieldNameTransed), f.getType());
					Method getThisField = this.getClass().getDeclaredMethod(thisGetMethod.get(fieldNameTransed));
					setMethod.invoke(page, getThisField.invoke(this));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  page;
	}
	private String transPageFieldName(String pojoFieldName, Class POJOClass,PageType pageType){
		String temp = null;
		switch (pageType){
			case NO_CHANGE:
				temp = pojoFieldName;
				break;
			case POJO_NAME_PREFIX:
				String profix = StringUtil.upCaptureName(POJOClass.getSimpleName());
				String body = StringUtil.captureName(pojoFieldName);
				temp = profix+body;
				break;
		}

		return temp;
	}

}
