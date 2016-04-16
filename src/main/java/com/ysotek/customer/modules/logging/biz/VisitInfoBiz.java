package com.ysotek.customer.modules.logging.biz;


import com.vt1314.framework.extend.biz.BaseBiz;
import com.ysotek.customer.modules.logging.dao.VisitInfoDao;
import com.ysotek.customer.modules.logging.entity.VisitInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: Mingjie Pan  Date: 13-7-6, 上午11:43
 */
@Service("visitInfoBiz")
public class VisitInfoBiz extends BaseBiz {

	@Autowired
	private VisitInfoDao visitDao;

	@Transactional
	public void logVisitInfo(VisitInfo visitInfo) {
		visitDao.logVisitInfo(visitInfo);
	}
}
