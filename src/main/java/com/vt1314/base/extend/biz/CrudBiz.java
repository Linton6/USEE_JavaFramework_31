package com.vt1314.base.extend.biz;

import com.vt1314.base.extend.dao.CrudDao;
import com.vt1314.base.extend.entity.DataEntity;
import com.vt1314.base.sugar.data.QueryData;
import com.vt1314.base.sugar.data.QueryParam;
import com.vt1314.base.sugar.tools.CommonSugar;
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
	 */
	public JSONObject findJSONList() {
		return this.findJSONList(null, null);
	}

	public JSONObject findJSONList(QueryParam queryParam) {
		return this.findJSONList(queryParam, null);
	}

	public JSONObject findJSONList(String jsonConverterName) {
		return this.findJSONList(null, jsonConverterName);
	}

	public JSONObject findJSONList(QueryParam queryParam, String jsonConverterName) {
		jsonConverterName = CommonSugar.getTypedDefault(jsonConverterName, "toJSONObject");
		return this.findNumList(queryParam).toJSONObject(jsonConverterName);
	}

	/**
	 * 分页获得数据列表并且带有总记录数
	 */
	public QueryData<T> findNumList() {
		return this.findNumList(null);
	}

	public QueryData<T> findNumList(QueryParam queryParam) {

		List<T> list = this.findList(queryParam);
		Long totalNum = this.totalRecord(queryParam.getSqlMap());

		return new QueryData<>(list, totalNum);
	}

	/**
	 * 获得数据列表
	 */
	public List<T> findList() {
		return this.findList(null);
	}

	public List<T> findList(QueryParam queryParam) {
		queryParam = CommonSugar.getTypedDefault(queryParam, new QueryParam());
		return dao.findList(queryParam);
	}

	/**
	 * 获得总记录数
	 */
	public Long totalRecord() {
		return this.totalRecord(null);
	}

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
