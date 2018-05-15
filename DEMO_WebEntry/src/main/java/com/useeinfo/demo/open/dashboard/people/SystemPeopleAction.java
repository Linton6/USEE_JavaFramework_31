package com.useeinfo.demo.open.dashboard.people;

import com.useeinfo.demo.modules.security.biz.SystemRoleBiz;
import com.useeinfo.demo.modules.security.biz.SystemUserBiz;
import com.useeinfo.demo.modules.people.biz.SystemPeopleBiz;
import com.useeinfo.demo.modules.people.entity.SystemPeople;
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
@RequestMapping("/web/admin/people")
public class SystemPeopleAction extends BaseAction {

	//private final static Logger logger = LoggerFactory.getLogger(SystemUserAction.class);
	private final static Integer DEFAULT_PAGE_LIST_NUM = 20;

	@Autowired
	private SystemPeopleBiz systemPeopleBiz;

	@Autowired
	private SystemRoleBiz systemRoleBiz;

	// ******************************************************************************
	// ******************************* 代码自动生成开始 *******************************
	// ******************************************************************************

	/**
	 * 打开列表页面
	 */
	@RequestMapping("/getSystemPeopleListPage")
	public ModelAndView getSystemPeopleListPage() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("people/systemPeopleList");

		return modelAndView;
	}

	/**
	 * 分页获取JSON数据
	 */
	@RequestMapping("/getSystemPeopleListJSON")
	@ResponseBody
	public JSONObject getSystemPeopleListJSON(@RequestParam(value = "page", required = false) String pageNowParam,
	                                        @RequestParam(value = "rows", required = false) String pageSizeParam) {

		QueryParam queryParam = new QueryParam(pageNowParam, pageSizeParam, 0);
		return systemPeopleBiz.findJSONList(queryParam);
	}

	/**
	 * 获取详情页面
	 */
	@RequestMapping("/getSystemPeopleViewPage")
	public ModelAndView getSystemPeopleViewPage(@RequestParam(value = "peopleId", required = false) String peopleIdParam) {

		Long peopleId = StringConverters.ToLong(peopleIdParam);

		SystemPeople systemPeople = null;
		if (peopleId != null) {
			systemPeople = systemPeopleBiz.findModel(peopleId);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("people/systemPeopleViewPart");
		modelAndView.addObject("systemPeople", systemPeople);
		return modelAndView;
	}

	/**
	 * 获取编辑页面
	 */
	@RequestMapping("/getSystemPeopleEditPage")
	public ModelAndView getSystemPeopleEditPage(@RequestParam(value = "isPage", required = false) String isPageParam,
	                                          @RequestParam(value = "peopleId", required = false) String peopleIdParam) {

		Long peopleId = StringConverters.ToLong(peopleIdParam);

		SystemPeople systemPeople = null;
		if (peopleId != null) {
			systemPeople = systemPeopleBiz.findModel(peopleId);
		}
//		if (systemPeople == null && "1".equals(isPageParam)) {
//			systemPeople = systemPeopleBiz.getByPeopleAccount(peopleIdParam);
//		}

//		List<SystemRole> roleList = systemRoleBiz.findList();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("people/systemPeopleEditPart");
		modelAndView.addObject("systemPeople", systemPeople);
//		modelAndView.addObject("roleList", roleList);
		modelAndView.addObject("isPage", isPageParam);
		return modelAndView;
	}

	/**
	 * 执行提交的新增或修改请求
	 */
	@RequestMapping(value = "/executeSystemPeopleEdit", produces = {"text/plain;charset=UTF-8"})
	@ResponseBody
	public String executeSystemPeopleEdit(SystemPeople systemPeople) {

//		if (systemPeople.getSystemRole() != null && systemPeople.getSystemRole().getRoleId() == null) {
//			systemPeople.setSystemRole(null);
//		}

//		判断用户名是否重复
//		if (systemPeopleBiz.existUserAccount(systemUser)) {
//			return "您输入的用户名为空或已存在，请重新输入";
//		}

		systemPeopleBiz.addOrUpdate(systemPeople);
		return "1";
	}

	/**
	 * 逻辑删除机构用户信息
	 */
	@RequestMapping("/logicRemoveSystemUser")
	@ResponseBody
	public String logicRemoveSystemPeople(@RequestParam(value = "peopleId", required = false) String peopleIdParam,
	                                    @RequestParam(value = "isFakeDelete", required = false) String isFakeDelete) {

		Long peopleId = StringConverters.ToLong(peopleIdParam);

		if (isFakeDelete.equals("1")) {
//			systemPeopleBiz.logicRemove(peopleId);
		} else {
			systemPeopleBiz.delete(peopleId);
		}

		return "1";
	}

	// ******************************************************************************
	// ******************************* 代码自动生成结束 *******************************
	// ******************************************************************************
}