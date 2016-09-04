package com.useeinfo.demo.open.dashboard.security;

import com.useeinfo.demo.modules.security.biz.SystemAuthorityBiz;
import com.useeinfo.demo.modules.security.biz.SystemRoleBiz;
import com.useeinfo.demo.modules.security.biz.SystemUserBiz;
import com.useeinfo.demo.modules.security.entity.SystemAuthority;
import com.useeinfo.demo.modules.security.entity.SystemRole;
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
@RequestMapping("/web/admin/systemRole")
public class SystemRoleAction extends BaseAction {

	@Autowired
	private SystemRoleBiz systemRoleBiz;

	@Autowired
	private SystemUserBiz systemUserBiz;

	@Autowired
	private SystemAuthorityBiz systemAuthorityBiz;

	/**
	 * 跳转到角色修改页面
	 */
	@RequestMapping("/updateRolePage.action")
	public ModelAndView UpdateRolePage(@RequestParam(value = "roleId", required = false) String roleIdParam) {

		Long roleId = StringConverters.ToLong(roleIdParam);
		Map<String, Object> map = systemRoleBiz.getRoleAndAuthById(roleId);
		return new ModelAndView("security/system/role/sys_role_manage", map);
	}

	/**
	 * 移除权限
	 */
	@RequestMapping("/removeAuthFromRole.action")
	@ResponseBody
	public Object RemoveAuth(@RequestParam(value = "roleId", required = false) String roleIdParam,
	                         @RequestParam(value = "authId", required = false) String authIdParam) {

		Long roleId = StringConverters.ToLong(roleIdParam);
		Long authId = StringConverters.ToLong(authIdParam);

		SystemAuthority auth = systemRoleBiz.deleteAuthoritiesFromRoles(roleId, authId);
		auth.setResourceSet(null);
		auth.setRoleSet(null);

		Map<String, Object> map = new HashMap<>();
		map.put("result", 1);
		map.put("auth", auth);
		return map;
	}

	/**
	 * 添加权限
	 */
	@RequestMapping("/addAuthToRole.action")
	@ResponseBody
	public Object AddAuth(@RequestParam(value = "roleId", required = false) String roleIdParam,
	                      @RequestParam(value = "authId", required = false) String authIdParam) {

		Long roleId = StringConverters.ToLong(roleIdParam);
		Long authId = StringConverters.ToLong(authIdParam);

		SystemAuthority auth = systemRoleBiz.addAuthoritiesToRoles(roleId, authId);
		auth.setResourceSet(null);
		auth.setRoleSet(null);

		Map<String, Object> map = new HashMap<>();
		map.put("result", 1);
		map.put("auth", auth);
		return map;
	}

	// ******************************************************************************
	// ******************************* 代码自动生成开始 *******************************
	// ******************************************************************************

	/**
	 * 打开列表页面
	 */
	@RequestMapping("/getSystemRoleListPage")
	public ModelAndView getSystemRoleListPage() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("security/systemRole/systemRoleList");

		return modelAndView;
	}

	/**
	 * 分页获取JSON数据
	 */
	@RequestMapping("/getSystemRoleListJSON")
	@ResponseBody
	public JSONObject getSystemRoleListJSON(@RequestParam(value = "page", required = false) String pageNowParam,
	                                        @RequestParam(value = "rows", required = false) String pageSizeParam) {

		QueryParam queryParam = new QueryParam(pageNowParam, pageSizeParam, 0);
		return systemRoleBiz.findJSONList(queryParam);
	}

	/**
	 * 获取详情页面
	 */
	@RequestMapping("/getSystemRoleViewPage")
	public ModelAndView getSystemRoleViewPage(@RequestParam(value = "roleId", required = false) String roleIdParam) {

		Long roleId = StringConverters.ToLong(roleIdParam);

		SystemRole systemRole = null;
		if (roleId != null) {
			systemRole = systemRoleBiz.findModel(roleId);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("security/systemRole/systemRoleViewPart");
		modelAndView.addObject("systemRole", systemRole);
		return modelAndView;
	}

	/**
	 * 获取编辑页面
	 */
	@RequestMapping("/getSystemRoleEditPage")
	public ModelAndView getSystemRoleEditPage(@RequestParam(value = "roleId", required = false) String roleIdParam) {

		Long roleId = StringConverters.ToLong(roleIdParam);

		SystemRole systemRole = null;
		if (roleId != null) {
			systemRole = systemRoleBiz.findModel(roleId);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("security/systemRole/systemRoleEditPart");
		modelAndView.addObject("systemRole", systemRole);
		return modelAndView;
	}

	/**
	 * 执行提交的新增或修改请求
	 */
	@RequestMapping(value = "/executeSystemRoleEdit", produces = {"text/plain;charset=UTF-8"})
	@ResponseBody
	public String executeSystemRoleEdit(SystemRole systemRole) {

		//判断角色名是否重复
		if (systemRoleBiz.existRoleName(systemRole)) {
			return "您输入的角色名为空或已存在，请重新输入";
		}

		systemRoleBiz.addOrUpdate(systemRole);
		return "1";
	}

	/**
	 * 逻辑删除机构用户信息
	 */
	@RequestMapping("/logicRemoveSystemRole")
	@ResponseBody
	public String logicRemoveSystemRole(@RequestParam(value = "roleId", required = false) String roleIdParam,
	                                    @RequestParam(value = "isFakeDelete", required = false) String isFakeDelete) {

		Long roleId = StringConverters.ToLong(roleIdParam);

		List<SystemUser> systemUserList = this.systemUserBiz.findByRoleId(roleId);
		List<SystemAuthority> systemAuthorityList = this.systemAuthorityBiz.getAuthoritiesByRoleId(roleId);

		if (CollectionUtils.isEmpty(systemUserList) && CollectionUtils.isEmpty(systemAuthorityList)) {
			systemRoleBiz.delete(roleId);
			return "1";
		}
		return "2";
	}

	// ******************************************************************************
	// ******************************* 代码自动生成结束 *******************************
	// ******************************************************************************
}