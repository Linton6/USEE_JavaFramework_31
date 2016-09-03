package com.useeinfo.framework.sugar.tools;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Author: 居泽平  Date: 15/11/5, 19:36
 */
public class XmlParser {

	private static final Logger logger = LoggerFactory.getLogger(XmlParser.class);

	/**
	 * 将XML转换为Map对象
	 * 该方法仅适用于简单的没有层级关系的XML转换
	 */
	public static Document xml2java(String xmlMsg) {

		logger.debug("开始将输入流转换为Java对象。");

		try {
			InputStream inputStream = new ByteArrayInputStream(xmlMsg.getBytes("UTF-8"));
			// 读取输入流
			SAXReader reader = new SAXReader();

			return reader.read(inputStream);
		} catch (Exception e) {
			logger.error("将输入流转换为Java对象失败.", e);
			return null;
		}
	}
}
