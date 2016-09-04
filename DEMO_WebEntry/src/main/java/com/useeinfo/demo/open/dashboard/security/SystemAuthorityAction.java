package com.useeinfo.demo.open.dashboard.security;

import com.useeinfo.demo.modules.security.biz.SystemAuthorityBiz;
import com.useeinfo.demo.modules.security.biz.SystemRoleBiz;
import com.useeinfo.demo.modules.security.entity.SystemAuthority;
import com.useeinfo.demo.modules.security.entity.SystemResource;
import com.useeinfo.demo.modules.security.entity.SystemRole;
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
@RequestMapping("/web/admin/systemAuthority")
public class SystemAuthorityAction extends BaseAction {

	@Autowired
	private SystemAuthorityBiz systemAuthorityBiz;

	@Autowired
	private SystemRoleBiz systemRoleBiz;

	/**
	 * 跳转到权限修改页面
	 */
	@RequestMapping("/updateAuthPage.action")
	public ModelAndView UpdateAuthPage(@RequestParam(value = "authId", required = false) String authIdParam) {

		Long authId = StringConverters.ToLong(authIdParam);

		Map<String, Object> map = systemAuthorityBiz.getAuthAndResourceById(authId);
		return new ModelAndView("security/system/auth/sys_auth_manage", map);
	}

	/**
	 * 绑定资源到权限
	 */
	@RequestMapping("/addResToAuth.action")
	@ResponseBody
	public Object AddResToAuth(@RequestParam(value = "authId", required = false) String authIdParam,
	                           @RequestParam(value = "resId", required = false) String resIdParam) {

		Long authId = StringConverters.ToLong(authIdParam);
		Long resId = StringConverters.ToLong(resIdParam);

		SystemResource res = systemAuthorityBiz.addResourcesToAuthorities(authId, resId);
		res.setAuthoritySet(null);

		Map<String, Object> map = new HashMap<>();
		map.put("result", 1);
		map.put("res", res);
		return map;
	}

	/**
	 * 移除资源从权限
	 */
	@RequestMapping("/removeResFromAuth.action")
	@ResponseBody
	public Object RemoveResFromAuth(@RequestParam(value = "authId", required = false) String authIdParam,
	                                @RequestParam(value = "resId", required = false) String resIdParam) {

		Long authId = StringConverters.ToLong(authIdParam);
		Long resId = StringConverters.ToLong(resIdParam);

		SystemResource res = systemAuthorityBiz.deleteResourcesFromAuthorities(authId, resId);
		res.setAuthoritySet(null);

		Map<String, Object> map = new HashMap<>();
		map.put("result", 1);
		map.put("res", res);
		return map;
	}

	// ******************************************************************************
	// ******************************* 代码自动生成开始 *******************************
	// ******************************************************************************

	/**
	 * 打开列表页面
	 */
	@RequestMapping("/getSystemAuthorityListPage")
	public ModelAndView getSystemAuthorityListPage() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("security/systemAuthority/systemAuthorityList");

		return modelAndView;
	}

	/**
	 * 分页获取JSON数据
	 */
	@RequestMapping("/getSystemAuthorityListJSON")
	@ResponseBody
	public JSONObject getSystemAuthorityListJSON(@RequestParam(value = "page", required = false) String pageNowParam,
	                                             @RequestParam(value = "rows", required = false) String pageSizeParam) {

		QueryParam queryParam = new QueryParam(pageNowParam, pageSizeParam, 0);
		return systemAuthorityBiz.findJSONList(queryParam);
	}

	/**
	 * 获取详情页面
	 */
	@RequestMapping("/getSystemAuthorityViewPage")
	public ModelAndView getSystemAuthorityViewPage(@RequestParam(value = "authorityId", required = false) String authorityIdParam) {

		Long authorityId = StringConverters.ToLong(authorityIdParam);

		SystemAuthority systemAuthority = null;
		if (authorityId != null) {
			systemAuthority = systemAuthorityBiz.findModel(authorityId);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("security/systemAuthority/systemAuthorityViewPart");
		modelAndView.addObject("systemAuthority", systemAuthority);
		return modelAndView;
	}

	/**
	 * 获取编辑页面
	 */
	@RequestMapping("/getSystemAuthorityEditPage")
	public ModelAndView getSystemAuthorityEditPage(@RequestParam(value = "authorityId", required = false) String authorityIdParam) {

		Long authorityId = StringConverters.ToLong(authorityIdParam);

		SystemAuthority systemAuthority = null;
		if (authorityId != null) {
			systemAuthority = systemAuthorityBiz.findModel(authorityId);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("security/systemAuthority/systemAuthorityEditPart");
		modelAndView.addObject("systemAuthority", systemAuthority);
		return modelAndView;
	}

	/**
	 * 执行提交的新增或修改请求
	 */
	@RequestMapping(value = "/executeSystemAuthorityEdit", produces = {"text/plain;charset=UTF-8"})
	@ResponseBody
	public String executeSystemAuthorityEdit(SystemAuthority systemAuthority) {

		//判断权限名称是否重复
		if (systemAuthorityBiz.existAuthorityName(systemAuthority)) {
			return "您输入的权限名称为空或已存在，请重新输入";
		}

		systemAuthorityBiz.addOrUpdate(systemAuthority);
		return "1";
	}

	/**
	 * 逻辑删除机构用户信息
	 */
	@RequestMapping("/logicRemoveSystemAuthority")
	@ResponseBody
	public String logicRemoveSystemAuthority(@RequestParam(value = "authorityId", required = false) String authorityIdParam,
	                                         @RequestParam(value = "isFakeDelete", required = false) String isFakeDelete) {

		Long authorityId = StringConverters.ToLong(authorityIdParam);

		List<SystemRole> systemRoleList = this.systemRoleBiz.findByAuthorityId(authorityId);
		if (CollectionUtils.isEmpty(systemRoleList)) {
			systemAuthorityBiz.delete(authorityId);
			return "1";
		}

		return "2";

	}

	// ******************************************************************************
	// ******************************* 代码自动生成结束 *******************************
	// ******************************************************************************
}