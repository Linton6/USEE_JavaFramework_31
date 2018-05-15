package com.useeinfo.demo.modules.people.biz;

import com.useeinfo.demo.modules.people.dao.SystemPeopleDao;
import com.useeinfo.demo.modules.people.entity.SystemPeople;
import com.useeinfo.framework.sugar.data.QueryParam;
import com.useeinfo.framework.extend.biz.CrudBiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: 居泽平
 */
@Service("systemPeopleBizWeb")
public class SystemPeopleBiz extends CrudBiz<SystemPeopleDao, SystemPeople> {

//	public Boolean existPeopleAccount(SystemPeople systemPeople) {
		//若用户名为空
//		logger.info("用户名为：" + systemPeople.getPeopleAccount());
//		if (StringUtils.isBlank(systemPeople.getPeopleAccount())) {
//			return true;
//		}

//		//根据入库单号查询
//		QueryParam queryParam = new QueryParam(1);
//
//		queryParam.getSqlMap().put("peopleAccount", systemPeople.getPeopleAccount());
//		List<SystemPeople> systemPeopleList = findList(queryParam);
//		if (systemPeople.getPeopleId() == null) {
//			//新增操作，判断列表size是否为0
//			return CollectionUtils.isNotEmpty(systemPeopleList);
//		} else {
//			//修改操作，情况一：判断list长度是否为1，并且与自身相同，那么就是不存在，所以结果取反
//			//修改操作，情况二：判断list长度是0
//			return !((systemPeopleList.size() == 1 && systemPeopleList.get(0).getPeopleId().equals(systemPeople.getPeopleId())) || systemPeopleList.size() == 0);
//		}
//	}

//	@Transactional
//	public SystemUser getByUserAccount(String userAccount) {
//
//		if (StringUtils.isBlank(userAccount)) {
//			return null;
//		}
//
//		QueryParam queryParam = new QueryParam(1);
//		queryParam.getSqlMap().put("userAccount", userAccount);
//
//		List<SystemUser> systemUserList = findList(queryParam);
//		if (CollectionUtils.isEmpty(systemUserList)) {
//			return null;
//		}
//
//		//查询结果串不为空，则返回第一个
//		SystemUser systemUser = systemUserList.get(0);
//		logger.debug("systemRole:[{}]", systemUser.getSystemRole());
//
//		return systemUser;
//	}

//	public List<SystemUser> findByRoleId(Long roleId) {
//		return this.dao.findByRoleId(roleId);
//	}

	@Transactional
	public void logicRemove(Long peopleId) {
		dao.logicRemove(peopleId);
	}
}