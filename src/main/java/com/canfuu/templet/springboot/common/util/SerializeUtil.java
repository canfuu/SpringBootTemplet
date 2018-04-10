package com.canfuu.templet.springboot.common.util;


import org.springframework.util.SerializationUtils;

/**
 * In CanfuuPro->com.cts.system.utils
 * <p>
 * Create in 11:00 2017/11/26
 *
 * @author canfuu
 * @version v1.0:say explain
 */
public final class SerializeUtil {

	//序列化
	public static byte [] serialize(Object obj){
		return SerializationUtils.serialize(obj);

	}


	//反序列化
	public static Object unserizlize(byte[] byt){
		return SerializationUtils.deserialize(byt);

	}
}
