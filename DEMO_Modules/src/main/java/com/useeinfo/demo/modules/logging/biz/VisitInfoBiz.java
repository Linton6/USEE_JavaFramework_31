package com.useeinfo.demo.modules.logging.biz;


import com.useeinfo.demo.modules.logging.dao.VisitInfoDao;
import com.useeinfo.demo.modules.logging.entity.VisitInfo;
import com.useeinfo.framework.extend.biz.BaseBiz;
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
