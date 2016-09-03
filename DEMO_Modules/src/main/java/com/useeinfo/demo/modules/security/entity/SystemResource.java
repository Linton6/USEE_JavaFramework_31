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
@Table(name = "wms_system_resource")
@GenericGenerator(name = "WMS_SYSTEM_RESOURCE_GEN", strategy = "enhanced-table",
		parameters = {
				@Parameter(name = "table_name", value = "table_generator"),
				@Parameter(name = "value_column_name", value = "next"),
				@Parameter(name = "segment_column_name", value = "segment_name"),
				@Parameter(name = "segment_value", value = "wms_system_resource_id"),
				@Parameter(name = "initial_value", value = "1000"),
				@Parameter(name = "increment_size", value = "10"),
				@Parameter(name = "optimizer", value = "pooled-lo")
		})
public class SystemResource extends DataEntity<SystemResource> {

	private Long resourceId;
	private String resourceName;
	private String resourceDescription;
	private String resourceType;
	private String resourceString;
	private Set<SystemAuthority> authoritySet;
	private boolean systemManager;
	private boolean enabled;

	@Override
	public JSONObject toJSONObject() {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("resourceId", CommonSugar.getTypedDefault(getResourceId(), 0L));
		jsonObject.put("resourceName", CommonSugar.getTypedDefault(getResourceName(), ""));
		jsonObject.put("resourceDescription", CommonSugar.getTypedDefault(getResourceDescription(), ""));
		jsonObject.put("resourceType", CommonSugar.getTypedDefault(getResourceType(), ""));
		jsonObject.put("resourceString", CommonSugar.getTypedDefault(getResourceString(), ""));
		jsonObject.put("systemManager", isSystemManager());
		jsonObject.put("enabled", isEnabled());

		return jsonObject;
	}

	@Id
	@GeneratedValue(generator = "WMS_SYSTEM_RESOURCE_GEN")
	@Column(name = "resource_id")
	public Long getResourceId() {
		return resourceId;
	}

	@Override
	@Transient
	public Long getId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	@Column(name = "resource_name")
	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Column(name = "resource_description")
	public String getResourceDescription() {
		return resourceDescription;
	}

	public void setResourceDescription(String resourceDescription) {
		this.resourceDescription = resourceDescription;
	}

	@Column(name = "resource_type")
	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Column(name = "resource_string")
	public String getResourceString() {
		return resourceString;
	}

	public void setResourceString(String resourceString) {
		this.resourceString = resourceString;
	}

	@ManyToMany(mappedBy = "resourceSet")
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
}
