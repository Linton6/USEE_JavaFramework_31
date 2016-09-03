package com.useeinfo.demo.common.config;

import org.apache.commons.lang.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author: 居泽平  Date: 13-11-24, 下午11:20
 */
@SuppressWarnings("unused")
public class ExcelExportMap {

	public static Map<String, String> CUSTOMER_EXPORT_MAP(String selectFields) {

		Map<String, String> columnPropertyMap = new LinkedHashMap<>();
		columnPropertyMap.put("account", "一米帐号");

		Map<String, String> columnPropertySelectMap = new LinkedHashMap<>();
		if (StringUtils.isNotEmpty(selectFields)) {
			String[] fields = selectFields.split(",");
			for (String field : fields) {
				columnPropertySelectMap.put(field, columnPropertyMap.get(field));
			}
		}

		return columnPropertySelectMap;
	}
}
