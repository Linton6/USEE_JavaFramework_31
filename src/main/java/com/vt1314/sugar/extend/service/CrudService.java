/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vt1314.sugar.extend.service;

import com.vt1314.sugar.extend.entity.DataEntity;
import com.vt1314.sugar.extend.persistence.CrudDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Service基类
 *
 * @author 居泽平
 * @version 2016-03-10
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;

	// ******************************************************************************
	// ********************************* CRUD START *********************************
	// ******************************************************************************

	public Long totalRecord() {
		return this.totalRecord(null);
	}

	public Long totalRecord(Map<String, String> queryHash) {
		return dao.totalRecord(queryHash);
	}

	public List<T> findList() {
		return this.findList(0, 0, null);
	}

	public List<T> findList(Map<String, String> queryHash) {
		return this.findList(0, 0, queryHash);
	}

	public List<T> findList(Integer pageNow, Integer pageSize) {
		return this.findList(pageNow, pageSize, null);
	}

	public List<T> findList(Integer pageNow, Integer pageSize, Map<String, String> queryHash) {
		return this.findList(pageNow, pageSize, "", queryHash);
	}

	public List<T> findList(Integer pageNow, Integer pageSize, String sqlOrder, Map<String, String> queryHash) {
		return dao.findList(pageNow, pageSize, sqlOrder, queryHash);
	}

	public T findModel(Long adminUserId) {
		return dao.findModel(adminUserId);
	}

	@Transactional
	public void addOrUpdate(T model) {
		/*if (model.getAdminUserId() != null && model.getAdminUserId() > 0) {
			logger.info("当前需修改的AdminUser对象admin_user_id为[" + model.getAdminUserId() + "]");
			dao.update(model);
		} else {
			logger.info("AdminUserBizImpl添加adminUser！");
			dao.add(model);
		}*/
	}

	@Transactional
	public void delete(Long adminUserId) {
		dao.delete(adminUserId);
	}

	// ******************************************************************************
	// ********************************** CRUD END **********************************
	// ******************************************************************************
}
