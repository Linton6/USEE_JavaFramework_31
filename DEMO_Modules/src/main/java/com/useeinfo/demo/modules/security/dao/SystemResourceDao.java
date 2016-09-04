package com.useeinfo.demo.modules.security.dao;

import com.useeinfo.demo.modules.security.entity.SystemResource;
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
@Repository("systemResourceDaoWeb")
public class SystemResourceDao implements CrudDao<SystemResource> {

	private final static Logger logger = LoggerFactory.getLogger(SystemResourceDao.class);

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
				case "existsAuthId":
					Long existsAuthId = StringConverters.ToLong(queryValue);
					if (existsAuthId != null && existsAuthId > -1) {
						conditionHash.put("exists (select authority from s.authoritySet authority where authority.authorityId = ?{paramIndex}) ", existsAuthId);
						continue;
					}

				case "notExistsAuthId":
					Long notExistsAuthId = StringConverters.ToLong(queryValue);
					if (notExistsAuthId != null && notExistsAuthId > -1) {
						conditionHash.put("not exists (select authority from s.authoritySet authority where authority.authorityId = ?{paramIndex}) ", notExistsAuthId);
						continue;
					}
			}

			conditionHash.put("resourceId < ?{paramIndex}", 0L);
			break;
		}

		return conditionHash;
	}

	@Override
	public Long totalRecord(QueryParam queryParam) {

		Map<String, Object> conditions = getSearchCondition(queryParam);
		TypedQuery<Long> typedQuery = QueryUtils.getTypedQueryByCondition("select count(s) from SystemResource s ", conditions, entityManager, Long.class);
		return typedQuery.getSingleResult();
	}

	@Override
	public List<SystemResource> findList(QueryParam queryParam) {

		String sqlInfo = queryParam.joinJPQL("select s from SystemResource s ", "order by s.resourceId desc ");

		Map<String, Object> conditions = getSearchCondition(queryParam);
		TypedQuery<SystemResource> typedQuery = QueryUtils.getTypedQueryByCondition(sqlInfo, conditions, entityManager, SystemResource.class);

		return queryParam.findPageList(typedQuery);
	}

	@Override
	public SystemResource findModel(Long resourceId) {
		return entityManager.find(SystemResource.class, resourceId);
	}

	@Override
	public Integer add(SystemResource systemResource) {
		entityManager.persist(systemResource);
		logger.info("SystemResourceDaoImpl添加systemResource成功！");
		return 1;
	}

	@Override
	public Integer update(SystemResource systemResource) {
		SystemResource existSystemResource = entityManager.find(SystemResource.class, systemResource.getResourceId());
		existSystemResource.setResourceName(systemResource.getResourceName());
		existSystemResource.setResourceDescription(systemResource.getResourceDescription());
		existSystemResource.setResourceType(systemResource.getResourceType());
		existSystemResource.setResourceString(systemResource.getResourceString());
		existSystemResource.setSystemManager(systemResource.isSystemManager());
		existSystemResource.setEnabled(systemResource.isEnabled());
		return 1;
	}

	@Override
	public Integer delete(Long resourceId) {
		SystemResource existSystemResource = entityManager.find(SystemResource.class, resourceId);
		entityManager.remove(existSystemResource);
		return 1;
	}

	// ******************************************************************************
	// ******************************* 代码自动生成结束 *******************************
	// ******************************************************************************
}