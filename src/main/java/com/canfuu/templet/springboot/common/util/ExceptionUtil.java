package com.canfuu.templet.springboot.common.util;

/**
 * In CanfuuPro->com.cts.system.utils
 * <p>
 * Create in 11:23 2017/11/28
 *
 * @author canfuu
 * @version v1.0:say explain
 */
public class ExceptionUtil {
	public static String explainError(String explain,String fixSupport){
		return "\n发生原因："+explain+"\n"+"建议解决方法："+fixSupport + "\nDetails:";
	}
}
