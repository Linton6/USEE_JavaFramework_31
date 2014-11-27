package com.softtek.base.action.converter;

import com.softtek.base.domain.AdminUser;
import com.softtek.base.sugar.tools.DateTimeUtils;
import com.softtek.base.sugar.web.WebLayerUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Author: val.jzp
 */
public class AdminUserConverter {

	public static JSONObject getJson(List<AdminUser> adminUserList, Long totalNum) {
		JSONObject result = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		for (AdminUser adminUser : adminUserList) {
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("adminUserId", WebLayerUtil.getDefaultByNullLong(adminUser.getAdminUserId(), 0L));
			jsonObject.put("loginName", WebLayerUtil.getDefaultByNullString(adminUser.getLoginName(), ""));
			jsonObject.put("loginPassword", WebLayerUtil.getDefaultByNullString(adminUser.getLoginPassword(), ""));
			jsonObject.put("realName", WebLayerUtil.getDefaultByNullString(adminUser.getRealName(), ""));
			jsonObject.put("email", WebLayerUtil.getDefaultByNullString(adminUser.getEmail(), ""));
			jsonObject.put("createDate", WebLayerUtil.getDefaultByNullString(DateTimeUtils.formatDateToStringWithTime(adminUser.getCreateDate()), ""));
			jsonObject.put("updateDate", WebLayerUtil.getDefaultByNullString(DateTimeUtils.formatDateToStringWithTime(adminUser.getUpdateDate()), ""));

			jsonArray.add(jsonObject);
		}

		result.put("total", totalNum);
		result.put("rows", jsonArray);

		return result;
	}
}