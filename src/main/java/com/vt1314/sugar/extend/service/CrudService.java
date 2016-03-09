/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vt1314.sugar.extend.service;

import com.vt1314.sugar.data.QueryData;
import com.vt1314.sugar.extend.dao.CrudDao;
import com.vt1314.sugar.extend.entity.DataEntity;
import net.sf.json.JSONObject;
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
@SuppressWarnings("unused")
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;

	// ******************************************************************************
	// ********************************* CRUD START *********************************
	// ******************************************************************************

	/**
	 * 获得列表JSON
	 *
	 * @param jsonConverterName 对应的JSON转换方法名称
	 * @return JSONObject.
	 */
	public JSONObject findJSONList(String jsonConverterName) {
		return findNumList(0, 0, null).toJSONObject(jsonConverterName);
	}

	/**
	 * 获得列表JSON
	 *
	 * @param queryHash         查询参数
	 * @param jsonConverterName JSON转换的方法名
	 * @return JSONObject.
	 */
	public JSONObject findJSONList(Map<String, String> queryHash, String jsonConverterName) {
		return findNumList(0, 0, queryHash).toJSONObject(jsonConverterName);
	}

	/**
	 * 获得列表JSON
	 *
	 * @param pageNow           当前页
	 * @param pageSize          每页数据量
	 * @param jsonConverterName JSON转换的方法名
	 * @return JSONObject.
	 */
	public JSONObject findJSONList(Integer pageNow, Integer pageSize, String jsonConverterName) {
		return findNumList(pageNow, pageSize, null).toJSONObject(jsonConverterName);
	}

	/**
	 * 获得列表JSON
	 *
	 * @param pageNow           当前页
	 * @param pageSize          每页数据量
	 * @param queryHash         查询参数
	 * @param jsonConverterName JSON转换的方法名
	 * @return JSONObject.
	 */
	public JSONObject findJSONList(Integer pageNow, Integer pageSize, Map<String, String> queryHash, String jsonConverterName) {
		return findNumList(pageNow, pageSize, queryHash).toJSONObject(jsonConverterName);
	}

	/**
	 * 分页获得数据列表并且带有总记录数
	 *
	 * @return QueryData.
	 */
	public QueryData<T> findNumList() {
		return this.findNumList(0, 0, null);
	}

	/**
	 * 分页获得数据列表并且带有总记录数
	 *
	 * @param queryHash 查询参数
	 * @return QueryData.
	 */
	public QueryData<T> findNumList(Map<String, String> queryHash) {
		return this.findNumList(0, 0, queryHash);
	}

	/**
	 * 分页获得数据列表并且带有总记录数
	 *
	 * @param pageNow  当前页
	 * @param pageSize 每页数据量
	 * @return QueryData.
	 */
	public QueryData<T> findNumList(Integer pageNow, Integer pageSize) {
		return this.findNumList(pageNow, pageSize, null);
	}

	/**
	 * 分页获得数据列表并且带有总记录数
	 *
	 * @param pageNow   当前页
	 * @param pageSize  每页数据量
	 * @param queryHash 查询参数
	 * @return QueryData.
	 */
	public QueryData<T> findNumList(Integer pageNow, Integer pageSize, Map<String, String> queryHash) {

		List<T> basicChannelList = findList(pageNow, pageSize, queryHash);
		Long totalNum = totalRecord(queryHash);

		return new QueryData<>(basicChannelList, totalNum);
	}

	/**
	 * 获得总记录数
	 *
	 * @return 记录数.
	 */
	public Long totalRecord() {
		return this.totalRecord(null);
	}

	/**
	 * 获得总记录数
	 *
	 * @param queryHash 查询参数
	 * @return 记录数.
	 */
	public Long totalRecord(Map<String, String> queryHash) {
		return dao.totalRecord(queryHash);
	}

	/**
	 * 获得数据列表
	 *
	 * @return 数据列表.
	 */
	public List<T> findList() {
		return this.findList(0, 0, null);
	}

	/**
	 * 获得数据列表
	 *
	 * @param queryHash 查询参数
	 * @return 数据列表.
	 */
	public List<T> findList(Map<String, String> queryHash) {
		return this.findList(0, 0, queryHash);
	}

	/**
	 * 获得数据列表
	 *
	 * @param pageNow  当前页
	 * @param pageSize 每页数据量
	 * @return 数据列表.
	 */
	public List<T> findList(Integer pageNow, Integer pageSize) {
		return this.findList(pageNow, pageSize, null);
	}

	/**
	 * 获得数据列表
	 *
	 * @param pageNow   当前页
	 * @param pageSize  每页数据量
	 * @param queryHash 查询参数
	 * @return 数据列表.
	 */
	public List<T> findList(Integer pageNow, Integer pageSize, Map<String, String> queryHash) {
		return this.findList(pageNow, pageSize, "", queryHash);
	}

	/**
	 * 获得数据列表
	 *
	 * @param pageNow   当前页
	 * @param pageSize  每页数据量
	 * @param sqlOrder  排序
	 * @param queryHash 查询参数
	 * @return 数据列表.
	 */
	public List<T> findList(Integer pageNow, Integer pageSize, String sqlOrder, Map<String, String> queryHash) {
		return dao.findList(pageNow, pageSize, sqlOrder, queryHash);
	}

	/**
	 * 根据id查询单条数据
	 *
	 * @param id 主键id
	 * @return Model.
	 */
	public T findModel(Long id) {
		return dao.findModel(id);
	}

	/**
	 * 新增或修改数据
	 *
	 * @param model 待新增或更新的对象.
	 */
	@Transactional(readOnly = false)
	public void addOrUpdate(T model) {
		/*if (model.getAdminUserId() != null && model.getAdminUserId() > 0) {
			logger.info("当前需修改的AdminUser对象admin_user_id为[" + model.getAdminUserId() + "]");
			dao.update(model);
		} else {
			logger.info("AdminUserBizImpl添加adminUser！");
			dao.add(model);
		}*/
	}

	/**
	 * 真删除数据
	 *
	 * @param id 主键id
	 */
	@Transactional
	public void delete(Long id) {
		dao.delete(id);
	}

	// ******************************************************************************
	// ********************************** CRUD END **********************************
	// ******************************************************************************
}
