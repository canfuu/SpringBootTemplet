package com.canfuu.templet.springboot.common.factory;



import com.canfuu.templet.springboot.common.entity.Report;

import java.io.Serializable;

/**
 * In YunSuDesignWeb->com.yunsudesign.server.common
 * Create in 21:18 2018/4/9
 *
 * @author canfuu
 * @version v1.0
 * @descriotion S -> 服务端错误、C -> 客户端错误、D -> 数据错误
 */

public enum  ReportFactory {
	S_500_ERROR(500,"服务器内部错误"),
	C_400_ERROR(400,"客户端错误"),
	S_0_OK(0,"OK"),
	C_101_LOGIN_INFO_ERROR(101,"用户名或密码错误"),
	C_102_LOGIN_STATUS_ERROR(102,"登陆状态失效"),
	C_103_DATA_ERROR(103,"数据格式错误"),
	C_201_TOO_MUCH_CODE(201,"验证码发送次数过多"),
	C_202_SEND_CODE_ERROR(202,"验证码发送失败");
	private int status;
	private Serializable msg;

	ReportFactory(int status,Serializable msg){
		this.status = status;
		this.msg=msg;
	}
	public Report report(Serializable data){
		return new Report(status,data,msg);
	}
	public Report report(){
		return new Report(status,null,msg);
	}

}
