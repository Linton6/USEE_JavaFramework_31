package com.useeinfo.framework.sugar.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.SqlResultSetMapping;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Author: 居泽平  Date: 13-8-23, 下午5:33
 */
@SqlResultSetMapping(
		name = "OrderWaybillStatistics"

)
public final class QueryUtilsNative {

	private final static Logger logger = LoggerFactory.getLogger(QueryUtilsNative.class);

	/**
	 * 通过传入相应的参数，获取TypedQuery对象以进行下一步的数据查询
	 * 注意：当进行模糊查询时需自行判断参数是否为NULL
	 *
	 * @param selectInfo    JPQL选择语句
	 * @param condition     JPQL条件语句hash列表
	 * @param orderInfo     JPQL排序语句
	 * @param entityManager EntityManager Object.
	 * @return Query Object.
	 */
	public static Query getTypedQueryByCondition(String selectInfo, Map<String, Object> condition, String orderInfo, EntityManager entityManager, boolean isCount) {

		logger.info("---------------------------------------");

		StringBuilder query = new StringBuilder();
		query.append(selectInfo);
		query.append("where 1 = 1 ");

		int i = 1;
		if (condition != null) {

			for (Entry<String, Object> entry : condition.entrySet()) {
				String key = entry.getKey();
				Object conditionValue = entry.getValue();
				if (conditionValue != null && !"".equals(conditionValue) && key != null) {
					key = key.trim();
					key = key.replace("{paramIndex}", String.valueOf(i++));
					if (!key.startsWith("and") && !key.startsWith("or")) {
						query.append(" and ");
					}
					query.append(key);
					query.append(" ");
				}
			}
		}

		query.append(orderInfo);

		String sql = query.toString();

		if (isCount) {
			sql = String.format("select count(*) from (%s) as rsc", sql);
		}

		logger.info("拼接完成的sql语句为：[" + sql + "]");

		Query nativeQuery = entityManager.createNativeQuery(sql);

		if (condition != null) {
			i = 1;
			for (Entry<String, Object> entry : condition.entrySet()) {
				Object conditionValue = entry.getValue();
				if (conditionValue != null && !"".equals(conditionValue)) {
					nativeQuery.setParameter(i, conditionValue);
					logger.info("为拼接完成的JPQL第[" + i++ + "]个参数赋值:[" + conditionValue + "]");
				}
			}
		}

		logger.info("---------------------------------------");

		return nativeQuery;
	}
}
