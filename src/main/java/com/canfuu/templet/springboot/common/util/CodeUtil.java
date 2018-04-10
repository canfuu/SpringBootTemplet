package com.canfuu.templet.springboot.common.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

/**
 * In server->com.dayukeji.common.utils
 * <p>
 * Create in 16:33 2017/12/10
 *
 * @author canfuu
 * @version v1.0:say explain
 */
public class CodeUtil {
	public static final int[] NUMBER_LIB = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	public static final char[] WORD_LIB_S = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	public static final char[] WORD_LIB_L = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	private static final MessageDigest md5Digest;

	static {
		MessageDigest messageDigest1;
		try {
			messageDigest1 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			messageDigest1 = null;
		}
		md5Digest = messageDigest1;
	}

	public static String randomNumer(int length) {
		StringBuffer code = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			code.append(NUMBER_LIB[random.nextInt(10)]);
		}
		return code.toString();
	}

	public static String transformMD5(String message) throws Exception {
		if (md5Digest != null) {
			BASE64Encoder base64Encoder = new BASE64Encoder();
			return base64Encoder.encode(md5Digest.digest(message.getBytes()));
		} else {
			return null;
		}

	}
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-","");
	}


}
