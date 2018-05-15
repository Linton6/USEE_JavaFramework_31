package com.useeinfo.demo.modules.people.dao;

import com.useeinfo.demo.modules.people.entity.SystemPeople;
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
@Repository("systemPeopleDaoWeb")
public class SystemPeopleDao implements CrudDao<SystemPeople> {

	private final static Logger logger = LoggerFactory.getLogger(SystemPeopleDao.class);

	@PersistenceContext
	private EntityManager entityManager;

//	public SystemPeople login(String peoplename, String password) {
//		List<SystemPeople> customers = entityManager.createNamedQuery("SystemPeople.login", SystemPeople.class)
//				.setParameter("peopleAccount", peoplename)
//				.setParameter("peoplePassword", password)
//				.getResultList();
//		if (CollectionUtils.isEmpty(customers)) {
//			return null;
//		} else {
//			return customers.get(0);
//		}
//	}

	public List<SystemPeople> findByRoleId(Long roleId) {
		List<SystemPeople> systemPeopleList = this.entityManager.createQuery("select su from SystemPeople su where su.systemRole.roleId = :roleId", SystemPeople.class).setParameter("roleId", roleId).getResultList();
		if (CollectionUtils.isEmpty(systemPeopleList)) {
			return null;
		}
		return systemPeopleList;
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
				case "peopleAccount":
					conditionHash.put("s.peopleAccount = ?{paramIndex} ", queryValue);
					continue;
			}

			conditionHash.put("peopleId < ?{paramIndex}", 0L);
			break;
		}

		return conditionHash;
	}

	@Override
	public Long totalRecord(QueryParam queryParam) {

		Map<String, Object> conditions = getSearchCondition(queryParam);
		TypedQuery<Long> typedQuery = QueryUtils.getTypedQueryByCondition("select count(s) from SystemPeople s ", conditions, entityManager, Long.class);
		return typedQuery.getSingleResult();
	}

	@Override
	public List<SystemPeople> findList(QueryParam queryParam) {

		String sqlInfo = queryParam.joinJPQL("select s from SystemPeople s ", "order by s.peopleId desc ");

		Map<String, Object> conditions = getSearchCondition(queryParam);
		TypedQuery<SystemPeople> typedQuery = QueryUtils.getTypedQueryByCondition(sqlInfo, conditions, entityManager, SystemPeople.class);

		return queryParam.findPageList(typedQuery);
	}

	@Override
	public SystemPeople findModel(Long peopleId) {
		return entityManager.find(SystemPeople.class, peopleId);
	}

	@Override
	public Integer add(SystemPeople systemPeople) {
		entityManager.persist(systemPeople);
		logger.info("SystemPeopleDaoImpl添加systemPeople成功！");
		return 1;
	}

	@Override
	public Integer update(SystemPeople systemPeople) {
		SystemPeople existSystemPeople = entityManager.find(SystemPeople.class, systemPeople.getPeopleId());
//		existSystemPeople.setUserAccount(systemUser.getUserAccount());
//		existSystemPeople.setUserPassword(systemUser.getUserPassword());
		existSystemPeople.setPeopleName(systemPeople.getPeopleName());
//		existSystemPeople.setSystemManager(systemUser.isSystemManager());
//		existSystemPeople.setSystemRole(systemUser.getSystemRole());
//		existSystemPeople.setEnabled(systemPeople.isEnabled());
//		existSystemPeople.setValid(systemPeople.isValid());
		return 1;
	}

	public void logicRemove(Long peopleId) {
		SystemPeople existSystemPeople = entityManager.find(SystemPeople.class, peopleId);
//		existSystemPeople.setValid(false);
	}

	@Override
	public Integer delete(Long peopleId) {
		SystemPeople existSystemPeople = entityManager.find(SystemPeople.class, peopleId);
		entityManager.remove(existSystemPeople);
		return 1;
	}

	// ******************************************************************************
	// ******************************* 代码自动生成结束 *******************************
	// ******************************************************************************
}