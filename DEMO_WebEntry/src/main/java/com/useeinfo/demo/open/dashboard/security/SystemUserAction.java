package com.useeinfo.demo.open.dashboard.security;

import com.useeinfo.demo.modules.security.biz.SystemRoleBiz;
import com.useeinfo.demo.modules.security.biz.SystemUserBiz;
import com.useeinfo.demo.modules.security.entity.SystemRole;
import com.useeinfo.demo.modules.security.entity.SystemUser;
import com.useeinfo.framework.extend.action.BaseAction;
import com.useeinfo.framework.sugar.data.QueryParam;
import com.useeinfo.framework.sugar.tools.StringConverters;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Author: 居泽平
 */
@Controller
@RequestMapping("/web/admin/systemUser")
public class SystemUserAction extends BaseAction {

	//private final static Logger logger = LoggerFactory.getLogger(SystemUserAction.class);
	private final static Integer DEFAULT_PAGE_LIST_NUM = 20;

	@Autowired
	private SystemUserBiz systemUserBiz;

	@Autowired
	private SystemRoleBiz systemRoleBiz;

	// ******************************************************************************
	// ******************************* 代码自动生成开始 *******************************
	// ******************************************************************************

	/**
	 * 打开列表页面
	 */
	@RequestMapping("/getSystemUserListPage")
	public ModelAndView getSystemUserListPage() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("security/systemUser/systemUserList");

		return modelAndView;
	}

	/**
	 * 分页获取JSON数据
	 */
	@RequestMapping("/getSystemUserListJSON")
	@ResponseBody
	public JSONObject getSystemUserListJSON(@RequestParam(value = "page", required = false) String pageNowParam,
	                                        @RequestParam(value = "rows", required = false) String pageSizeParam) {

		QueryParam queryParam = new QueryParam(pageNowParam, pageSizeParam, 0);
		return systemUserBiz.findJSONList(queryParam);
	}

	/**
	 * 获取详情页面
	 */
	@RequestMapping("/getSystemUserViewPage")
	public ModelAndView getSystemUserViewPage(@RequestParam(value = "userId", required = false) String userIdParam) {

		Long userId = StringConverters.ToLong(userIdParam);

		SystemUser systemUser = null;
		if (userId != null) {
			systemUser = systemUserBiz.findModel(userId);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("security/systemUser/systemUserViewPart");
		modelAndView.addObject("systemUser", systemUser);
		return modelAndView;
	}

	/**
	 * 获取编辑页面
	 */
	@RequestMapping("/getSystemUserEditPage")
	public ModelAndView getSystemUserEditPage(@RequestParam(value = "isPage", required = false) String isPageParam,
	                                          @RequestParam(value = "userId", required = false) String userIdParam) {

		Long userId = StringConverters.ToLong(userIdParam);

		SystemUser systemUser = null;
		if (userId != null) {
			systemUser = systemUserBiz.findModel(userId);
		}
		if (systemUser == null && "1".equals(isPageParam)) {
			systemUser = systemUserBiz.getByUserAccount(userIdParam);
		}

		List<SystemRole> roleList = systemRoleBiz.findList();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("security/systemUser/systemUserEditPart");
		modelAndView.addObject("systemUser", systemUser);
		modelAndView.addObject("roleList", roleList);
		modelAndView.addObject("isPage", isPageParam);
		return modelAndView;
	}

	/**
	 * 执行提交的新增或修改请求
	 */
	@RequestMapping(value = "/executeSystemUserEdit", produces = {"text/plain;charset=UTF-8"})
	@ResponseBody
	public String executeSystemUserEdit(SystemUser systemUser) {

		if (systemUser.getSystemRole() != null && systemUser.getSystemRole().getRoleId() == null) {
			systemUser.setSystemRole(null);
		}

		//判断用户名是否重复
		if (systemUserBiz.existUserAccount(systemUser)) {
			return "您输入的用户名为空或已存在，请重新输入";
		}

		systemUserBiz.addOrUpdate(systemUser);
		return "1";
	}

	/**
	 * 逻辑删除机构用户信息
	 */
	@RequestMapping("/logicRemoveSystemUser")
	@ResponseBody
	public String logicRemoveSystemUser(@RequestParam(value = "userId", required = false) String userIdParam,
	                                    @RequestParam(value = "isFakeDelete", required = false) String isFakeDelete) {

		Long userId = StringConverters.ToLong(userIdParam);

		if (isFakeDelete.equals("1")) {
			systemUserBiz.logicRemove(userId);
		} else {
			systemUserBiz.delete(userId);
		}

		return "1";
	}

	// ******************************************************************************
	// ******************************* 代码自动生成结束 *******************************
	// ******************************************************************************
}