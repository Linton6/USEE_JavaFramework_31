package com.useeinfo.demo.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 密码解密
 * 字符串，各个字符ascii - 1，返回字符串
 * Author: Liping Ye  Date: 13-6-17, 下午6:10
 */
public class DecodePassword {

	private static final Logger logger = LoggerFactory.getLogger(DecodePassword.class);

	/**
	 * 密码解密
	 *
	 * @param str 每个字符ascii码11
	 * @return 解密后字符串
	 */
	public static String decode(String str) {
		StringBuilder stringBuilder = new StringBuilder("");

		char[] charArray = str.toCharArray();
		int size = charArray.length;
		for (int i = 0; i < size; i++) {
			int asc = ((int) charArray[i]) - 1;
			stringBuilder.append((char) asc);
		}
		logger.debug("解密方法，原字符串：[" + str + "],处理后字符串：[" + stringBuilder.toString() + "]");
		return stringBuilder.toString();
	}

	/**
	 * 字符串加密
	 *
	 * @param str 每个字符ascii码+1
	 * @return 加密后字符串
	 */
	public static String encode(String str) {
		StringBuilder stringBuilder = new StringBuilder("");

		char[] charArray = str.toCharArray();
		int size = charArray.length;
		for (int i = 0; i < size; i++) {
			int asc = ((int) charArray[i]) + 1;
			stringBuilder.append((char) asc);
		}
		logger.debug("加密方法，原字符串：[" + str + "],处理后字符串：[" + stringBuilder.toString() + "]");
		return stringBuilder.toString();
	}

	public static void main(String[] args) {
		logger.error(decode("81:111112"));
	}
}
