/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.useeinfo.framework.extend.entity;

import net.sf.json.JSONObject;

import java.io.Serializable;

/**
 * Entity支持类
 *
 * @author 居泽平
 * @version 2016-03-10
 */
@SuppressWarnings("unused")
public abstract class BaseEntity<T> implements Serializable {

	public abstract JSONObject toJSONObject();
}
