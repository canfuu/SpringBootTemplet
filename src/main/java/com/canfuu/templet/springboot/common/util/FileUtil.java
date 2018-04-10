package com.canfuu.templet.springboot.common.util;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * In LawyerWeb->com.dayu.lawyer.common.utils
 * <p>
 * Create in 16:52 2017/11/9
 *
 * @author canfuu
 * @version v1.0:say explain
 */
public class FileUtil {
	public static String saveFile(String proWebPath,String proLocalPath,String fileName,String base64){
		try{
			if(base64!=null&& Base64.isBase64(base64)){// Base64.isBase64(base64)判断base64字符串是否为Base64码
				byte[] bytes = Base64.decodeBase64(base64); //将base64字符串转为byte[]
				FileChannel fileChannel = new FileOutputStream(proLocalPath+fileName).getChannel();
				fileChannel.write(ByteBuffer.wrap(bytes));
				fileChannel.close();
				return proWebPath+fileName;
			}
			return null;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static String saveFile(String proWebPath,String proLocalPath,String fileName,byte[] bytes){
		try{
			FileChannel fileChannel = new FileOutputStream(proLocalPath+fileName).getChannel();
			fileChannel.write(ByteBuffer.wrap(bytes));
			fileChannel.close();
			return proLocalPath+fileName;
		} catch(Exception e){
			return null;
		}
	}


}
