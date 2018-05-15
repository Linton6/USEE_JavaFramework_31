package com.useeinfo.demo.open.dashboard.work;

import com.useeinfo.demo.modules.security.biz.SystemAuthorityBiz;
import com.useeinfo.demo.modules.work.entity.SystemWork;
import com.useeinfo.demo.modules.work.biz.SystemWorkBiz;
import com.useeinfo.demo.modules.work.dao.SystemWorkDao;
import com.useeinfo.demo.modules.security.biz.SystemUserBiz;
import com.useeinfo.demo.modules.security.entity.SystemAuthority;
import com.useeinfo.demo.modules.security.entity.SystemUser;
import com.useeinfo.framework.extend.action.BaseAction;
import com.useeinfo.framework.sugar.data.QueryParam;
import com.useeinfo.framework.sugar.tools.StringConverters;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: 居泽平
 */
@Controller
@RequestMapping("/web/admin/work")
public class SystemWorkAction extends BaseAction {

	@Autowired
	private SystemWorkBiz systemWorkBiz;

	@Autowired
	private SystemUserBiz systemUserBiz;

	@Autowired
	private SystemAuthorityBiz systemAuthorityBiz;

	/**
	 * 跳转到角色修改页面
//	 */
	@RequestMapping("/updateWorkPage.action")
	public ModelAndView UpdateWorkPage(@RequestParam(value = "workId", required = false) String workIdParam) {

		Long workId = StringConverters.ToLong(workIdParam);
		Map<String, Object> map = systemWorkBiz.getWorkAndAuthById(workId);
//		return new ModelAndView("security/system/role/sys_role_manage", map);
		return null;
	}

	/**
	 * 移除权限
//	 */
//	@RequestMapping("/removeAuthFromRole.action")
//	@ResponseBody
//	public Object RemoveAuth(@RequestParam(value = "roleId", required = false) String roleIdParam,
//	                         @RequestParam(value = "authId", required = false) String authIdParam) {
//
//		Long roleId = StringConverters.ToLong(roleIdParam);
//		Long authId = StringConverters.ToLong(authIdParam);
//
//		SystemAuthority auth = systemRoleBiz.deleteAuthoritiesFromRoles(roleId, authId);
//		auth.setResourceSet(null);
//		auth.setRoleSet(null);
//
//		Map<String, Object> map = new HashMap<>();
//		map.put("result", 1);
//		map.put("auth", auth);
//		return map;
//	}

	/**
	 * 添加权限
//	 */
//	@RequestMapping("/addAuthToRole.action")
//	@ResponseBody
//	public Object AddAuth(@RequestParam(value = "roleId", required = false) String roleIdParam,
//	                      @RequestParam(value = "authId", required = false) String authIdParam) {
//
//		Long roleId = StringConverters.ToLong(roleIdParam);
//		Long authId = StringConverters.ToLong(authIdParam);
//
//		SystemAuthority auth = systemRoleBiz.addAuthoritiesToRoles(roleId, authId);
//		auth.setResourceSet(null);
//		auth.setRoleSet(null);
//
//		Map<String, Object> map = new HashMap<>();
//		map.put("result", 1);
//		map.put("auth", auth);
//		return map;
//	}

	// ******************************************************************************
	// ******************************* 代码自动生成开始 *******************************
	// ******************************************************************************

	/**
	 * 打开列表页面
	 */
	@RequestMapping("/getSystemWorkListPage")
	public ModelAndView getSystemWorkListPage() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("work/systemWorkList");

		return modelAndView;
	}

	/**
	 * 分页获取JSON数据
	 */
	@RequestMapping("/getSystemWorkListJSON")
	@ResponseBody
	public JSONObject getSystemWorkListJSON(@RequestParam(value = "page", required = false) String pageNowParam,
	                                        @RequestParam(value = "rows", required = false) String pageSizeParam) {

		QueryParam queryParam = new QueryParam(pageNowParam, pageSizeParam, 0);
		return systemWorkBiz.findJSONList(queryParam);
	}

	/**
	 * 获取详情页面
	 */
	@RequestMapping("/getSystemWorkViewPage")
	public ModelAndView getSystemWorkViewPage(@RequestParam(value = "workId", required = false) String workIdParam) {

		Long workId = StringConverters.ToLong(workIdParam);

		SystemWork systemWork = null;
		if (workId != null) {
			systemWork = systemWorkBiz.findModel(workId);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("work/systemWorkViewPart");
		modelAndView.addObject("systemWork", systemWork);
		return modelAndView;
	}

	/**
	 * 获取编辑页面
	 */
	@RequestMapping("/getSystemWorkEditPage")
	public ModelAndView getSystemWorkEditPage(@RequestParam(value = "workId", required = false) String workIdParam) {

		Long workId = StringConverters.ToLong(workIdParam);

		SystemWork systemWork = null;
		if (workId != null) {
			systemWork = systemWorkBiz.findModel(workId);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("work/systemWorkEditPart");
		modelAndView.addObject("systemWork", systemWork);
		return modelAndView;
	}

	/**
	 * 执行提交的新增或修改请求
	 */
	@RequestMapping(value = "/executeSystemWorkEdit", produces = {"text/plain;charset=UTF-8"})
	@ResponseBody
	public String executeSystemWorkEdit(SystemWork systemWork) {

		//判断角色名是否重复
//		if (systemWorkBiz.existWorkName(systemWork)) {
//			return "您输入的角色名为空或已存在，请重新输入";
//		}

		systemWorkBiz.addOrUpdate(systemWork);
		return "1";
	}

	/**
	 * 逻辑删除机构用户信息
//	 */
	@RequestMapping("/logicRemoveSystemWork")
	@ResponseBody
	public String logicRemoveSystemWork(@RequestParam(value = "workId", required = false) String workIdParam,
	                                    @RequestParam(value = "isFakeDelete", required = false) String isFakeDelete) {

		Long workId = StringConverters.ToLong(workIdParam);

		List<SystemUser> systemUserList = this.systemUserBiz.findByRoleId(workId);
		List<SystemAuthority> systemAuthorityList = this.systemAuthorityBiz.getAuthoritiesByRoleId(workId);

		if (CollectionUtils.isEmpty(systemUserList) && CollectionUtils.isEmpty(systemAuthorityList)) {
			systemWorkBiz.delete(workId);
			return "1";
		}
		return "2";
	}

	// ******************************************************************************
	// ******************************* 代码自动生成结束 *******************************
	// ******************************************************************************
}