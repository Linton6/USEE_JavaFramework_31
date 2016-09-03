/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.useeinfo.framework.extend.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * Service基类
 *
 * @author 居泽平
 * @version 2016-03-10
 */
@Transactional(readOnly = true)
public abstract class BaseBiz {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 数据范围过滤
	 *
	 * @return 标准连接条件对象
	 */
	public static String dataScopeFilter(HttpServletRequest request) {

		return "";
	}
}
