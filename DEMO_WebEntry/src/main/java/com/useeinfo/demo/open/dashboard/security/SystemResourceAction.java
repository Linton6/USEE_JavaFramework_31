package com.useeinfo.demo.open.dashboard.security;

import com.useeinfo.demo.modules.security.biz.SystemResourceBiz;
import com.useeinfo.demo.modules.security.entity.SystemResource;
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

/**
 * Author: 居泽平
 */
@Controller
@RequestMapping("/web/admin/systemResource")
public class SystemResourceAction extends BaseAction {

	@Autowired
	private SystemResourceBiz systemResourceBiz;

	// ******************************************************************************
	// ******************************* 代码自动生成开始 *******************************
	// ******************************************************************************

	/**
	 * 打开列表页面
	 */
	@RequestMapping("/getSystemResourceListPage")
	public ModelAndView getSystemResourceListPage() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("security/systemResource/systemResourceList");

		return modelAndView;
	}

	/**
	 * 分页获取JSON数据
	 */
	@RequestMapping("/getSystemResourceListJSON")
	@ResponseBody
	public JSONObject getSystemResourceListJSON(@RequestParam(value = "page", required = false) String pageNowParam,
	                                            @RequestParam(value = "rows", required = false) String pageSizeParam) {

		QueryParam queryParam = new QueryParam(pageNowParam, pageSizeParam, 0);
		return systemResourceBiz.findJSONList(queryParam);
	}

	/**
	 * 获取详情页面
	 */
	@RequestMapping("/getSystemResourceViewPage")
	public ModelAndView getSystemResourceViewPage(@RequestParam(value = "resourceId", required = false) String resourceIdParam) {

		Long resourceId = StringConverters.ToLong(resourceIdParam);

		SystemResource systemResource = null;
		if (resourceId != null) {
			systemResource = systemResourceBiz.findModel(resourceId);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("security/systemResource/systemResourceViewPart");
		modelAndView.addObject("systemResource", systemResource);
		return modelAndView;
	}

	/**
	 * 获取编辑页面
	 */
	@RequestMapping("/getSystemResourceEditPage")
	public ModelAndView getSystemResourceEditPage(@RequestParam(value = "resourceId", required = false) String resourceIdParam) {

		Long resourceId = StringConverters.ToLong(resourceIdParam);

		SystemResource systemResource = null;
		if (resourceId != null) {
			systemResource = systemResourceBiz.findModel(resourceId);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("security/systemResource/systemResourceEditPart");
		modelAndView.addObject("systemResource", systemResource);
		return modelAndView;
	}

	/**
	 * 执行提交的新增或修改请求
	 */
	@RequestMapping("/executeSystemResourceEdit")
	@ResponseBody
	public String executeSystemResourceEdit(SystemResource systemResource) {

		systemResourceBiz.addOrUpdate(systemResource);
		return "1";
	}

	/**
	 * 逻辑删除机构用户信息
	 */
	@RequestMapping("/logicRemoveSystemResource")
	@ResponseBody
	public String logicRemoveSystemResource(@RequestParam(value = "resourceId", required = false) String resourceIdParam,
	                                        @RequestParam(value = "isFakeDelete", required = false) String isFakeDelete) {

		Long resourceId = StringConverters.ToLong(resourceIdParam);

		systemResourceBiz.delete(resourceId);

		return "1";
	}

	// ******************************************************************************
	// ******************************* 代码自动生成结束 *******************************
	// ******************************************************************************
}