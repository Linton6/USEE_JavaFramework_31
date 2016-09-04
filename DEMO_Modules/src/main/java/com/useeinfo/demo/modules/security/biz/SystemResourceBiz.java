package com.useeinfo.demo.modules.security.biz;

import com.useeinfo.demo.modules.security.dao.SystemResourceDao;
import com.useeinfo.demo.modules.security.entity.SystemResource;
import com.useeinfo.framework.extend.biz.CrudBiz;
import com.useeinfo.framework.sugar.data.QueryParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: 居泽平
 */
@Service("systemResourceBizWeb")
public class SystemResourceBiz extends CrudBiz<SystemResourceDao, SystemResource> {

	public List<SystemResource> getResourceByAuthId(Long authId) {

		QueryParam queryParam = new QueryParam(1);
		queryParam.getSqlMap().put("existsAuthId", authId == null ? "-1" : authId.toString());

		return findList(queryParam);
	}

	public List<SystemResource> getOtherResourceByAuthId(Long authId) {

		QueryParam queryParam = new QueryParam(1);
		queryParam.getSqlMap().put("notExistsAuthId", authId == null ? "-1" : authId.toString());

		return findList(queryParam);
	}
}