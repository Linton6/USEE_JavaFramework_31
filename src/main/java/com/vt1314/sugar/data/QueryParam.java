package com.vt1314.sugar.data;

import com.company.example.sys.entity.AdminUser;
import com.vt1314.sugar.config.ConstantKeyGlobal;
import com.vt1314.sugar.tools.CommonSugar;
import com.vt1314.sugar.tools.StringConverters;

import java.util.Map;

/**
 * Author: 居泽平  Date: 16/3/10, 15:25
 */
public class QueryParam {

	public QueryParam() {
	}

	public QueryParam(Integer pageNow, Integer pageSize) {
		this.pageNow = pageNow;
		this.pageSize = pageSize;
	}

	public QueryParam(AdminUser currentUser, Integer pageNow, Integer pageSize, Map<String, String> sqlMap) {
		this.currentUser = currentUser;
		this.pageNow = pageNow;
		this.pageSize = pageSize;
		this.sqlMap = sqlMap;
	}

	public QueryParam(String pageNowParam, String pageSizeParam) {
		Integer pageNow = StringConverters.ToInteger(pageNowParam);
		Integer pageSize = StringConverters.ToInteger(pageSizeParam);

		this.pageNow = CommonSugar.getTypedDefault(pageNow, 1);
		this.pageSize = CommonSugar.getTypedDefault(pageSize, ConstantKeyGlobal.DEFAULT_PAGE_LIST_NUM);
	}

	public QueryParam(AdminUser currentUser, String pageNowParam, String pageSizeParam, Map<String, String> sqlMap) {
		Integer pageNow = StringConverters.ToInteger(pageNowParam);
		Integer pageSize = StringConverters.ToInteger(pageSizeParam);

		this.currentUser = currentUser;
		this.pageNow = CommonSugar.getTypedDefault(pageNow, 1);
		this.pageSize = CommonSugar.getTypedDefault(pageSize, ConstantKeyGlobal.DEFAULT_PAGE_LIST_NUM);
		this.sqlMap = sqlMap;
	}

	/**
	 * 当前用户
	 */
	private AdminUser currentUser;

	public AdminUser getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(AdminUser currentUser) {
		this.currentUser = currentUser;
	}


	/**
	 * 分页参数
	 */
	private Integer pageNow;
	private Integer pageSize;

	public Integer getPageNow() {
		return pageNow;
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}

	public void setPageNow(String pageNowParam) {
		Integer pageNow = StringConverters.ToInteger(pageNowParam);
		this.pageNow = CommonSugar.getTypedDefault(pageNow, 1);
	}

	public Integer getPageSize() {
		return pageSize;
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
		return sqlMap;
	}

	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}
}
