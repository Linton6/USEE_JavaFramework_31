package com.softtek.base.sugar.web;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: Liping Ye  Date: 13-6-22, 下午5:23
 */
public class WebLayerUtil {

	private final static Logger logger = LoggerFactory.getLogger(WebLayerUtil.class);

	private final static String USER_AGENT = "User-Agent";

	/**
	 * 返回错误信息
	 *
	 * @param url    请求路径
	 * @param code   错误代码
	 * @param reason 错误描述
	 * @return JSONObject json对象
	 */
	public static JSONObject getError(String url, String code, String reason) {
		JSONObject error = new JSONObject();
		JSONObject errorJson = new JSONObject();

		error.put("url", url);
		error.put("statuscode", code);
		error.put("description", reason);

		errorJson.put("error", error);
		return errorJson;
	}

	/**
	 * 返回错误信息（get请求时，获取其查询参数）
	 *
	 * @param request 请求对象
	 * @param code    错误代码
	 * @param reason  错误描述
	 * @return JSONObject json对象
	 */
	public static JSONObject getErrorWithRequestParam(HttpServletRequest request, String code, String reason) {
		//居泽平修改，注释了以下代码
		/*StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(request.getRequestURL().toString());
		if(request.getQueryString() != null){
			stringBuilder.append("?" + request.getQueryString());
		}*/
		return getError(getFullRequestURL(request), code, reason);
	}

	/**
	 * 返回成功信息
	 *
	 * @param url     请求路径
	 * @param message 成功描述
	 * @return JSONObject json对象
	 */
	public static JSONObject getSuccess(String url, String message) {
		JSONObject info = new JSONObject();
		JSONObject success = new JSONObject();

		info.put("url", url);
		info.put("description", message);

		success.put("success", info);
		return success;
	}

	/**
	 * 返回成功信息
	 *
	 * @return JSONObject json对象
	 */
	public static JSONObject genReturnStatus(int code, String description) {

		JSONObject success = new JSONObject();

		success.put("statuscode", code);
		success.put("description", description);

		return success;
	}

	/**
	 * 根据节点名称返回一个 {"nodeName":{}} 格式的对象
	 *
	 * @param nodeName 节点名称
	 * @return JSON Object.
	 */
	public static JSONObject getJsonObjectNull(String nodeName) {
		return getJsonObjectByObjectNode(nodeName, null);
	}

	/**
	 * 根据节点名称返回一个 {"nodeName":[]} 格式的对象
	 *
	 * @param nodeName 节点名称
	 * @return JSON Object.
	 */
	public static JSONObject getJsonArrayNull(String nodeName) {
		return getJsonObjectByArrayNode(nodeName, null);
	}

	/**
	 * 定义了根据节点名称获取JSONObject对象的方法
	 *
	 * @param nodeName  节点名称
	 * @param nodeValue 节点参数，默认为空
	 * @return JSON Object.
	 */
	public static JSONObject getJsonObjectByObjectNode(String nodeName, JSONObject nodeValue) {
		if (nodeValue == null) {
			nodeValue = new JSONObject();
		}
		return getJsonByNodeName(nodeName, nodeValue);
	}

	/**
	 * 定义了根据节点名称获取JSONObject对象的方法
	 *
	 * @param nodeName  节点名称
	 * @param nodeValue 节点参数，默认为空
	 * @return JSON Object.
	 */
	public static JSONObject getJsonObjectByArrayNode(String nodeName, JSONArray nodeValue) {
		if (nodeValue == null) {
			nodeValue = new JSONArray();
		}
		return getJsonByNodeName(nodeName, nodeValue);
	}

	/**
	 * 根据传入参数装配 JSONObject.
	 *
	 * @param nodeName  节点名称
	 * @param nodeValue 节点值
	 * @return JSON Object.
	 */
	private static JSONObject getJsonByNodeName(String nodeName, Object nodeValue) {
		JSONObject node = new JSONObject();
		node.put(nodeName, nodeValue);
		return node;
	}

	/**
	 * 获取当前请求的完整URL
	 *
	 * @param request HttpServletRequest Object.
	 * @return String.
	 */
	public static String getFullRequestURL(HttpServletRequest request) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(request.getRequestURL().toString());
		if (request.getQueryString() != null) {
			stringBuilder.append("?" + request.getQueryString());
		}
		return stringBuilder.toString();
	}

	/**
	 * 获取当前请求的URL
	 *
	 * @param request HttpServletRequest Object.
	 * @return String.
	 */
	public static String getRequestURL(HttpServletRequest request) {
		return request.getRequestURL().toString();// + "?" + request.getQueryString();
	}

	public static void setRequestLog(HttpServletRequest request) {
		logger.info("---------------------------------------------- HttpServletRequest Start ----------------------------------------------");
		logger.info("Request Url : " + getRequestURL(request));
	}

	public static void setActionStartLog() {
		logger.info("---------------------------------------------------- Action Start ----------------------------------------------------");
	}

	public static int getDefaultByNullInteger(Integer testInt, Integer defaultInt) {
		if (testInt == null) {
			return defaultInt;
		} else {
			return testInt;
		}
	}

	public static Double getDefaultByNullDouble(Double testDouble, Double defaultDouble) {
		if (testDouble == null) {
			return defaultDouble;
		} else {
			return testDouble;
		}
	}

	public static String getDefaultByNullString(String testString, String defaultString) {
		if (testString == null) {
			return defaultString;
		} else {
			return testString;
		}
	}

	public static Long getDefaultByNullLong(Long testLong, Long defaultLong) {
		if (testLong == null) {
			return defaultLong;
		} else {
			return testLong;
		}
	}

	/**
	 * 根据user-agent得到客户端版本
	 */
	public static Double getClientVersion(HttpServletRequest request) {

		String userAgent = request.getHeader(USER_AGENT);
		if (userAgent == null) {
			return null;
		}

		int startPos = userAgent.indexOf("/");
		int endPos = userAgent.indexOf("(");

		if (startPos < 0 || endPos < 0 || startPos >= endPos) {
			return null;
		}

		try {
			Double version = Double.parseDouble(userAgent.substring(startPos + 1, endPos));
			logger.debug("获取版本成功，版本号是：" + version);
			return version;
		} catch (NumberFormatException e) {
			logger.error("获取版本失败", e);
			return null;
		}
	}


}
