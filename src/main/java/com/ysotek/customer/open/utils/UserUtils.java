package com.ysotek.customer.open.utils;

import com.ysotek.customer.common.config.ConstantKeySession;
import com.ysotek.customer.modules.security.entity.SystemUser;

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
