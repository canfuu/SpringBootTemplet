package com.canfuu.templet.springboot.common.util;



import com.canfuu.templet.springboot.common.entity.Node;

import java.util.HashMap;

/**
 * In YunSuDesignWeb->com.yunsudesign.server.util
 * Create in 00:42 2018/4/10
 *
 * @author canfuu
 * @version v1.0
 * @descriotion 描述
 */
public class MapUtil {
	public static HashMap createMap(Node<String,Object>... nodes){
		HashMap map = new HashMap();
		for (int i = 0; i < nodes.length; i++) {
			map.put(nodes[i].getK(),nodes[i].getV());
		}
		return map;
	}
}
