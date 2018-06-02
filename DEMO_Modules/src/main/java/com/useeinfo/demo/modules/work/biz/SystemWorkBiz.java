package com.useeinfo.demo.modules.work.biz;

import com.useeinfo.demo.modules.security.biz.SystemAuthorityBiz;
import com.useeinfo.demo.modules.security.dao.SystemRoleDao;
import com.useeinfo.demo.modules.security.entity.SystemAuthority;
import com.useeinfo.demo.modules.work.dao.SystemWorkDao;
import com.useeinfo.demo.modules.work.entity.SystemWork;
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
@Service("systemWorkBizWeb")
public class  SystemWorkBiz extends CrudBiz<SystemWorkDao, SystemWork> {

	@Autowired
	private SystemAuthorityBiz systemAuthorityBiz;

	public Boolean existWorkName(SystemWork systemWork) {                /****m没有判断属性是否存在***/
		//若角色名为空
		logger.info("角色名为：" + systemWork.getWorkName());
		if (StringUtils.isBlank(systemWork.getWorkName())) {
			return true;
		}

		//根据角色名查询
		QueryParam queryParam = new QueryParam(1);
		queryParam.getSqlMap().put("workName", systemWork.getWorkName());
		List<SystemWork> systemWorkList = findList(queryParam);

		if (systemWork.getWorkId() == null) {
			//新增操作，判断列表size是否为0
			return CollectionUtils.isNotEmpty(systemWorkList);
		} else {
			//修改操作，情况一：判断list长度是否为1，并且与自身相同，那么就是不存在，所以结果取反
			//修改操作，情况二：判断list长度是0
			return !((systemWorkList.size() == 1 && systemWorkList.get(0).getWorkId().equals(systemWork.
					getWorkId())) || systemWorkList.size() == 0);
		}
	}

	public List<SystemWork> findByAuthorityId(Long authorityId) {

		QueryParam queryParam = new QueryParam(1);
		queryParam.getSqlMap().put("existsAuthorityId", authorityId == null ? "-1" : authorityId.toString());

		return findList(queryParam);
	}

	@Transactional
	public Map<String, Object> getWorkAndAuthById(Long workId) {

		Map<String, Object> map = new HashMap<>();
		SystemWork systemWork = findModel(workId);
//		List ownAuth = systemAuthorityBiz.getAuthoritiesByWorkId(workId);
//		List otherAuth = systemAuthorityBiz.getOtherAuthoritiesByWorkId(workId);
		map.put("work", systemWork);
		map.put("antiskidMode",systemWork);
		map.put("drainageMode",systemWork);
		map.put("antiThermalRadiation",systemWork);
		map.put("bottomWaterCurtain",systemWork);
		map.put("breathingEquipment",systemWork);
		map.put("bucketRatedLoad",systemWork);
		map.put("createTime",systemWork);

//		map.put("ownAuth", ownAuth);
//		map.put("otherAuth", otherAuth);
		return map;
	}

//	@Transactional
//	public SystemAuthority deleteAuthoritiesFromRoles(Long roleId, Long authId) {
//		SystemAuthority systemAuthority = systemAuthorityBiz.findModel(authId);
//		SystemRole systemWork = findModel(roleId);
//		systemRole.removeAuthority(systemAuthority);
//		return systemAuthority;
//	}

//	@Transactional
//	public SystemAuthority addAuthoritiesToRoles(Long roleId, Long authId) {
//		SystemAuthority systemAuthority = systemAuthorityBiz.findModel(authId);
//		SystemRole systemRole = findModel(roleId);
//		systemRole.addAuthority(systemAuthority);
//		return systemAuthority;
//	}
}