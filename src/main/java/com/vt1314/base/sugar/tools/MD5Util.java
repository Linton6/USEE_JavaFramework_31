package com.vt1314.base.sugar.tools;

import java.security.MessageDigest;

/**
 * Author: 居泽平  Date: 15/11/5, 16:19
 */
public class MD5Util {
	/**
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr) {

		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuilder hexValue = new StringBuilder();
		for (byte b : md5Bytes) {
			int val = ((int) b) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */
	public static String convertMD5(String inStr) {

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}

	// 测试主函数
	public static void main(String args[]) {
		String s = "c5680910aaff4d07a8f421df3b2cfc20fieldcodemethodsto.trades.searchpage_no1page_size10timestamp2015-11-05 16:27:18WMSID95683821358WMSSIGNc5680910aaff4d07a8f421df3b2cfc20";
		System.out.println("原始：" + s);
		System.out.println("MD5后：" + string2MD5(s));
		System.out.println("MD5后：" + string2MD5(s).toUpperCase());
		System.out.println("MD5后：" + "AD62BB3027443ED00D6A621FCA668B28".equals(string2MD5(s).toUpperCase()));
		System.out.println("加密的：" + convertMD5(s));
		System.out.println("解密的：" + convertMD5(convertMD5(s)));

	}
}
