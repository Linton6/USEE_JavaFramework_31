/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.useeinfo.framework.extend.dao;

import com.useeinfo.framework.sugar.data.QueryParam;

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

	public Map<String, Object> getSearchCondition(QueryParam queryParam);

	/**
	 * 获取总记录数
	 */
	public Long totalRecord(QueryParam queryParam);

	/**
	 * 分页列表
	 */
	public List<T> findList(QueryParam queryParam);

	/**
	 * id获取记录
	 */
	public T findModel(Long id);

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
	public Integer delete(Long id);

	// ******************************************************************************
	// ********************************** CRUD END **********************************
	// ******************************************************************************
}