package com.useeinfo.demo.modules.security.biz;

import com.useeinfo.demo.modules.security.dao.SystemRoleDao;
import com.useeinfo.demo.modules.security.entity.SystemAuthority;
import com.useeinfo.demo.modules.security.entity.SystemRole;
import com.useeinfo.framework.extend.biz.CrudBiz;
import com.useeinfo.framework.sugar.data.QueryParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: 居泽平
 */
@Service("systemRoleBizWeb")
public class SystemRoleBiz extends CrudBiz<SystemRoleDao, SystemRole> {

	@Autowired
	private SystemAuthorityBiz systemAuthorityBiz;

	public Boolean existRoleName(SystemRole systemRole) {
		//若角色名为空
		logger.info("角色名为：" + systemRole.getRoleName());
		if (StringUtils.isBlank(systemRole.getRoleName())) {
			return true;
		}

		//根据角色名查询
		QueryParam queryParam = new QueryParam(1);
		queryParam.getSqlMap().put("roleName", systemRole.getRoleName());
		List<SystemRole> systemRoleList = findList(queryParam);

		if (systemRole.getRoleId() == null) {
			//新增操作，判断列表size是否为0
			return CollectionUtils.isNotEmpty(systemRoleList);
		} else {
			//修改操作，情况一：判断list长度是否为1，并且与自身相同，那么就是不存在，所以结果取反
			//修改操作，情况二：判断list长度是0
			return !((systemRoleList.size() == 1 && systemRoleList.get(0).getRoleId().equals(systemRole.getRoleId())) || systemRoleList.size() == 0);
		}
	}

	public List<SystemRole> findByAuthorityId(Long authorityId) {

		QueryParam queryParam = new QueryParam(1);
		queryParam.getSqlMap().put("existsAuthorityId", authorityId == null ? "-1" : authorityId.toString());

		return findList(queryParam);
	}

	@Transactional
	public Map<String, Object> getRoleAndAuthById(Long roleId) {

		Map<String, Object> map = new HashMap<>();
		SystemRole systemRole = findModel(roleId);
		List ownAuth = systemAuthorityBiz.getAuthoritiesByRoleId(roleId);
		List otherAuth = systemAuthorityBiz.getOtherAuthoritiesByRoleId(roleId);
		map.put("role", systemRole);
		map.put("ownAuth", ownAuth);
		map.put("otherAuth", otherAuth);
		return map;
	}

	@Transactional
	public SystemAuthority deleteAuthoritiesFromRoles(Long roleId, Long authId) {
		SystemAuthority systemAuthority = systemAuthorityBiz.findModel(authId);
		SystemRole systemRole = findModel(roleId);
		systemRole.removeAuthority(systemAuthority);
		return systemAuthority;
	}

	@Transactional
	public SystemAuthority addAuthoritiesToRoles(Long roleId, Long authId) {
		SystemAuthority systemAuthority = systemAuthorityBiz.findModel(authId);
		SystemRole systemRole = findModel(roleId);
		systemRole.addAuthority(systemAuthority);
		return systemAuthority;
	}
}