package com.canfuu.templet.springboot.common.util;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.HashMap;

/**
 * In server->com.dayukeji.common.utils
 * Create in 19:11 2018/3/18
 *
 * @author canfuu
 * @version v1.0
 * @descriotion 描述
 */
public class JsonUtil {
	public static String toJSON(Serializable obj){
		return JSON.toJSONString(obj);
	}

}
