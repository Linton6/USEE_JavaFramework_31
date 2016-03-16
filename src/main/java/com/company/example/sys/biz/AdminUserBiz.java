package com.company.example.sys.biz;

import com.company.example.sys.dao.AdminUserDao;
import com.company.example.sys.entity.AdminUser;
import com.vt1314.base.extend.biz.CrudBiz;
import com.vt1314.base.sugar.data.QueryParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: val.jzp
 */
@Service
@Transactional(readOnly = true)
public class AdminUserBiz extends CrudBiz<AdminUserDao, AdminUser> {

	public AdminUser login(String loginName, String loginPassword) {

		if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(loginPassword)) {
			return null;
		}

		QueryParam queryParam = new QueryParam();
		queryParam.getSqlMap().put("loginName", loginName);
		queryParam.getSqlMap().put("loginPassword", loginPassword);

		List<AdminUser> adminUserList = findList(queryParam);
		if (adminUserList != null && adminUserList.size() > 0) {
			return adminUserList.get(0);
		} else {
			return null;
		}
	}
}