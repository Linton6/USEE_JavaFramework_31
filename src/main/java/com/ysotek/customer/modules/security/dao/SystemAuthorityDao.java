package com.ysotek.customer.modules.security.dao;

import com.vt1314.framework.extend.dao.CrudDao;
import com.vt1314.framework.sugar.data.QueryParam;
import com.vt1314.framework.sugar.data.QueryUtils;
import com.vt1314.framework.sugar.tools.StringConverters;
import com.ysotek.customer.modules.security.entity.SystemAuthority;
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
@Repository("systemAuthorityDaoWeb")
public class SystemAuthorityDao implements CrudDao<SystemAuthority> {

	private final static Logger logger = LoggerFactory.getLogger(SystemAuthorityDao.class);

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

		String authorityName = queryHash.get("authorityName");
		if (!StringUtils.isEmpty(authorityName)) {
			conditionHash.put("authorityName = ?{paramIndex} ", authorityName);
		}

		Long existsRoleId = StringConverters.ToLong(queryHash.get("existsRoleId"));
		if (existsRoleId != null && existsRoleId > -1) {
			conditionHash.put("exists (select role from s.roleSet role where role.roleId = ?{paramIndex}) ", existsRoleId);
		}

		Long notExistsRoleId = StringConverters.ToLong(queryHash.get("notExistsRoleId"));
		if (notExistsRoleId != null && notExistsRoleId > -1) {
			conditionHash.put("not exists (select role from s.roleSet role where role.roleId = ?{paramIndex}) ", notExistsRoleId);
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
		TypedQuery<Long> typedQuery = QueryUtils.getTypedQueryByCondition("select count(s) from SystemAuthority s ", conditions, entityManager, Long.class);
		return typedQuery.getSingleResult();
	}

	@Override
	public List<SystemAuthority> findList(QueryParam queryParam) {

		String sqlInfo = queryParam.joinJPQL("select s from SystemAuthority s ", "order by s.authorityId desc ");

		Map<String, Object> conditions = getSearchCondition(queryParam.getSqlMap());
		TypedQuery<SystemAuthority> typedQuery = QueryUtils.getTypedQueryByCondition(sqlInfo, conditions, entityManager, SystemAuthority.class);

		return queryParam.findPageList(typedQuery);
	}

	@Override
	public SystemAuthority findModel(Long authorityId) {
		return entityManager.find(SystemAuthority.class, authorityId);
	}

	@Override
	public Integer add(SystemAuthority systemAuthority) {
		entityManager.persist(systemAuthority);
		logger.info("SystemAuthorityDaoImpl添加systemAuthority成功！");
		return 1;
	}

	@Override
	public Integer update(SystemAuthority systemAuthority) {
		SystemAuthority existSystemAuthority = entityManager.find(SystemAuthority.class, systemAuthority.getAuthorityId());
		existSystemAuthority.setAuthorityName(systemAuthority.getAuthorityName());
		existSystemAuthority.setAuthorityDescription(systemAuthority.getAuthorityDescription());
		existSystemAuthority.setSystemManager(systemAuthority.isSystemManager());
		existSystemAuthority.setEnabled(systemAuthority.isEnabled());
		return 1;
	}

	@Override
	public Integer delete(Long authorityId) {
		SystemAuthority existSystemAuthority = entityManager.find(SystemAuthority.class, authorityId);
		entityManager.remove(existSystemAuthority);
		return 1;
	}

	// ******************************************************************************
	// ******************************* 代码自动生成结束 *******************************
	// ******************************************************************************
}