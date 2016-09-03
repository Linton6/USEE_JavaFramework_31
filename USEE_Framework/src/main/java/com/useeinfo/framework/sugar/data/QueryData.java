package com.useeinfo.framework.sugar.data;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: 居泽平  Date: 14/12/10, 09:53
 */
@SuppressWarnings("unused")
public class QueryData<T> {

	private final static Logger logger = LoggerFactory.getLogger(QueryData.class);

	private Long totalNum;
	private List<T> dataList;

	public Long getTotalNum() {
		return totalNum;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public QueryData(Long totalNum) {
		this.totalNum = totalNum;
	}

	public QueryData(List<T> dataList) {
		this.dataList = dataList;
	}

	public QueryData(List<T> dataList, Integer totalNum) {

		if (dataList == null) {
			dataList = new ArrayList<>();
		}
		if (totalNum == null) {
			totalNum = 0;
		}

		this.totalNum = Long.valueOf(totalNum);
		this.dataList = dataList;
	}

	public QueryData(List<T> dataList, Long totalNum) {

		if (dataList == null) {
			dataList = new ArrayList<>();
		}
		if (totalNum == null) {
			totalNum = 0L;
		}

		this.totalNum = totalNum;
		this.dataList = dataList;
	}

	public JSONObject toJSONObject() {
		return toJSONObject("toJSONObject");
	}

	public JSONObject toJSONObject(String methodName) {
		JSONObject result = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		for (T t : this.dataList) {
			try {
				Method method = t.getClass().getDeclaredMethod(methodName);
				jsonArray.add(method.invoke(t));
			} catch (Exception ex) {
				logger.error("遍历Object转换JSONObject异常,异常原因:[{}]", ex.getMessage(), ex);
			}
		}

		result.put("errcode", 0);
		result.put("errmsg", "ok");
		result.put("total", totalNum);
		result.put("rows", jsonArray);

		return result;
	}
}
