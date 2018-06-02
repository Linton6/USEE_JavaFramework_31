package com.useeinfo.demo.modules.work.dao;
import com.useeinfo.demo.modules.work.entity.SystemWork;
import com.useeinfo.framework.extend.dao.CrudDao;
import com.useeinfo.framework.sugar.data.QueryParam;
import com.useeinfo.framework.sugar.data.QueryUtils;
import com.useeinfo.framework.sugar.tools.CommonSugar;
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
@Repository("systemWorkDaoWeb")
public class SystemWorkDao implements CrudDao<SystemWork> {

	private final static Logger logger = LoggerFactory.getLogger(SystemWorkDao.class);

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
				case "workName":
					conditionHash.put("workName = ?{paramIndex} ", queryValue);
					continue;
				case "antiskidMode":
					conditionHash.put("antiskidMode = ?{paramIndex} ", queryValue);
					continue;
				case "drainageMode":
					conditionHash.put("drainageMode = ?{paramIndex} ", queryValue);
					continue;
				case "antiThermalRadiation":
					conditionHash.put("antiThermalRadiation = ?{paramIndex} ", queryValue);
					continue;

				case "bottomWaterCurtain":
					conditionHash.put("bottomWaterCurtain = ?{paramIndex} ", queryValue);
					continue;

				case "breathingEquipment":
					conditionHash.put("breathingEquipment = ?{paramIndex} ", queryValue);
					continue;



			}

			conditionHash.put("workId < ?{paramIndex}", 0L);
			break;
		}

		return conditionHash;
	}

	@Override
	public Long totalRecord(QueryParam queryParam) {

		Map<String, Object> conditions = getSearchCondition(queryParam);
		TypedQuery<Long> typedQuery = QueryUtils.getTypedQueryByCondition("select count(s) from " +
				"SystemWork s ", conditions, entityManager, Long.class);
		return typedQuery.getSingleResult();
	}

	@Override
	public List<SystemWork> findList(QueryParam queryParam) {

		String sqlInfo = queryParam.joinJPQL("select s from SystemWork s ",
				"order by s.workId desc ");										/*******存疑*****/

		Map<String, Object> conditions = getSearchCondition(queryParam);
		TypedQuery<SystemWork> typedQuery = QueryUtils.getTypedQueryByCondition(sqlInfo, conditions,
				entityManager, SystemWork.class);

		return queryParam.findPageList(typedQuery);
	}

	@Override
	public SystemWork findModel(Long workId) {												/*******存疑*****/
		return entityManager.find(SystemWork.class, workId);
	}

	@Override
	public Integer add(SystemWork systemWork) {
		entityManager.persist(systemWork);
		logger.info("SystemWorkDaoImpl添加systemWork成功！");
		return 1;
	}

	@Override
	public Integer update(SystemWork systemWork) {
		SystemWork existSystemWork = entityManager.find(SystemWork.class, systemWork.getWorkId());
		existSystemWork.setWorkName(systemWork.getWorkName());
		existSystemWork.setAntiskidMode(systemWork.getAntiskidMode());
		existSystemWork.setDrainageMode(systemWork.getDrainageMode());
		existSystemWork.setAntiThermalRadiation(systemWork.getAntiThermalRadiation());
		existSystemWork.setBottomWaterCurtain(systemWork.getBottomWaterCurtain());
		existSystemWork.setBreathingEquipment(systemWork.getBreathingEquipment());
		existSystemWork.setBucketRatedLoad(systemWork.getBucketRatedLoad());
		existSystemWork.setCreateTime(systemWork.getCreateTime());

//		existSystemWork.setWorkDescription(systemWork.getWorkDescription());
//		existSystemWork.setSystemManager(systemRole.isSystemManager());
//		existSystemWork.setEnabled(systemRole.isEnabled());
		return 1;
	}

	@Override
	public Integer delete(Long workId) {
		SystemWork existSystemWork = entityManager.find(SystemWork.class, workId);
		entityManager.remove(existSystemWork);
		return 1;
	}

	// ******************************************************************************
	// ******************************* 代码自动生成结束 *******************************
	// ******************************************************************************
}