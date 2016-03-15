package com.company.example.sys.action.page;

import com.company.example.sys.biz.AdminUserBiz;
import com.company.example.sys.entity.AdminUser;
import com.vt1314.sugar.extend.action.BaseAction;
import com.vt1314.sugar.tools.StringConverters;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author: val.jzp
 */
@Controller
@RequestMapping("/web/admin/adminUser")
@SuppressWarnings("unused")
public class AdminUserAction extends BaseAction {

	@Autowired
	private AdminUserBiz adminUserService;

	// ******************************************************************************
	// ********************************* CRUD START *********************************
	// ******************************************************************************

	/**
	 * 打开列表页面
	 */
	@RequestMapping("/getAdminUserListPage")
	public ModelAndView getAdminUserListPage() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/adminUser/adminUserList");

		return modelAndView;
	}

	/**
	 * 分页获取JSON数据
	 */
	@RequestMapping("/getAdminUserListJSON")
	@ResponseBody
	public JSONObject getAdminUserListJSON(@RequestParam(value = "page", required = false) String pageNowParam,
	                                       @RequestParam(value = "rows", required = false) String pageSizeParam) {

		return adminUserService.findJSONList(pageNowParam, pageSizeParam);
	}

	/**
	 * 获取详情页面
	 */
	@RequestMapping("/getAdminUserViewPage")
	public ModelAndView getAdminUserViewPage(@RequestParam(value = "id", required = false) String idParam) {

		Long id = StringConverters.ToLong(idParam);
		AdminUser adminUser = adminUserService.findModel(id);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/adminUser/adminUserViewPart");
		modelAndView.addObject("adminUser", adminUser);
		return modelAndView;
	}

	/**
	 * 获取编辑页面
	 */
	@RequestMapping("/getAdminUserEditPage")
	public ModelAndView getAdminUserEditPage(@RequestParam(value = "id", required = false) String idParam) {

		Long id = StringConverters.ToLong(idParam);
		AdminUser adminUser = adminUserService.findModel(id);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/adminUser/adminUserEditPart");
		modelAndView.addObject("adminUser", adminUser);
		return modelAndView;
	}

	/**
	 * 执行提交的新增或修改请求
	 */
	@RequestMapping("/executeAdminUserEdit")
	@ResponseBody
	public String executeAdminUserEdit(AdminUser adminUser) {

		adminUserService.addOrUpdate(adminUser);
		return "1";
	}

	/**
	 * 逻辑删除机构用户信息
	 */
	@RequestMapping("/logicRemoveAdminUser")
	@ResponseBody
	public String logicRemoveAdminUser(@RequestParam(value = "id", required = false) String idParam,
	                                   @RequestParam(value = "isFakeDelete", required = false) String isFakeDelete) {

		Long id = StringConverters.ToLong(idParam);
		adminUserService.delete(id);

		return "1";
	}

	// ******************************************************************************
	// ********************************** CRUD END **********************************
	// ******************************************************************************
}