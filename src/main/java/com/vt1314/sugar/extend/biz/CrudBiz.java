package com.vt1314.sugar.extend.biz;

import com.vt1314.sugar.config.ConstantKeyGlobal;
import com.vt1314.sugar.data.QueryData;
import com.vt1314.sugar.extend.dao.CrudDao;
import com.vt1314.sugar.extend.entity.DataEntity;
import com.vt1314.sugar.tools.CommonSugar;
import com.vt1314.sugar.tools.StringConverters;
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
public abstract class CrudBiz<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseBiz {

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
	 * @return JSONObject.
	 */
	public JSONObject findJSONList() {
		return findJSONList(0, 0, null);
	}

	/**
	 * 获得列表JSON
	 *
	 * @param jsonConverterName 对应的JSON转换方法名称
	 * @return JSONObject.
	 */
	public JSONObject findJSONList(String jsonConverterName) {
		return findJSONList(0, 0, null, null);
	}

	/**
	 * 获得列表JSON
	 *
	 * @param pageNow  当前页
	 * @param pageSize 每页数据量
	 * @return JSONObject.
	 */
	public JSONObject findJSONList(Integer pageNow, Integer pageSize) {
		return findJSONList(pageNow, pageSize, null, null);
	}

	/**
	 * 获得列表JSON
	 *
	 * @param pageNowParam  当前页
	 * @param pageSizeParam 每页数据量
	 * @return JSONObject.
	 */
	public JSONObject findJSONList(String pageNowParam, String pageSizeParam) {
		Integer pageNow = StringConverters.ToInteger(pageNowParam);
		Integer pageSize = StringConverters.ToInteger(pageSizeParam);

		pageNow = CommonSugar.getTypedDefault(pageNow, 1);
		pageSize = CommonSugar.getTypedDefault(pageSize, ConstantKeyGlobal.DEFAULT_PAGE_LIST_NUM);

		return findJSONList(pageNow, pageSize, null, null);
	}

	/**
	 * 获得列表JSON
	 *
	 * @param queryHash         查询参数
	 * @param jsonConverterName JSON转换的方法名
	 * @return JSONObject.
	 */
	public JSONObject findJSONList(Map<String, String> queryHash, String jsonConverterName) {
		return findJSONList(0, 0, queryHash, jsonConverterName);
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
		return findJSONList(pageNow, pageSize, null, jsonConverterName);
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
		jsonConverterName = CommonSugar.getTypedDefault(jsonConverterName, "toJSONObject");
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
	 * 根据id查询单条数据
	 *
	 * @param id 主键id
	 * @return Model.
	 */
	public T findModel(Long id) {
		if (id == null) {
			return null;
		}

		return dao.findModel(id);
	}

	/**
	 * 新增或修改数据
	 *
	 * @param model 待新增或更新的对象.
	 */
	@Transactional(readOnly = false)
	public void addOrUpdate(T model) {
		if (model.getId() != null && model.getId() > 0) {
			logger.info("当前需修改的[{}]对象id为[{}]", model.getClass(), model.getId());
			dao.update(model);
		} else {
			logger.info("添加[{}]!", model.getClass());
			dao.add(model);
		}
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
