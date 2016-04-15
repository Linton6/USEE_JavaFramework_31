package com.ysotek.customer.modules.security.biz;

import com.vt1314.framework.extend.biz.CrudBiz;
import com.vt1314.framework.sugar.data.QueryParam;
import com.ysotek.customer.modules.security.dao.SystemResourceDao;
import com.ysotek.customer.modules.security.entity.SystemResource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: 居泽平
 */
@Service("systemResourceBizWeb")
public class SystemResourceBiz extends CrudBiz<SystemResourceDao, SystemResource> {

	public List<SystemResource> getResourceByAuthId(Long authId) {

		QueryParam queryParam = new QueryParam();
		queryParam.getSqlMap().put("existsAuthId", authId == null ? "-1" : authId.toString());

		return findList(queryParam);
	}

	public List<SystemResource> getOtherResourceByAuthId(Long authId) {

		QueryParam queryParam = new QueryParam();
		queryParam.getSqlMap().put("notExistsAuthId", authId == null ? "-1" : authId.toString());

		return findList(queryParam);
	}
}