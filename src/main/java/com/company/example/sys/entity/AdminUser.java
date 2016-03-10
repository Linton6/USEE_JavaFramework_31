package com.company.example.sys.entity;

import com.vt1314.sugar.extend.entity.DataEntity;
import com.vt1314.sugar.tools.CommonSugar;
import com.vt1314.sugar.tools.DateTimeUtils;
import net.sf.json.JSONObject;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Author: 居泽平  Date: 14/11/25, 10:11
 */
@Entity
@Table(name = "base_admin_user")
@GenericGenerator(name = "BASE_ADMIN_USER_GEN", strategy = "enhanced-table",
		parameters = {
				@org.hibernate.annotations.Parameter(name = "table_name", value = "table_generator"),
				@org.hibernate.annotations.Parameter(name = "segment_column_name", value = "segment_name"),
				@org.hibernate.annotations.Parameter(name = "segment_value", value = "admin_user_id"),
				@org.hibernate.annotations.Parameter(name = "value_column_name", value = "next"),
				@org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
				@org.hibernate.annotations.Parameter(name = "increment_size", value = "10"),
				@org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo")
		}
)
@SuppressWarnings("unused")
public class AdminUser extends DataEntity<AdminUser> {

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 用户名
	 */
	private String loginName;

	/**
	 * 登录密码
	 */
	private String loginPassword;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 创建时间（注册时间）
	 */
	private Date createDate;

	/**
	 * 更新时间
	 */
	private Date updateDate;


	@Id
	@GeneratedValue(generator = "BASE_ADMIN_USER_GEN")
	@Column(name = "admin_user_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "login_name")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String username) {
		this.loginName = username;
	}

	@Column(name = "login_password")
	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}


	@Column(name = "real_name")
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}


	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "update_date")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public JSONObject toJSONObject() {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("id", CommonSugar.getTypedDefault(this.getId(), 0L));
		jsonObject.put("loginName", CommonSugar.getTypedDefault(this.getLoginName(), ""));
		jsonObject.put("loginPassword", CommonSugar.getTypedDefault(this.getLoginPassword(), ""));
		jsonObject.put("realName", CommonSugar.getTypedDefault(this.getRealName(), ""));
		jsonObject.put("email", CommonSugar.getTypedDefault(this.getEmail(), ""));
		jsonObject.put("createDate", CommonSugar.getTypedDefault(DateTimeUtils.formatDateToString(this.getCreateDate()), ""));
		jsonObject.put("updateDate", CommonSugar.getTypedDefault(DateTimeUtils.formatDateToString(this.getUpdateDate()), ""));

		return jsonObject;
	}
}
