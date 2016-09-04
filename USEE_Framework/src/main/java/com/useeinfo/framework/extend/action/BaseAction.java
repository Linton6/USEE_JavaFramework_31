package com.useeinfo.framework.extend.action;

import com.useeinfo.framework.sugar.tools.StringConverters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * Author: 居泽平  Date: 16/3/10, 15:44
 */
@SuppressWarnings("unused")
public abstract class BaseAction {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		/*binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml(text.trim()));
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});*/

		/*binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(text == null ? null : StringConverters.ToDateTime(text.trim()));
			}
		});*/
	}
}
