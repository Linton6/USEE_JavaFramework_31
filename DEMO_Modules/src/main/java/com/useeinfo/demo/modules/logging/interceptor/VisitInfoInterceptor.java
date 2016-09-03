package com.useeinfo.demo.modules.logging.interceptor;

/**
 * Created by Yso on 2016/1/11.
 */

import com.useeinfo.demo.common.config.ConstantKeySession;
import com.useeinfo.demo.modules.logging.biz.VisitInfoBiz;
import com.useeinfo.demo.modules.logging.entity.VisitInfo;
import com.useeinfo.demo.modules.security.entity.SystemUser;
import com.useeinfo.demo.modules.security.util.RightsCommonUtils;
import com.useeinfo.demo.modules.security.vo.UsernamePasswordVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 访问信息拦截器，主要用于记录用户的访问痕迹
 * <p/>
 * Author: Mingjie Pan  Date: 13-7-6, 上午10:37
 * Reviewed by Jianping.
 */
public class VisitInfoInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(VisitInfoInterceptor.class);

	@Autowired
	private VisitInfoBiz visitInfoBiz;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		logger.debug("接收到一个新的请求，开始记录用户的操作痕迹");
		String url = request.getRequestURL().toString();
		String userAgent = request.getHeader("User-Agent");
		String authorization = request.getHeader("Authorization");

		UsernamePasswordVO userinfo = RightsCommonUtils.getUserNameAndPasswordPair(authorization);
		if (userinfo == null) {
			return true;
		}

		SystemUser systemUser = (SystemUser) request.getAttribute(ConstantKeySession.HTTP_CUSTOMER_ATTRIBUTE_KEY);
		if (systemUser == null) {
			return true;
		}

		if (userAgent != null && url.contains("login")) {
			Integer phoneSystem = 0;
			boolean androidFlag = userAgent.contains("Android");
			boolean iosFlag = userAgent.contains("iPhone") || userAgent.contains("iPod") || userAgent.contains("iPad") || userAgent.contains("iTouch");
			logger.debug("访问的设备为：" + (androidFlag ? "Android" : (iosFlag ? "IOS" : "other")));
			//if(iosFlag
			//		&& (customer.getPhoneSystem() == null || customer.getPhoneSystem() != 2)){
			//	// 更新customer对应手机系统为IOS(2)
			//	phoneSystem = 2;
			//	customerBiz.updatePhoneSystem(customer.getDjid(),phoneSystem);
			//}else if(androidFlag
			//		&& (customer.getPhoneSystem() == null || customer.getPhoneSystem() != 1)){
			//	// 更新customer对应手机系统为android(1)
			//	phoneSystem = 1;
			//	customerBiz.updatePhoneSystem(customer.getDjid(),phoneSystem);
			//}
		}


		VisitInfo bean = new VisitInfo();
		bean.setUserName(userinfo.getUsername());
		bean.setSystemUser(systemUser);
		bean.setUserAgent(userAgent);
		bean.setVisitTime(new Date());
		bean.setUrl(url);
		bean.setClientIP(request.getRemoteAddr());
		bean.setAuthorization(authorization);
		visitInfoBiz.logVisitInfo(bean);

		logger.debug("以下是用户的访问痕迹：" + bean);

		return true;
	}
}

