package com.useeinfo.demo.modules.logging.entity;

import com.useeinfo.demo.modules.security.entity.SystemUser;
import com.useeinfo.framework.extend.entity.DataEntity;
import net.sf.json.JSONObject;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Author: Mingjie Pan  Date: 13-7-6, 上午11:03
 */
@Entity
@Table(name = "wms_api_visit_info")
@GenericGenerator(name = "VISIT_INFO_GENERATOR", strategy = "enhanced-table",
		parameters = {
				@org.hibernate.annotations.Parameter(name = "table_name", value = "table_generator"),
				@org.hibernate.annotations.Parameter(name = "segment_column_name", value = "segment_name"),
				@org.hibernate.annotations.Parameter(name = "segment_value", value = "info_id"),
				@org.hibernate.annotations.Parameter(name = "value_column_name", value = "next"),
				@org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
				@org.hibernate.annotations.Parameter(name = "increment_size", value = "10"),
				@org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo")
		}
)
public class VisitInfo extends DataEntity<VisitInfo> {

	/**
	 * 信息ID（主键）
	 */
	private Long infoId;


	/**
	 * 用户名
	 */
	private String userName;

	private SystemUser systemUser;

	/**
	 * 密码
	 */
	private String passwd;

	/**
	 * 代理信息
	 */
	private String userAgent;

	/**
	 * 访问时间
	 */
	private Date visitTime;

	/**
	 * 访问路径
	 */
	private String url;

	/**
	 * 客户端IP地址
	 */
	private String clientIP;

	/**
	 * Authorization
	 */
	private String authorization;

	@Override
	public JSONObject toJSONObject() {
		return null;
	}

	@Id
	@GeneratedValue(generator = "VISIT_INFO_GENERATOR")
	@Column(name = "info_id")
	public Long getInfoId() {
		return infoId;
	}

	@Override
	@Transient
	public Long getId() {
		return infoId;
	}

	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@ManyToOne
	@JoinColumn(name = "system_user_id")
	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

	@Column(name = "user_agent")
	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	@Column(name = "visit_time")
	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "client_ip")
	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	@Column(name = "authorization")
	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	@Column(name = "passwd")
	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public String toString() {
		return "VisitInfo{" +
				"infoId=" + infoId +
				", userName='" + userName + '\'' +
				", passwd='" + passwd + '\'' +
				", userAgent='" + userAgent + '\'' +
				", visitTime=" + visitTime +
				", url='" + url + '\'' +
				", clientIP='" + clientIP + '\'' +
				", authorization='" + authorization + '\'' +
				'}';
	}
}
