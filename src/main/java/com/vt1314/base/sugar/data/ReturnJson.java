package com.vt1314.base.sugar.data;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Liping Ye  Date: 13-6-22, 下午5:23
 */
public class ReturnJson {

	private final static Logger logger = LoggerFactory.getLogger(ReturnJson.class);


	/**
	 * 返回错误信息
	 *
	 * @return JSONObject json对象
	 */
	public static JSONObject getUserInfoMismatchError() {
		JSONObject errorJson = new JSONObject();

		errorJson.put("stat", -1);
		errorJson.put("msg", "登录信息不正确，登录失败。");

		return errorJson;
	}


	/**
	 * 返回成功信息
	 *
	 * @return JSONObject json对象
	 */
	public static JSONObject genReturnStatus(int stat, String description, JSONObject jsonObject) {

		JSONObject success = new JSONObject();

		success.put("stat", stat);
		success.put("msg", description);
		success.put("data", jsonObject);

		return success;
	}


	/**
	 * 返回错误信息
	 *
	 * @return JSONObject json对象
	 */
	public static JSONObject getErrorMsg(String msg) {
		JSONObject resultObject = new JSONObject();
		resultObject.put("stat", -1);
		resultObject.put("msg", msg);
		return resultObject;
	}


}
