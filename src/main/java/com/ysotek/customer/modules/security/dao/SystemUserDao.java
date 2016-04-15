package com.ysotek.customer.modules.security.dao;

import com.vt1314.base.extend.dao.CrudDao;
import com.vt1314.base.sugar.data.QueryParam;
import com.vt1314.base.sugar.data.QueryUtils;
import com.ysotek.customer.modules.security.entity.SystemUser;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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

	public Map<String, Object> getSearchCondition(Map<String, String> queryHash) {

		Map<String, Object> conditionHash = new HashMap<>();
		if (queryHash == null) {
			return conditionHash;
		}

		String userAccount = queryHash.get("userAccount");
		if (!StringUtils.isEmpty(userAccount)) {
			conditionHash.put("s.userAccount = ?{paramIndex} ", userAccount);
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
		TypedQuery<Long> typedQuery = QueryUtils.getTypedQueryByCondition("select count(s) from SystemUser s ", conditions, entityManager, Long.class);
		return typedQuery.getSingleResult();
	}

	@Override
	public List<SystemUser> findList(QueryParam queryParam) {

		String sqlInfo = queryParam.joinJPQL("select s from SystemUser s ", "order by s.userId desc ");

		Map<String, Object> conditions = getSearchCondition(queryParam.getSqlMap());
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