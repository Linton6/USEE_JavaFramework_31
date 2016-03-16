package com.vt1314.base.sugar.data;

import com.vt1314.base.config.ConstantKeyGlobal;
import com.vt1314.base.sugar.tools.CommonSugar;
import com.vt1314.base.sugar.tools.StringConverters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: 居泽平  Date: 16/3/10, 15:25
 */
@SuppressWarnings("unused")
public class QueryParam {

	private final static Logger logger = LoggerFactory.getLogger(QueryParam.class);

	/**
	 * 分页参数
	 */
	private Integer pageNow;
	private Integer pageSize;

	public Integer getPageNow() {
		return CommonSugar.getTypedDefault(pageNow, 0);
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}

	public void setPageNow(String pageNowParam) {
		Integer pageNow = StringConverters.ToInteger(pageNowParam);
		this.pageNow = CommonSugar.getTypedDefault(pageNow, 1);
	}

	public Integer getPageSize() {
		return CommonSugar.getTypedDefault(pageSize, 0);
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageSize(String pageSizeParam) {
		Integer pageSize = StringConverters.ToInteger(pageSizeParam);
		this.pageSize = CommonSugar.getTypedDefault(pageSize, ConstantKeyGlobal.DEFAULT_PAGE_LIST_NUM);
	}

	/**
	 * 自定义SQL（SQL标识，SQL内容）
	 */
	private Map<String, String> sqlMap;

	public Map<String, String> getSqlMap() {
		if (sqlMap == null) {
			sqlMap = new HashMap<>();
		}
		return sqlMap;
	}

	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}

	/**
	 * 查询排序
	 */
	public String sqlOrder;

	public String getSqlOrder() {
		return sqlOrder;
	}

	public void setSqlOrder(String sqlOrder) {
		this.sqlOrder = sqlOrder;
	}

	/**
	 * HttpServletRequest & HttpServletResponse
	 */
	public HttpServletRequest request;
	public HttpServletResponse response;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * *********************************************************************************
	 * *********************************************************************************
	 * 构造函数
	 * *********************************************************************************
	 * *********************************************************************************
	 */
	public QueryParam() {
	}

	public QueryParam(Integer pageNow, Integer pageSize) {
		this.pageNow = CommonSugar.getTypedDefault(pageNow, 1);
		this.pageSize = CommonSugar.getTypedDefault(pageSize, ConstantKeyGlobal.DEFAULT_PAGE_LIST_NUM);
	}

	public QueryParam(String pageNowParam, String pageSizeParam) {
		Integer pageNow = StringConverters.ToInteger(pageNowParam);
		Integer pageSize = StringConverters.ToInteger(pageSizeParam);

		this.pageNow = CommonSugar.getTypedDefault(pageNow, 1);
		this.pageSize = CommonSugar.getTypedDefault(pageSize, ConstantKeyGlobal.DEFAULT_PAGE_LIST_NUM);
	}

	/**
	 * *********************************************************************************
	 * *********************************************************************************
	 * 设定分页
	 * *********************************************************************************
	 * *********************************************************************************
	 */
	public <T> List<T> setPageAndFindList(TypedQuery<T> typedQuery) {
		// 判断是否需要分页，并提交分页方法
		if (this.getPageNow() > 0 && this.getPageSize() > 0) {
			logger.debug("提交了分页查询信息，pageNow为[" + this.getPageNow() + "]，pageSize为[" + this.getPageSize() + "]");
			int minLimit = this.getPageSize() * (this.getPageNow() - 1);
			int maxLimit = this.getPageSize();
			typedQuery.setFirstResult(minLimit).setMaxResults(maxLimit);
		}

		// 返回查询结果
		return typedQuery.getResultList();
	}
}
