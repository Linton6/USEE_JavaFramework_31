package com.useeinfo.demo.modules.security.biz;

import com.useeinfo.demo.modules.security.dao.SystemUserDao;
import com.useeinfo.demo.modules.security.entity.SystemUser;
import com.useeinfo.framework.extend.biz.CrudBiz;
import com.useeinfo.framework.sugar.data.QueryParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: 居泽平
 */
@Service("systemUserBizWeb")
public class SystemUserBiz extends CrudBiz<SystemUserDao, SystemUser> {

	public Boolean existUserAccount(SystemUser systemUser) {
		//若用户名为空
		logger.info("用户名为：" + systemUser.getUserAccount());
		if (StringUtils.isBlank(systemUser.getUserAccount())) {
			return true;
		}

		//根据入库单号查询
		QueryParam queryParam = new QueryParam(1);
		queryParam.getSqlMap().put("userAccount", systemUser.getUserAccount());
		List<SystemUser> systemUserList = findList(queryParam);

		if (systemUser.getUserId() == null) {
			//新增操作，判断列表size是否为0
			return CollectionUtils.isNotEmpty(systemUserList);
		} else {
			//修改操作，情况一：判断list长度是否为1，并且与自身相同，那么就是不存在，所以结果取反
			//修改操作，情况二：判断list长度是0
			return !((systemUserList.size() == 1 && systemUserList.get(0).getUserId().equals(systemUser.getUserId())) || systemUserList.size() == 0);
		}
	}

	@Transactional
	public SystemUser getByUserAccount(String userAccount) {

		if (StringUtils.isBlank(userAccount)) {
			return null;
		}

		QueryParam queryParam = new QueryParam(1);
		queryParam.getSqlMap().put("userAccount", userAccount);

		List<SystemUser> systemUserList = findList(queryParam);
		if (CollectionUtils.isEmpty(systemUserList)) {
			return null;
		}

		//查询结果串不为空，则返回第一个
		SystemUser systemUser = systemUserList.get(0);
		logger.debug("systemRole:[{}]", systemUser.getSystemRole());

		return systemUser;
	}

	public List<SystemUser> findByRoleId(Long roleId) {
		return this.dao.findByRoleId(roleId);
	}

	@Transactional
	public void logicRemove(Long userId) {
		dao.logicRemove(userId);
	}
}