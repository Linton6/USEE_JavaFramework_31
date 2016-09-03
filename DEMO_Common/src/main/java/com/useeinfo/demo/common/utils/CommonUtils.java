package com.useeinfo.demo.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Author: wangmin  Date: 13-6-21, 上午15:10
 */
public final class CommonUtils {

	private static final String emailReg = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

	private static final String hourMinuteReg = "^(?:[0-1][0-9]|2[0-3]):(?:[0-5][0-9])$";

	private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

	/**
	 * validate email address
	 *
	 * @param email
	 * @return
	 */
	public static boolean validateEmail(String email) {
		return !StringUtils.isEmpty(email) && verifyEmail(email);
	}

	/**
	 * Base64 encode
	 *
	 * @param s
	 * @return
	 */
	public static String getBASE64(String s) {
		if (StringUtils.isEmpty(s)) {
			return null;
		}
		return Base64.encodeBase64String(s.getBytes());
	}

	/**
	 * Base64 decode
	 *
	 * @param s
	 * @return
	 */
	public static String getFromBASE64(String s) {
		if (StringUtils.isEmpty(s)) return null;
		try {
			return new String(Base64.decodeBase64(s));
		} catch (Exception e) {
			logger.error("进行Base64解码失败", e);
			return null;
		}
	}

	/**
	 * verify emal
	 *
	 * @param email
	 * @return boolean
	 */
	public static boolean verifyEmail(String email) {
		if (!StringUtils.isEmpty(email)) {
			return email.matches(emailReg);
		} else {
			return false;
		}
	}

	/**
	 * 验证时间格式（HH:mm）
	 *
	 * @param time
	 * @return boolean
	 */
	public static boolean verifyHourMinute(String time) {
		if (!StringUtils.isEmpty(time)) {
			return time.matches(hourMinuteReg);
		} else {
			return false;
		}
	}

	/**
	 * 检查参数是否为空
	 *
	 * @param args
	 * @return boolean
	 * @author wangmin
	 */
	public static boolean checkIfArgsNull(String... args) {
		for (String arg : args) {
			if (StringUtils.isEmpty(arg))
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		//String encode = getBASE64("haixinkewangmin");
		//System.out.println(encode);
		//System.out.println(getFromBASE64(encode));
		//String email = "hai3343x_4545in@1dfd3.com";
		//System.out.println(verifyEmail(email));
		logger.debug("Basic " + getBASE64("haixinke:123"));
		//logger.debug("Basic "+getFromBASE64(getBASE64("haixinke")));
		//logger.debug(getFromBASE64("MTAwMDAwOTUxOjI2OjExMTExMg=="));
		//logger.debug(Integer.valueOf("09").toString());
		//(?:0[1-9]|1[0-9]|2[0-8])
		String aa = "^(?:[0-1][0-9]|2[0-3]):(?:[0-5][0-9])$";
		logger.debug("23:59".matches(aa) + "");

	}


}
