package com.useeinfo.framework.extend.biz;

import com.useeinfo.framework.extend.dao.CrudDao;
import com.useeinfo.framework.extend.entity.DataEntity;
import com.useeinfo.framework.sugar.data.QueryData;
import com.useeinfo.framework.sugar.data.QueryParam;
import com.useeinfo.framework.sugar.tools.CommonSugar;
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

		queryParam = CommonSugar.getTypedDefault(queryParam, new QueryParam(0));
		List<T> list = this.findList(queryParam);
		Long totalNum = this.totalRecord(queryParam);

		return new QueryData<>(list, totalNum);
	}

	/**
	 * 获得数据列表
	 */
	public List<T> findList() {
		return this.findList(null);
	}

	public List<T> findList(QueryParam queryParam) {
		queryParam = CommonSugar.getTypedDefault(queryParam, new QueryParam(0));
		return dao.findList(queryParam);
	}

	/**
	 * 获得总记录数
	 */
	public Long totalRecord() {
		return this.totalRecord(null);
	}

	public Long totalRecord(QueryParam queryParam) {
		return dao.totalRecord(queryParam);
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
	@Transactional(readOnly = false)
	public void delete(Long id) {
		dao.delete(id);
	}

	// ******************************************************************************
	// ********************************** CRUD END **********************************
	// ******************************************************************************
}
