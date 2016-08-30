package com.ysotek.customer.modules.security.dao;

import com.vt1314.framework.extend.dao.CrudDao;
import com.vt1314.framework.sugar.data.QueryParam;
import com.vt1314.framework.sugar.data.QueryUtils;
import com.ysotek.customer.modules.security.entity.SystemRole;
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
		if (queryHash == null || queryHash.size() == 0) {
			return conditionHash;
		}

		for (String queryKey : queryHash.keySet()) {
			String queryValue = queryHash.get(queryKey);

			switch (queryKey) {
				case "roleName":
					conditionHash.put("roleName = ?{paramIndex} ", queryValue);
					continue;
			}

			conditionHash.put("resourceId < ?{paramIndex}", 0L);
			break;
		}

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