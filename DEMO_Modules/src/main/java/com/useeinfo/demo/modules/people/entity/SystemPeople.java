package com.useeinfo.demo.modules.people.entity;

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
@Table(name = "wms_system_people")
@GenericGenerator(name = "WMS_SYSTEM_PEOPLE_GEN", strategy = "enhanced-table",
		parameters = {
				@Parameter(name = "table_name", value = "table_generator"),
				@Parameter(name = "value_column_name", value = "next"),
				@Parameter(name = "segment_column_name", value = "segment_name"),
				@Parameter(name = "segment_value", value = "wms_system_people_id"),
				@Parameter(name = "initial_value", value = "1000"),
				@Parameter(name = "increment_size", value = "10"),
				@Parameter(name = "optimizer", value = "pooled-lo")
		})
public class SystemPeople extends DataEntity<SystemPeople> {

	/**
	 * 主键编号
	 */
	private Long peopleId;

	/**
	 * 用户登陆帐号
	 */
//	private String userAccount;

	/**
	 * 用户登陆密码
	 */
//	private String userPassword;

	/**
	 * 用户姓名
	 */
	private String peopleName;

	/**
	 * 是否平台管理员 true-平台管理员 false-小区管理员
	 */
//	private boolean systemManager;

	/**
	 * 用户角色
	 */
//	private SystemRole systemRole;

	/**
	 * 是否被禁用 true-正常 false-已禁用
	 */
//	private boolean enabled;

	/**
	 * 假删除 true-正常 false-已删除
	 */
//	private boolean valid = true;

	@Override
	public JSONObject toJSONObject() {
		JSONObject jsonObject = new JSONObject();

//		SystemRole systemRole = getSystemRole() == null ? new SystemRole() : getSystemRole();

		jsonObject.put("peopleId", CommonSugar.getTypedDefault(getPeopleId(), 0L));
//		jsonObject.put("peopleAccount", CommonSugar.getTypedDefault(getUserAccount(), ""));
//		jsonObject.put("userPassword", CommonSugar.getTypedDefault(getUserPassword(), ""));
		jsonObject.put("peopleName", CommonSugar.getTypedDefault(getPeopleName(), ""));
//		jsonObject.put("isSystemManager", isSystemManager());
//		jsonObject.put("systemRoleId", CommonSugar.getTypedDefault(systemRole.getRoleName(), ""));
//		jsonObject.put("enabled", isEnabled());
//		jsonObject.put("valid", isValid());

		return jsonObject;
	}

	public SystemPeople() {

	}

	public SystemPeople(Long peopleId, String peopleName) {
		this.peopleId = peopleId;
		this.peopleName = peopleName;
	}

	//	public SystemPeople(String userAccount, String userPassword, String userName) {
////		this.userAccount = userAccount;
////		this.userPassword = userPassword;
//		this.userName = userName;
//	}

	@Id
	@GeneratedValue(generator = "WMS_SYSTEM_PEOPLE_GEN")
	@Column(name = "people_id")
	public Long getPeopleId() {
		return peopleId;
	}

	@Override
	@Transient
	public Long getId() {
		return peopleId;
	}

	public void setPeopleId(Long peopleId) {
		this.peopleId = peopleId;
	}

//	@Column(name = "people_account")
//	public String getUserAccount() {
//		return userAccount;
//	}

//	public void setUserAccount(String userAccount) {
//		this.userAccount = userAccount;
//	}

//	@Column(name = "user_password")
//	public String getUserPassword() {
//		return userPassword;
//	}

//	public void setUserPassword(String userPassword) {
//		this.userPassword = userPassword;
//	}

	@Column(name = "people_name")
	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String userName) {
		this.peopleName = userName;
	}

//	@Column(name = "system_manager")
//	public boolean isSystemManager() {
//		return systemManager;
//	}

//	public void setSystemManager(boolean systemManager) {
//		this.systemManager = systemManager;
//	}

//	@ManyToOne
//	@JoinColumn(name = "system_role_id")
//	public SystemRole getSystemRole() {
//		return systemRole;
//	}

//	public void setSystemRole(SystemRole systemRole) {
//		this.systemRole = systemRole;
//	}

//	@Column(name = "enabled")
//	public boolean isEnabled() {
//		return enabled;
//	}

//	public void setEnabled(boolean enabled) {
//		this.enabled = enabled;
//	}

//	@Column(name = "valid")
//	public boolean isValid() {
//		return valid;
//	}

//	public void setValid(boolean valid) {
//		this.valid = valid;
//	}
}
