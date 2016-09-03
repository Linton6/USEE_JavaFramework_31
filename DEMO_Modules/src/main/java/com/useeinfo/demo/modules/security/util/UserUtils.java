package com.useeinfo.demo.modules.security.util;

import com.useeinfo.demo.common.config.ConstantKeySession;
import com.useeinfo.demo.modules.security.entity.SystemUser;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: 居泽平  Date: 16/3/17, 13:16
 */
public class UserUtils {

	public static SystemUser getSessionUser(HttpServletRequest request) {

		/** 开始验证有没有登录 **/
		return (SystemUser) request.getAttribute(ConstantKeySession.HTTP_CUSTOMER_ATTRIBUTE_KEY);
		/** 结束验证有没有登录**/
	}
}
