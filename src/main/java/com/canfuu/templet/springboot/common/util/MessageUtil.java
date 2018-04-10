package com.canfuu.templet.springboot.common.util;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.canfuu.templet.springboot.common.entity.Node;
import com.canfuu.templet.springboot.common.entity.Report;
import com.canfuu.templet.springboot.common.factory.ReportFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * In YunSuDesignWeb->com.yunsudesign.server.common.util
 * Create in 19:42 2018/4/10
 *
 * @author canfuu
 * @version v1.0
 * @descriotion 描述
 */

public class MessageUtil {
	private static final boolean dev = true;
	private static HashMap<String,List<String>> MESSAGE_CENTER = new HashMap<>();
	private static final int MAX_MESSAGE = 10;
	private static Lock lock = new ReentrantLock();
	private static final int ServerError = 3;
	private static final int PhoneNumberError = 4;
	private static final int InternetError = 5;
	private static final int PhoneNumberException = 6;
	private static final int ParamLengthError = 7;
	private static final String ServerErrorMessage = "服务器内部错误";
	private static final String PhoneNumberErrorMessage = "手机号码错误";
	private static final String InternetErrorMessage = "服务器网络错误";
	private static final String PhoneNumberExceptionMessage = "该号码状态异常";
	private static final String ParamLengthErrorMessage = "参数长度异常，传入参数应为1-8位字符串";

	public static Report sendE(String email){
		HashMap map  = new HashMap();
		String code = "123456";
		map.put("code",code);
		List<String> list;
		lock.lock();
		try {
			if(!MESSAGE_CENTER.containsKey(email)) {
				list=new ArrayList<>();
				MESSAGE_CENTER.put(email,list);
			}else {
				list=MESSAGE_CENTER.get(email);
				if(list.size()>MAX_MESSAGE){
					lock.unlock();
					return ReportFactory.C_201_TOO_MUCH_CODE.report();
				}
			}
			if(sendEmail(email,code).getStatus()==0) {
				list.add(code);
			}else {
				lock.unlock();
				return ReportFactory.C_202_SEND_CODE_ERROR.report();
			}
		}catch (Exception e){
			lock.unlock();
			return ReportFactory.C_202_SEND_CODE_ERROR.report();
		}
		lock.unlock();
		return ReportFactory.S_0_OK.report(map);
	}
	public static Report sendP(String phonenum){
		HashMap map  = new HashMap();
		String code = "123456";
		map.put("code",code);
		List<String> list;
		lock.lock();
		try {
			if(!MESSAGE_CENTER.containsKey(phonenum)) {
				list=new ArrayList<>();
				MESSAGE_CENTER.put(phonenum,list);
			}else {
				list=MESSAGE_CENTER.get(phonenum);
				if(list.size()>MAX_MESSAGE){
					lock.unlock();
					return ReportFactory.C_201_TOO_MUCH_CODE.report();
				}
			}
			if(sendPhone(phonenum,code).getStatus()==0) {
				list.add(code);
			}else {
				lock.unlock();
				return ReportFactory.C_202_SEND_CODE_ERROR.report();
			}
		}catch (Exception e){
			lock.unlock();
			return ReportFactory.C_202_SEND_CODE_ERROR.report();
		}

		lock.unlock();
		return ReportFactory.S_0_OK.report(map);
	}
	private static Report sendEmail(String email,String code){
		return ReportFactory.S_0_OK.report();
	}
	private static Report sendPhone(String phonenum,String code){
		return sendMessageAliyun("",phonenum,MapUtil.createMap(new Node<>("code",code)));

	}
	public static void flush(){
		if(MESSAGE_CENTER==null){
			MESSAGE_CENTER = new HashMap<>();
		}else {
			MESSAGE_CENTER.clear();
		}
	}
	public static boolean pass(String ep,String code){
		boolean result = false;
		if(MESSAGE_CENTER.containsKey(ep)){
			result=MESSAGE_CENTER.get(ep).contains(code);
			if(result) {
				MESSAGE_CENTER.get(ep).remove(code);
			}
		}
		return result;
	}

	public static Report sendMessageJuhe(Integer templateId, String mobile) {
		return MessageUtil.getRequestByJuhe(templateId, mobile, LIU_XIAO_AN_APPKEY, null);
	}
	public static Report sendMessageJuhe(Integer templateId, String mobile, HashMap<String, String> param) {
		return MessageUtil.getRequestByJuhe(templateId, mobile, LIU_XIAO_AN_APPKEY, param);
	}
	public static Report sendMessageAliyun(String templateId, String mobile) {
		return MessageUtil.getRequestByAliyun(templateId, mobile, ACCESS_KEY_ID, ACCESS_KEY, "大愚科技", null);
	}
	public static Report sendMessageAliyun(String templateId, String mobile, HashMap<String, String> param) {
		return MessageUtil.getRequestByAliyun(templateId, mobile, ACCESS_KEY_ID, ACCESS_KEY, "大愚科技", param);
	}

