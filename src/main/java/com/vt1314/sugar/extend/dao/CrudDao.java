/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vt1314.sugar.extend.dao;

import java.util.List;
import java.util.Map;

/**
 * DAO支持类实现
 *
 * @param <T>
 * @author 居泽平
 * @version 2016-03-10
 */
public interface CrudDao<T> extends BaseDao {

	// ******************************************************************************
	// ********************************* CRUD START *********************************
	// ******************************************************************************

	/**
	 * 获取总记录数
	 */
	public Long totalRecord(Map<String, String> queryHash);

	/**
	 * 分页列表
	 */
	public List<T> findList(Integer pageNow, Integer pageSize, String sqlOrder, Map<String, String> queryHash);

	/**
	 * id获取记录
	 */
	public T findModel(Long adminUserId);

	/**
	 * 增加记录
	 */
	public Integer add(T model);

	/**
	 * 修改记录
	 */
	public Integer update(T model);

	/**
	 * 删除记录
	 */
	public Integer delete(Long adminUserId);

	// ******************************************************************************
	// ********************************** CRUD END **********************************
	// ******************************************************************************
}