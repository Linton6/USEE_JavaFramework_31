package com.useeinfo.framework.sugar.tools;

import java.math.BigDecimal;

/**
 * Author: 居泽平  Date: 15/10/13, 17:19
 */
@SuppressWarnings("unused")
public class CalculateUtil {

	private static final int DEF_DIV_SCALE = 10;

	private CalculateUtil() {
	}

	public static float add(float d1, float d2) {
		BigDecimal b1 = new BigDecimal(Float.toString(d1));
		BigDecimal b2 = new BigDecimal(Float.toString(d2));
		return b1.add(b2).floatValue();
	}

	public static double add(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.add(b2).doubleValue();
	}

	public static double sub(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.subtract(b2).doubleValue();
	}

	public static float mul(int d1, float d2) {
		BigDecimal b1 = new BigDecimal(Integer.toString(d1));
		BigDecimal b2 = new BigDecimal(Float.toString(d2));
		return b1.multiply(b2).floatValue();
	}

	public static double mul(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.multiply(b2).doubleValue();
	}

	public static double div(double d1, double d2) {

		return div(d1, d2, DEF_DIV_SCALE);

	}

	public static double div(double d1, double d2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

	}
}