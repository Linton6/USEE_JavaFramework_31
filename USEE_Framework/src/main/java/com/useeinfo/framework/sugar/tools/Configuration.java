package com.useeinfo.framework.sugar.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Author: 居泽平
 */
@SuppressWarnings("unused")
public class Configuration {

	private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

	private static final Properties properties;

	private static final String CONFIG_FILE = "config.properties";

	static {
		try {
			ClassPathResource resource = new ClassPathResource(CONFIG_FILE);
			properties = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException ex) {
			throw new IllegalStateException("Could not load '[" + CONFIG_FILE + "]': " + ex.getMessage());
		}
	}

	public static String getConfigurationByName(String keyName) {
		if (StringUtils.isEmpty(keyName)) {
			return "";
		}
		logger.debug("start loading property,name : [" + keyName + "]");
		String property = CommonSugar.getStringDefault(properties.getProperty(keyName), "");

		try {
			if (property.equals(new String(property.getBytes("ISO-8859-1"), "ISO-8859-1"))) {
				property = new String(property.getBytes("ISO-8859-1"), "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("转换配置文件编码出错");
		}

		return property.trim();
	}

	public static String getConfigBaseUrl() {
		String baseUrl = Configuration.getConfigurationByName("BaseUrl");
		baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
		return baseUrl;
	}
}
