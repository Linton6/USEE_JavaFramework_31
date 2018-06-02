package com.useeinfo.framework.sugar.tools;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: 居泽平  Date: 14/12/15, 05:04
 */
public class CommonSugar {

	public static <T> T getTypedDefault(T t1, T t2) {
		return t1 == null ? t2 : t1;
	}

	public static String getStringDefault(String s1, String s2) {
		return StringUtils.isBlank(s1) ? s2 : s1;
	}

//	public static  getStringDefault(String s1, String s2) {
//		return StringUtils.isBlank(s1) ? s2 : s1;
//	}

	public static boolean isContainChinese(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		return m.find();
	}
}
