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
@Table(name = "wms_system_authority")
@GenericGenerator(name = "WMS_SYSTEM_AUTHORITY_GEN", strategy = "enhanced-table",
		parameters = {
				@Parameter(name = "table_name", value = "table_generator"),
				@Parameter(name = "value_column_name", value = "next"),
				@Parameter(name = "segment_column_name", value = "segment_name"),
				@Parameter(name = "segment_value", value = "wms_system_authority_id"),
				@Parameter(name = "initial_value", value = "1000"),
				@Parameter(name = "increment_size", value = "10"),
				@Parameter(name = "optimizer", value = "pooled-lo")
		})
public class SystemAuthority extends DataEntity<SystemAuthority> {

	private Long authorityId;
	private String authorityName;
	private String authorityDescription;
	private Set<SystemRole> roleSet;
	private Set<SystemResource> resourceSet;
	private boolean systemManager;
	private boolean enabled;

	@Override
	public JSONObject toJSONObject() {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("authorityId", CommonSugar.getTypedDefault(getAuthorityId(), 0L));
		jsonObject.put("authorityName", CommonSugar.getTypedDefault(getAuthorityName(), ""));
		jsonObject.put("authorityDescription", CommonSugar.getTypedDefault(getAuthorityDescription(), ""));
		jsonObject.put("systemManager", isSystemManager());
		jsonObject.put("enabled", isEnabled());

		return jsonObject;
	}

	public SystemAuthority() {
	}

	public SystemAuthority(String authorityName,
	                       String authorityDescription,
	                       Set<SystemRole> roleSet,
	                       Set<SystemResource> resourceSet,
	                       boolean systemManager,
	                       boolean enabled) {

		this.authorityName = authorityName;
		this.authorityDescription = authorityDescription;
		this.roleSet = roleSet;
		this.resourceSet = resourceSet;
		this.systemManager = systemManager;
		this.enabled = enabled;
	}

	@Id
	@GeneratedValue(generator = "WMS_SYSTEM_AUTHORITY_GEN")
	@Column(name = "authority_id")
	public Long getAuthorityId() {
		return authorityId;
	}

	@Override
	@Transient
	public Long getId() {
		return authorityId;
	}

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}

	@Column(name = "authority_name")
	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	@Column(name = "authority_description")
	public String getAuthorityDescription() {
		return authorityDescription;
	}

	public void setAuthorityDescription(String authorityDescription) {
		this.authorityDescription = authorityDescription;
	}

	@ManyToMany(mappedBy = "authoritySet")
	public Set<SystemRole> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<SystemRole> roleSet) {
		this.roleSet = roleSet;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "wms_system_authority_2_resource", joinColumns = @JoinColumn(name = "authority_id"), inverseJoinColumns = @JoinColumn(name = "resource_id"))
	public Set<SystemResource> getResourceSet() {
		return resourceSet;
	}

	public void setResourceSet(Set<SystemResource> resourceSet) {
		this.resourceSet = resourceSet;
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

	public void addResource(SystemResource systemResource) {
		this.resourceSet.add(systemResource);
	}

	public void removeResource(SystemResource systemResource) {
		this.resourceSet.remove(systemResource);
	}
}
