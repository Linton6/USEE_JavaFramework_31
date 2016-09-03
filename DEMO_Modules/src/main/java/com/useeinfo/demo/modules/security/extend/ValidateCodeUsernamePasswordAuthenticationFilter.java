package com.useeinfo.demo.modules.security.extend;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 带验证码校验功能的用户名、密码认证过滤器
 * <p/>
 * 支持不输入验证码；支持验证码忽略大小写。
 *
 * @author Long
 */
public class ValidateCodeUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		/*String warehouse = request.getParameter("warehouse");
		if (StringUtils.isNotBlank(warehouse)) {
			String warehouseInfo[] = warehouse.split("\\|\\|");
			request.getSession().setAttribute(ConstantKeySession.WEB_USER_WAREHOUSE_KEY, warehouseInfo[0]);
			request.getSession().setAttribute(ConstantKeySession.WEB_USER_WAREHOUSE_Name, warehouseInfo[1]);
		}

		String username = CommonSugar.getTypedDefault(obtainUsername(request), "");
		request.getSession().setAttribute(ConstantKeySession.WEB_USER_INFO_KEY, username);*/

		return super.attemptAuthentication(request, response);
	}
}