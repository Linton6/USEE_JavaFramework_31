package com.useeinfo.demo.modules.security.interceptor;

/**
 * Created by Yso on 2016/1/11.
 */

import com.useeinfo.demo.common.config.ConstantKeySession;
import com.useeinfo.demo.modules.security.biz.SystemUserBiz;
import com.useeinfo.demo.modules.security.entity.SystemUser;
import com.useeinfo.demo.modules.security.util.RightsCommonUtils;
import com.useeinfo.demo.modules.security.vo.UsernamePasswordVO;
import com.useeinfo.framework.sugar.data.ResponseJSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class AuthorityInterceptor extends HandlerInterceptorAdapter {

	/**
	 * HTTP HEADER 中的 Param 字符串
	 */
	public static final String HTTP_HEADER_AUTHORIZATION = "Authorization";
	public static final String HTTP_HEADER_WAREHOUSE = "Warehouse";

	private static final Logger logger = LoggerFactory.getLogger(AuthorityInterceptor.class);

	@Autowired
	private SystemUserBiz systemUserBiz;

	/**
	 * 验证用户的头部中关于权限相关的信息
	 * 这里其实不是验证，而是根据头部的认证信息查找对应的Customer对象，找到以后塞到请求中，以供后续请求使用。
	 * 如果认证信息不正确，则请求中对应的属性为NULL。
	 *
	 * @param request  request
	 * @param response response
	 * @param handler  handler
	 * @return 始终返回true，让业务层判断，这里只做数据处理，不做验证结果的判断
	 * @throws Exception 任何可能的异常。
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String authorization = request.getHeader(HTTP_HEADER_AUTHORIZATION);
		String Warehouse = request.getHeader(HTTP_HEADER_WAREHOUSE);

		logger.debug("开始鉴权：正在鉴权的请求来自：" + request.getRequestURL() + "Authorization内容如下：[" + authorization + "]");

		//根据Authorization解析用户名/密码
		UsernamePasswordVO usernamePasswordVO = RightsCommonUtils.getUserNameAndPasswordPair(authorization);
		if (usernamePasswordVO != null) {
			//根据用户名获取user
			SystemUser user = systemUserBiz.getByUserAccount(usernamePasswordVO.getUsername());
			//判断用户是否存在
			if (null != user && user.getUserPassword().equals(usernamePasswordVO.getPassword())) {
				logger.info("Authorization [" + authorization + "]，验证通过，因此将用户标记为登录。");
				request.setAttribute(ConstantKeySession.HTTP_CUSTOMER_ATTRIBUTE_KEY, user);
				return true;
			}
		}

		logger.warn("Authorization [" + authorization + "]，用户名不存在，登录失败，因此将用户标记为未登录或者登录失败。");

		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(ResponseJSON.getHandleResult(-1, "登录信息不正确，登录失败。"));
		out.close();

		return false;
	}

	@SuppressWarnings("unused")
	public void ResponseWrite(HttpServletResponse response, String info) throws IOException {
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(info);
		out.close();
	}
}
