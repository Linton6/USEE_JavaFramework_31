package com.useeinfo.demo.open.dashboard.security;

import com.useeinfo.framework.extend.action.BaseAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author: 居泽平  Date: 14-11-5
 */
@Controller
@RequestMapping("/security")
public class LoginAction extends BaseAction {

	@RequestMapping("/login")
	public ModelAndView Login() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("security/login");
		return modelAndView;
	}
}
