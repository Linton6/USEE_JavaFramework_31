package com.useeinfo.demo.common.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: 居泽平  Date: 13-11-24, 下午11:20
 */
@SuppressWarnings("unused")
public class ExcelImportMap {

	public static Map<Integer, String> KNOWLEDGE_BASE_ENTER_IMPORT_MAP() {
		Map<Integer, String> cellPropertyMap = new HashMap<>();
		int i = 0;
		cellPropertyMap.put(i++, "questionId"); // 业务ID
		cellPropertyMap.put(i, "answer"); // 回复
		return cellPropertyMap;
	}
}
