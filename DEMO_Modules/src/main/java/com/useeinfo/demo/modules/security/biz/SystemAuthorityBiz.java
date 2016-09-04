package com.useeinfo.demo.modules.security.biz;

import com.useeinfo.demo.modules.security.dao.SystemAuthorityDao;
import com.useeinfo.demo.modules.security.entity.SystemAuthority;
import com.useeinfo.demo.modules.security.entity.SystemResource;
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
@Service("systemAuthorityBizWeb")
public class SystemAuthorityBiz extends CrudBiz<SystemAuthorityDao, SystemAuthority> {

	@Autowired
	private SystemResourceBiz systemResourceBiz;

	@Transactional
	public void addSystemAuthorityFirst(String authorityName) {
		logger.info("authorityName的值为：" + authorityName);

		QueryParam queryParam = new QueryParam(1);
		queryParam.getSqlMap().put("authorityName", authorityName);
		List<SystemAuthority> systemAuthorityList = findList(queryParam);
		//若list为空则做新增操作
		if (CollectionUtils.isEmpty(systemAuthorityList)) {
			//执行新增操作
			SystemAuthority systemAuthority = new SystemAuthority(authorityName, authorityName, null, null, true, true);
			addOrUpdate(systemAuthority);
		}
	}

	public Boolean existAuthorityName(SystemAuthority systemAuthority) {
		//若权限名称为空
		logger.info("权限名称为：" + systemAuthority.getAuthorityName());
		if (StringUtils.isBlank(systemAuthority.getAuthorityName())) {
			return true;
		}

		//根据权限名称查询
		QueryParam queryParam = new QueryParam(1);
		queryParam.getSqlMap().put("authorityName", systemAuthority.getAuthorityName());
		List<SystemAuthority> systemAuthorityList = findList(queryParam);

		if (systemAuthority.getAuthorityId() == null) {
			//新增操作，判断列表size是否为0
			return CollectionUtils.isNotEmpty(systemAuthorityList);
		} else {
			//修改操作，情况一：判断list长度是否为1，并且与自身相同，那么就是不存在，所以结果取反
			//修改操作，情况二：判断list长度是0
			return !((systemAuthorityList.size() == 1 && systemAuthorityList.get(0).getAuthorityId().equals(systemAuthority.getAuthorityId())) || systemAuthorityList.size() == 0);
		}
	}

	public List<SystemAuthority> getAuthoritiesByRoleId(Long roleId) {

		QueryParam queryParam = new QueryParam(1);
		queryParam.getSqlMap().put("existsRoleId", roleId == null ? "-1" : roleId.toString());

		return findList(queryParam);
	}

	public List<SystemAuthority> getOtherAuthoritiesByRoleId(Long roleId) {

		QueryParam queryParam = new QueryParam(1);
		queryParam.getSqlMap().put("notExistsRoleId", roleId == null ? "-1" : roleId.toString());

		return findList(queryParam);
	}

	public Map<String, Object> getAuthAndResourceById(Long authId) {
		Map<String, Object> map = new HashMap<>();
		SystemAuthority auth = findModel(authId);
		List<SystemResource> ownResource = systemResourceBiz.getResourceByAuthId(authId);
		List<SystemResource> otherResource = systemResourceBiz.getOtherResourceByAuthId(authId);
		map.put("auth", auth);
		map.put("ownResource", ownResource);
		map.put("otherResource", otherResource);
		return map;
	}

	@Transactional
	public SystemResource addResourcesToAuthorities(Long authId, Long resId) {
		SystemResource systemResource = systemResourceBiz.findModel(resId);
		SystemAuthority systemAuthority = findModel(authId);
		systemAuthority.addResource(systemResource);
		return systemResource;
	}

	@Transactional
	public SystemResource deleteResourcesFromAuthorities(Long authId, Long resId) {
		SystemResource systemResource = systemResourceBiz.findModel(resId);
		SystemAuthority systemAuthority = findModel(authId);
		systemAuthority.removeResource(systemResource);
		return systemResource;
	}
}