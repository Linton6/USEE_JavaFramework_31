package com.useeinfo.demo.modules.security.util;

import com.useeinfo.demo.common.utils.CommonUtils;
import com.useeinfo.demo.common.utils.DecodePassword;
import com.useeinfo.demo.modules.security.vo.UsernamePasswordVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 权限模块通用的方法
 * Author: wangmin  Date: 13-6-21, 上午15:10
 */
public final class RightsCommonUtils {

	/**
	 * 符号
	 */
	public static final String WHITE_SPACE = " ";
	public static final String COLON = ":";

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 重置密码链接有效期（24小时）
	 */
	private static final long linkTimeOut = 24 * 3600000L;

	private static final Logger logger = LoggerFactory.getLogger(RightsCommonUtils.class);


	/**
	 * get username and password from authorization
	 *
	 * @param authorization
	 * @return
	 */
	public static UsernamePasswordVO getUserNameAndPasswordPair(String authorization) {
		logger.info("----------------------- RightsCommonUtils getUserNameAndPassword Start -----------------------");
		logger.info("authorization : [" + authorization + "]");

		if (StringUtils.isEmpty(authorization)) {
			logger.error("Authorization 为空");
			return null;
		}

		String[] array = authorization.split(WHITE_SPACE);

		if (array.length != 2) {
			logger.error("authorization 内容错误 : [" + authorization + "]");
			return null;
		}

		String[] result = new String[2];

		String data = CommonUtils.getFromBASE64(array[1]);

		if (data.contains(COLON)) {
			result[0] = data.substring(0, data.indexOf(COLON));
			result[1] = data.substring(data.indexOf(COLON) + 1);
		} else {
			logger.error("authorization 内容错误 : [" + authorization + "]");
			return null;
		}


		if (result.length != 2 || StringUtils.isEmpty(result[0]) || StringUtils.isEmpty(result[1])) {
			logger.debug("authorization 内容错误 : [" + authorization + "]");
			return null;
		} else {
			//decode password
			result[1] = DecodePassword.decode(result[1]);
			logger.debug("解码后的密码 [" + result[1] + "]");
			return new UsernamePasswordVO(result[0], result[1]);
		}

	}
}
