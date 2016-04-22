package com.ysotek.customer.open.api;

import com.vt1314.framework.sugar.data.ResponseJSON;
import com.ysotek.customer.modules.security.entity.SystemUser;
import com.ysotek.customer.common.utils.UserUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yso on 2016/1/14.
 */

@Controller
@RequestMapping("/api/user")
public class SecurityLoginAPI {

	private final static Logger logger = LoggerFactory.getLogger(SecurityLoginAPI.class);

	@RequestMapping("/login")
	@ResponseBody
	public JSONObject Login(HttpServletRequest request) {

		logger.info("用户登录成功");

		SystemUser systemUser = UserUtils.getSessionUser(request);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("trueName", systemUser.getUserName());
		jsonObject.put("copyright", "无锡雅索");
		jsonObject.put("version", "0.1");
		jsonObject.put("update", false);

		JSONObject result = ResponseJSON.getHandleResult(200, "登录成功", jsonObject);
		logger.info("PDA登录结果:[{}]", result);

		return result;
	}
}