	private static final String ACCESS_KEY_ID = "sTQmLwUJyGGCegv2";
	private static final String ACCESS_KEY = "EKgp4bw76zNS1XEOuVS2Q2thxGejKD";
	private static Report getRequestByAliyun(String mubanId, String telephone, String accessKeyId, String accessKeySecret, String signature, HashMap<String, String> param) {
		try {
			//设置超时时间-可自行调整
			System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
			System.setProperty("sun.net.client.defaultReadTimeout", "10000");
			//初始化ascClient需要的几个参数
			final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
			final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
			//替换成你的AK
			//初始化ascClient,暂时不支持多region（请勿修改）
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);
			//组装请求对象
			SendSmsRequest request = new SendSmsRequest();

			//使用post提交
			request.setMethod(MethodType.POST);

			//必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
			request.setPhoneNumbers(telephone);

			//必填:短信签名-可在短信控制台中找到
			request.setSignName(signature);

			//必填:短信模板-可在短信控制台中找到
			request.setTemplateCode(mubanId);

			//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			//友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
			request.setTemplateParam(JsonUtil.toJSON(param));

			//可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
			//request.setSmsUpExtendCode("90997");
			//可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
			//请求失败这里会抛ClientException异常
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
				return new Report(0, "成功");
			} else {
				return new Report(ServerError, sendSmsResponse.getMessage());
			}
		} catch (Exception e) {
			return new Report(ServerError, ServerErrorMessage + ":" + e.getMessage());
		}
	}

	private static final String LIU_XIAO_AN_APPKEY = "188508886b0d72f8be74d4bb0ee6f253";
	private static final String DEF_CHATSET = "UTF-8";
	private static final int DEF_CONN_TIMEOUT = 30000;
	private static final int DEF_READ_TIMEOUT = 30000;
	private static final String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
	private static Report getRequestByJuhe(Integer muban, String telephone, String appkey, HashMap<String, String> param) {
		if (telephone == null) {
			return new Report(ServerError, ServerErrorMessage);
		}
		try {
			String result;
			String url = "http://v.juhe.cn/sms/send";//请求接口地址
			Map params = new HashMap();//请求参数
			params.put("mobile", telephone);//接收短信的手机号码
			params.put("tpl_id", muban);//短信模板ID，请参考个人中心短信模板设置
			params.put("key", appkey);//应用APPKEY(应用详细页查询)
			Map temp = new HashMap();
			if (param != null) {
				final boolean[] error = {false};
				param.forEach((key, value) -> {
					if (value != null && value.length() > 8) {
						error[0] = true;
					}
					temp.put("#" + key + "#", value);
				});
				if (error[0]) {
					return new Report(ParamLengthError, ParamLengthErrorMessage);
				}
				params.put("tpl_value", urlencode(temp));
			}
			result = net(url, params, "GET");
			if (result == null) {
				return new Report(ServerError, ServerErrorMessage);
			}

			Map<Object, Object> map = JSON.parseObject(result, HashMap.class);
			switch (((Double) map.get("error_code")).intValue()) {
				case 0:
					return new Report(0, "成功");
				case 205401:
					return new Report(PhoneNumberError, PhoneNumberErrorMessage);
				case 205403:
					return new Report(InternetError, InternetErrorMessage);
				case 205405:
					return new Report(PhoneNumberException, PhoneNumberExceptionMessage);
				default:
					return new Report(((Double) map.get("error_code")).intValue(), map.get("reason"));
			}
		} catch (Exception e) {
			return new Report(ServerError, ServerErrorMessage);
		}
	}

	private static String net(String strUrl, Map params, String method) throws Exception {
		HttpURLConnection conn;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			if (method == null || "GET".equals(method)) {
				strUrl = strUrl + "?" + urlencode(params);
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || "GET".equals(method)) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			try {
				conn.connect();
				if (params != null && method.equals("POST")) {
					try {
						DataOutputStream out = new DataOutputStream(conn.getOutputStream());
						out.writeBytes(urlencode(params));
					} catch (Exception e) {
						return null;
					}
				}
				InputStream is = conn.getInputStream();
				reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
				String strRead = null;
				while ((strRead = reader.readLine()) != null) {
					sb.append(strRead);
				}
				rs = sb.toString();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					reader.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			}
			return rs;
		} catch (Exception e) {
			return null;
		}
	}
	private static String urlencode(Map<String, Object> data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	private static String createCode(){
		return dev?"123456":CodeUtil.randomNumer(6);
	}
}
