package com.ysotek.example.sys.dao;

import com.ysotek.example.sys.entity.AdminUser;
import com.vt1314.base.extend.dao.CrudDao;
import com.vt1314.base.sugar.data.QueryParam;
import com.vt1314.base.sugar.data.QueryUtils;
import com.vt1314.base.sugar.tools.CommonSugar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: val.jzp
 */
@Repository("adminUserDao")
public class AdminUserDao implements CrudDao<AdminUser> {

	private final static Logger logger = LoggerFactory.getLogger(AdminUserDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	// ******************************************************************************
	// ********************************* CRUD START *********************************
	// ******************************************************************************

	public Map<String, Object> getSearchCondition(Map<String, String> queryHash) {

		Map<String, Object> conditionHash = new HashMap<>();
		if (queryHash == null) {
			return conditionHash;
		}

		String loginName = queryHash.get("loginName");
		if (!StringUtils.isEmpty(loginName)) {
			conditionHash.put("loginName = ?{paramIndex} ", loginName);
		}

		String loginPassword = queryHash.get("loginPassword");
		if (!StringUtils.isEmpty(loginPassword)) {
			conditionHash.put("loginPassword = ?{paramIndex} ", loginPassword);
		}

        /*String String = queryHash.get("String");
		if (!StringUtils.isEmpty(String)) {
			conditionHash.put("String like ?{paramIndex} ", "%" + String + "%");
		}
		Integer Integer = StringConverters.ToInteger(queryHash.get("Integer"));
		if (Integer != null && Integer > -1) {
			conditionHash.put("Integer = ?{paramIndex} ", Integer);
		}
		Date Date = StringConverters.ToDate(queryHash.get("Date"));
		if (Date != null) {
			conditionHash.put("Date >= ?{paramIndex} ", Date);
		}*/

		return conditionHash;
	}

	@Override
	public Long totalRecord(Map<String, String> queryHash) {

		Map<String, Object> conditions = getSearchCondition(queryHash);
		TypedQuery<Long> typedQuery = QueryUtils.getTypedQueryByCondition("select count(a) from AdminUser a ", conditions, entityManager, Long.class);
		return typedQuery.getSingleResult();
	}

	@Override
	public List<AdminUser> findList(QueryParam queryParam) {

		String sqlOrder = CommonSugar.getStringDefault(queryParam.getSqlOrder(), "order by a.id desc ");
		String sqlInfo = "select a from AdminUser a " + sqlOrder;

		Map<String, Object> conditions = getSearchCondition(queryParam.getSqlMap());
		TypedQuery<AdminUser> typedQuery = QueryUtils.getTypedQueryByCondition(sqlInfo, conditions, entityManager, AdminUser.class);

		//设定分页信息后返回数据
		return queryParam.findPageList(typedQuery);
	}

	@Override
	public AdminUser findModel(Long id) {
		return entityManager.find(AdminUser.class, id);
	}

	@Override
	public Integer add(AdminUser adminUser) {
		adminUser.setCreateDate(new Date());
		entityManager.persist(adminUser);
		logger.info("AdminUserDaoImpl添加adminUser成功！");
		return 1;
	}

	@Override
	public Integer update(AdminUser adminUser) {
		AdminUser existAdminUser = entityManager.find(AdminUser.class, adminUser.getId());
		existAdminUser.setLoginName(adminUser.getLoginName());
		existAdminUser.setLoginPassword(adminUser.getLoginPassword());
		existAdminUser.setRealName(adminUser.getRealName());
		existAdminUser.setEmail(adminUser.getEmail());
		existAdminUser.setUpdateDate(new Date());
		return 1;
	}

	@Override
	public Integer delete(Long id) {
		AdminUser existAdminUser = entityManager.find(AdminUser.class, id);
		entityManager.remove(existAdminUser);
		return 1;
	}

	// ******************************************************************************
	// ********************************** CRUD END **********************************
	// ******************************************************************************
}