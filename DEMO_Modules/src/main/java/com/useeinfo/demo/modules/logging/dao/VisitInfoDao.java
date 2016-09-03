package com.useeinfo.demo.modules.logging.dao;

import com.useeinfo.demo.modules.logging.entity.VisitInfo;
import com.useeinfo.framework.extend.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Author: Mingjie Pan  Date: 13-7-6, 上午11:48
 */
@Repository("visitDao")
public class VisitInfoDao implements BaseDao {

	private static final Logger logger = LoggerFactory.getLogger(VisitInfoDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void logVisitInfo(VisitInfo visitInfo) {
		try {
			entityManager.persist(visitInfo);
			entityManager.flush();
			logger.debug("保存用户访问痕迹成功。");
		} catch (Exception e) {
			logger.error("保存访问信息出错。", e);
		}
	}
}
