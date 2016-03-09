package com.company.example.sys.service;

import com.company.example.sys.dao.AdminUserDao;
import com.company.example.sys.entity.AdminUser;
import com.vt1314.sugar.extend.service.CrudService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: val.jzp
 */
@Service
@Transactional(readOnly = true)
public class AdminUserService extends CrudService<AdminUserDao, AdminUser> {

	public AdminUser login(String loginName, String loginPassword) {

		if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(loginPassword)) {
			return null;
		}

		Map<String, String> queryHash = new HashMap<>();
		queryHash.put("loginName", loginName);
		queryHash.put("loginPassword", loginPassword);

		List<AdminUser> adminUserList = findList(queryHash);
		if (adminUserList != null && adminUserList.size() > 0) {
			return adminUserList.get(0);
		} else {
			return null;
		}
	}
}