package com.ysotek.customer.modules.security.dao;

import com.vt1314.base.extend.dao.CrudDao;
import com.vt1314.base.sugar.data.QueryParam;
import com.vt1314.base.sugar.data.QueryUtils;
import com.ysotek.customer.modules.security.entity.SystemRole;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: 居泽平
 */
@Repository("systemRoleDaoWeb")
public class SystemRoleDao implements CrudDao<SystemRole> {

	private final static Logger logger = LoggerFactory.getLogger(SystemRoleDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	// ******************************************************************************
	// ******************************* 代码自动生成开始 *******************************
	// ******************************************************************************

	public Map<String, Object> getSearchCondition(Map<String, String> queryHash) {

		Map<String, Object> conditionHash = new HashMap<>();
		if (queryHash == null) {
			return conditionHash;
		}

		String roleName = queryHash.get("roleName");
		if (!StringUtils.isEmpty(roleName)) {
			conditionHash.put("roleName = ?{paramIndex} ", roleName);
		}

        /*String String = queryHash.get("String");
		if (!StringUtils.isEmpty(String)) {
			conditionHash.put("String like ?{paramIndex} ", "%" + String + "%");
		}
		Integer Integer = TypeConvertUtils.StringToInteger(queryHash.get("Integer"));
		if (Integer != null && Integer > -1) {
			conditionHash.put("Integer = ?{paramIndex} ", Integer);
		}
		Date Date = TypeConvertUtils.StringToDate(queryHash.get("Date"));
		if (Date != null) {
			conditionHash.put("Date >= ?{paramIndex} ", Date);
		}*/

		return conditionHash;
	}

	@Override
	public Long totalRecord(Map<String, String> queryHash) {

		Map<String, Object> conditions = getSearchCondition(queryHash);
		TypedQuery<Long> typedQuery = QueryUtils.getTypedQueryByCondition("select count(s) from SystemRole s ", conditions, entityManager, Long.class);
		return typedQuery.getSingleResult();
	}

	@Override
	public List<SystemRole> findList(QueryParam queryParam) {

		String sqlInfo = queryParam.joinJPQL("select s from SystemRole s ", "order by s.roleId desc ");

		Map<String, Object> conditions = getSearchCondition(queryParam.getSqlMap());
		TypedQuery<SystemRole> typedQuery = QueryUtils.getTypedQueryByCondition(sqlInfo, conditions, entityManager, SystemRole.class);

		return queryParam.findPageList(typedQuery);
	}

	@Override
	public SystemRole findModel(Long roleId) {
		return entityManager.find(SystemRole.class, roleId);
	}

	@Override
	public Integer add(SystemRole systemRole) {
		entityManager.persist(systemRole);
		logger.info("SystemRoleDaoImpl添加systemRole成功！");
		return 1;
	}

	@Override
	public Integer update(SystemRole systemRole) {
		SystemRole existSystemRole = entityManager.find(SystemRole.class, systemRole.getRoleId());
		existSystemRole.setRoleName(systemRole.getRoleName());
		existSystemRole.setRoleDescription(systemRole.getRoleDescription());
		existSystemRole.setSystemManager(systemRole.isSystemManager());
		existSystemRole.setEnabled(systemRole.isEnabled());
		return 1;
	}

	@Override
	public Integer delete(Long roleId) {
		SystemRole existSystemRole = entityManager.find(SystemRole.class, roleId);
		entityManager.remove(existSystemRole);
		return 1;
	}

	// ******************************************************************************
	// ******************************* 代码自动生成结束 *******************************
	// ******************************************************************************
}