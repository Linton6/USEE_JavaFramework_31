package com.softtek.base.sugar.tools;

/**
 * Author: 居泽平  Date: 14/12/15, 05:04
 */
public class CommonSugar {

	public static <T> T getTypedDefault(T t1, T t2) {
		return t1 == null ? t2 : t1;
	}
}
