package com.useeinfo.demo.modules.security.entity;

import com.useeinfo.framework.extend.entity.DataEntity;
import com.useeinfo.framework.sugar.tools.CommonSugar;
import net.sf.json.JSONObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Set;

/**
 * Author: 居泽平  Date: 14-3-31, 上午10:01
 */
@Entity
@Table(name = "wms_system_role")
@GenericGenerator(name = "WMS_SYSTEM_ROLE_GEN", strategy = "enhanced-table",
		parameters = {
				@Parameter(name = "table_name", value = "table_generator"),
				@Parameter(name = "value_column_name", value = "next"),
				@Parameter(name = "segment_column_name", value = "segment_name"),
				@Parameter(name = "segment_value", value = "wms_system_role_id"),
				@Parameter(name = "initial_value", value = "1000"),
				@Parameter(name = "increment_size", value = "10"),
				@Parameter(name = "optimizer", value = "pooled-lo")
		})
public class SystemRole extends DataEntity<SystemRole> {

	private Long roleId;
	private String roleName;
	private String roleDescription;
	private Set<SystemAuthority> authoritySet;
	private boolean systemManager;
	private boolean enabled;

	@Override
	public JSONObject toJSONObject() {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("roleId", CommonSugar.getTypedDefault(getRoleId(), 0L));
		jsonObject.put("roleName", CommonSugar.getTypedDefault(getRoleName(), ""));
		jsonObject.put("roleDescription", CommonSugar.getTypedDefault(getRoleDescription(), ""));
		jsonObject.put("systemManager", isSystemManager());
		jsonObject.put("enabled", isEnabled());

		return jsonObject;
	}

	public SystemRole() {
	}

	public SystemRole(Long roleId) {
		this.roleId = roleId;
	}

	@Id
	@GeneratedValue(generator = "WMS_SYSTEM_ROLE_GEN")
	@Column(name = "role_id")
	public Long getRoleId() {
		return roleId;
	}

	@Override
	@Transient
	public Long getId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name = "role_name")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "role_description")
	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "wms_system_role_2_authority", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
	public Set<SystemAuthority> getAuthoritySet() {
		return authoritySet;
	}

	public void setAuthoritySet(Set<SystemAuthority> authoritySet) {
		this.authoritySet = authoritySet;
	}

	@Column(name = "system_manager")
	public boolean isSystemManager() {
		return systemManager;
	}

	public void setSystemManager(boolean systemManager) {
		this.systemManager = systemManager;
	}

	@Column(name = "enabled")
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void addAuthority(SystemAuthority systemAuthority) {
		this.authoritySet.add(systemAuthority);
	}

	public void removeAuthority(SystemAuthority systemAuthority) {
		this.authoritySet.remove(systemAuthority);
	}
}
