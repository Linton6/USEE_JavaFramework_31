package com.useeinfo.demo.modules.security.dao;

import com.useeinfo.demo.modules.security.entity.SystemUser;
import com.useeinfo.framework.extend.dao.CrudDao;
import com.useeinfo.framework.sugar.data.QueryParam;
import com.useeinfo.framework.sugar.data.QueryUtils;
import com.useeinfo.framework.sugar.tools.CommonSugar;
import org.apache.commons.collections.CollectionUtils;
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
@Repository("systemUserDaoWeb")
public class SystemUserDao implements CrudDao<SystemUser> {

	private final static Logger logger = LoggerFactory.getLogger(SystemUserDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	public SystemUser login(String username, String password) {
		List<SystemUser> customers = entityManager.createNamedQuery("SystemUser.login", SystemUser.class)
				.setParameter("userAccount", username)
				.setParameter("userPassword", password)
				.getResultList();
		if (CollectionUtils.isEmpty(customers)) {
			return null;
		} else {
			return customers.get(0);
		}
	}

	public List<SystemUser> findByRoleId(Long roleId) {
		List<SystemUser> systemUserList = this.entityManager.createQuery("select su from SystemUser su where su.systemRole.roleId = :roleId", SystemUser.class).setParameter("roleId", roleId).getResultList();
		if (CollectionUtils.isEmpty(systemUserList)) {
			return null;
		}
		return systemUserList;
	}

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
				case "userAccount":
					conditionHash.put("s.userAccount = ?{paramIndex} ", queryValue);
					continue;
			}

			conditionHash.put("userId < ?{paramIndex}", 0L);
			break;
		}

		return conditionHash;
	}

	@Override
	public Long totalRecord(QueryParam queryParam) {

		Map<String, Object> conditions = getSearchCondition(queryParam);
		TypedQuery<Long> typedQuery = QueryUtils.getTypedQueryByCondition("select count(s) from SystemUser s ", conditions, entityManager, Long.class);
		return typedQuery.getSingleResult();
	}

	@Override
	public List<SystemUser> findList(QueryParam queryParam) {

		String sqlInfo = queryParam.joinJPQL("select s from SystemUser s ", "order by s.userId desc ");

		Map<String, Object> conditions = getSearchCondition(queryParam);
		TypedQuery<SystemUser> typedQuery = QueryUtils.getTypedQueryByCondition(sqlInfo, conditions, entityManager, SystemUser.class);

		return queryParam.findPageList(typedQuery);
	}

	@Override
	public SystemUser findModel(Long userId) {
		return entityManager.find(SystemUser.class, userId);
	}

	@Override
	public Integer add(SystemUser systemUser) {
		entityManager.persist(systemUser);
		logger.info("SystemUserDaoImpl添加systemUser成功！");
		return 1;
	}

	@Override
	public Integer update(SystemUser systemUser) {
		SystemUser existSystemUser = entityManager.find(SystemUser.class, systemUser.getUserId());
		existSystemUser.setUserAccount(systemUser.getUserAccount());
		existSystemUser.setUserPassword(systemUser.getUserPassword());
		existSystemUser.setUserName(systemUser.getUserName());
		existSystemUser.setSystemManager(systemUser.isSystemManager());
		existSystemUser.setSystemRole(systemUser.getSystemRole());
		existSystemUser.setEnabled(systemUser.isEnabled());
		existSystemUser.setValid(systemUser.isValid());
		return 1;
	}

	public void logicRemove(Long userId) {
		SystemUser existSystemUser = entityManager.find(SystemUser.class, userId);
		existSystemUser.setValid(false);
	}

	@Override
	public Integer delete(Long userId) {
		SystemUser existSystemUser = entityManager.find(SystemUser.class, userId);
		entityManager.remove(existSystemUser);
		return 1;
	}

	// ******************************************************************************
	// ******************************* 代码自动生成结束 *******************************
	// ******************************************************************************
}