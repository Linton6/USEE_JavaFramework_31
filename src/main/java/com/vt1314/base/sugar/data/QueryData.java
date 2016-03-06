package com.vt1314.base.sugar.data;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: 居泽平  Date: 14/12/10, 09:53
 */
@SuppressWarnings("unused")
public class QueryData<T> {

	private Long totalNum;
	private List<T> dataList;

	public Long getTotalNum() {
		return totalNum;
	}

	public List<T> getDataList() {
		return dataList;
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

	public JSONObject toJSONObject(String methodName) {
		JSONObject result = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		for (T t : this.dataList) {
			Class<? extends Object> clazz = t.getClass();
			try {
				Method method = clazz.getDeclaredMethod(methodName);
				jsonArray.add(method.invoke(t));
				System.out.println("");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		result.put("total", totalNum);
		result.put("rows", jsonArray);

		return result;
	}
}
