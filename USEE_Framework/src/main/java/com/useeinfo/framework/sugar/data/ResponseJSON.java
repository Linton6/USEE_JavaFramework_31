package com.useeinfo.framework.sugar.data;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

/**
 * Author: 张霄鹏  Date: 14-2-10 14:55
 */
@SuppressWarnings("unused")
public class ResponseJSON {

	/**
	 * 返回操作结果信息
	 */
	public static JSONObject getHandleResult(int stat, String msg) {
		return getHandleResult(stat, msg, null);
	}

	public static JSONObject getHandleResult(int stat, String msg, JSON data) {
		JSONObject result = new JSONObject();

		result.put("stat", stat);
		result.put("msg", msg);
		if (data != null) {
			result.put("data", data);
		}

		return result;
	}
}
