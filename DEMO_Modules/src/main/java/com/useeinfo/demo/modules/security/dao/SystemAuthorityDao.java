package com.useeinfo.demo.modules.security.dao;

import com.useeinfo.demo.modules.security.entity.SystemAuthority;
import com.useeinfo.framework.extend.dao.CrudDao;
import com.useeinfo.framework.sugar.data.QueryParam;
import com.useeinfo.framework.sugar.data.QueryUtils;
import com.useeinfo.framework.sugar.tools.CommonSugar;
import com.useeinfo.framework.sugar.tools.StringConverters;
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

	public Map<String, Object> getSearchCondition(QueryParam queryParam) {

		queryParam = CommonSugar.getTypedDefault(queryParam, new QueryParam(0));
		Map<String, String> queryHash = queryParam.getSqlMap();

		Map<String, Object> conditionHash = new HashMap<>();
		if (queryHash == null || queryHash.size() == 0) {
			return conditionHash;
		}

		for (String queryKey : queryHash.keySet()) {
			String queryValue = queryHash.get(queryKey);

			switch (queryKey) {
				case "authorityName":
					conditionHash.put("authorityName = ?{paramIndex} ", queryValue);
					continue;

				case "existsRoleId":
					Long existsRoleId = StringConverters.ToLong(queryValue);
					if (existsRoleId != null) {
						conditionHash.put("exists (select role from s.roleSet role where role.roleId = ?{paramIndex}) ", existsRoleId);
						continue;
					}

				case "notExistsRoleId":
					Long notExistsRoleId = StringConverters.ToLong(queryValue);
					if (notExistsRoleId != null) {
						conditionHash.put("not exists (select role from s.roleSet role where role.roleId = ?{paramIndex}) ", notExistsRoleId);
						continue;
					}
			}

			conditionHash.put("authorityId < ?{paramIndex}", 0L);
			break;
		}

		return conditionHash;
	}

	@Override
	public Long totalRecord(QueryParam queryParam) {

		Map<String, Object> conditions = getSearchCondition(queryParam);
		TypedQuery<Long> typedQuery = QueryUtils.getTypedQueryByCondition("select count(s) from SystemAuthority s ", conditions, entityManager, Long.class);
		return typedQuery.getSingleResult();
	}

	@Override
	public List<SystemAuthority> findList(QueryParam queryParam) {

		String sqlInfo = queryParam.joinJPQL("select s from SystemAuthority s ", "order by s.authorityId desc ");

		Map<String, Object> conditions = getSearchCondition(queryParam);
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