package com.ysotek.example.modules.security.biz;

import com.vt1314.base.extend.biz.CrudBiz;
import com.vt1314.base.sugar.data.QueryParam;
import com.ysotek.example.modules.security.dao.SystemResourceDao;
import com.ysotek.example.modules.security.entity.SystemResource;
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