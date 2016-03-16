package com.vt1314.base.sugar.data;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Author: 张霄鹏  Date: 14-2-10 14:55
 */
@SuppressWarnings("unused")
public class ResponseJSON {

	/**
	 * 返回错误信息
	 *
	 * @param code 错误代码
	 * @param desc 描述
	 * @return JSONObject json对象
	 */
	public static JSONObject getSimpleReturn(String code, String desc) {

		JSONObject result = new JSONObject();

		result.put("code", code);
		result.put("desc", desc);

		return result;
	}

	/**
	 * 返回错误信息
	 *
	 * @param code 错误代码
	 * @param desc 描述
	 * @return JSONObject json对象
	 */
	public static JSONObject getSimpleReturn(int code, String desc) {
		return getSimpleReturn(code + "", desc);
	}

	public static JSONObject getImportResultJson(String resultDesc, JSONArray jsonArray) {

		Integer total = jsonArray == null ? 0 : jsonArray.size();

		JSONObject result = new JSONObject();

		result.put("resultDesc", resultDesc);
		result.put("total", total);
		result.put("rows", jsonArray);

		return result;
	}

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
