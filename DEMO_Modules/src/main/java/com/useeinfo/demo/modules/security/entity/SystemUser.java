package com.useeinfo.demo.modules.security.entity;

import com.useeinfo.framework.extend.entity.DataEntity;
import com.useeinfo.framework.sugar.tools.CommonSugar;
import net.sf.json.JSONObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * Author: 居泽平  Date: 14-3-31, 上午10:02
 */
@Entity
@Table(name = "wms_system_user")
@GenericGenerator(name = "WMS_SYSTEM_USER_GEN", strategy = "enhanced-table",
		parameters = {
				@Parameter(name = "table_name", value = "table_generator"),
				@Parameter(name = "value_column_name", value = "next"),
				@Parameter(name = "segment_column_name", value = "segment_name"),
				@Parameter(name = "segment_value", value = "wms_system_user_id"),
				@Parameter(name = "initial_value", value = "1000"),
				@Parameter(name = "increment_size", value = "10"),
				@Parameter(name = "optimizer", value = "pooled-lo")
		})
public class SystemUser extends DataEntity<SystemUser> {

	/**
	 * 主键编号
	 */
	private Long userId;

	/**
	 * 用户登陆帐号
	 */
	private String userAccount;

	/**
	 * 用户登陆密码
	 */
	private String userPassword;

	/**
	 * 用户姓名
	 */
	private String userName;

	/**
	 * 是否平台管理员 true-平台管理员 false-小区管理员
	 */
	private boolean systemManager;

	/**
	 * 用户角色
	 */
	private SystemRole systemRole;

	/**
	 * 是否被禁用 true-正常 false-已禁用
	 */
	private boolean enabled;

	/**
	 * 假删除 true-正常 false-已删除
	 */
	private boolean valid = true;

	@Override
	public JSONObject toJSONObject() {
		JSONObject jsonObject = new JSONObject();

		SystemRole systemRole = getSystemRole() == null ? new SystemRole() : getSystemRole();

		jsonObject.put("userId", CommonSugar.getTypedDefault(getUserId(), 0L));
		jsonObject.put("userAccount", CommonSugar.getTypedDefault(getUserAccount(), ""));
		jsonObject.put("userPassword", CommonSugar.getTypedDefault(getUserPassword(), ""));
		jsonObject.put("userName", CommonSugar.getTypedDefault(getUserName(), ""));
		jsonObject.put("isSystemManager", isSystemManager());
		jsonObject.put("systemRoleId", CommonSugar.getTypedDefault(systemRole.getRoleName(), ""));
		jsonObject.put("enabled", isEnabled());
		jsonObject.put("valid", isValid());

		return jsonObject;
	}

	public SystemUser() {
	}

	public SystemUser(String userAccount, String userPassword, String userName) {
		this.userAccount = userAccount;
		this.userPassword = userPassword;
		this.userName = userName;
	}

	@Id
	@GeneratedValue(generator = "WMS_SYSTEM_USER_GEN")
	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	@Override
	@Transient
	public Long getId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "user_account")
	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	@Column(name = "user_password")
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "system_manager")
	public boolean isSystemManager() {
		return systemManager;
	}

	public void setSystemManager(boolean systemManager) {
		this.systemManager = systemManager;
	}

	@ManyToOne
	@JoinColumn(name = "system_role_id")
	public SystemRole getSystemRole() {
		return systemRole;
	}

	public void setSystemRole(SystemRole systemRole) {
		this.systemRole = systemRole;
	}

	@Column(name = "enabled")
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "valid")
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
