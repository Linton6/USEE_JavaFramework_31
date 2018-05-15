package com.useeinfo.demo.modules.work.entity;

import com.useeinfo.demo.modules.security.entity.SystemAuthority;
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
@Table(name = "wms_system_work")
@GenericGenerator(name = "WMS_SYSTEM_WORK_GEN", strategy = "enhanced-table",
		parameters = {
				@Parameter(name = "table_name", value = "table_generator"),
				@Parameter(name = "value_column_name", value = "next"),
				@Parameter(name = "segment_column_name", value = "segment_name"),
				@Parameter(name = "segment_value", value = "wms_system_work_id"),
				@Parameter(name = "initial_value", value = "1000"),
				@Parameter(name = "increment_size", value = "10"),
				@Parameter(name = "optimizer", value = "pooled-lo")
		})
public class SystemWork extends DataEntity<SystemWork> {

	private Long workId;
	private String workName;
//	private String workDescription;
	private Set<SystemAuthority> authoritySet;
//	private boolean systemManager;
//	private boolean enabled;

	@Override
	public JSONObject toJSONObject() {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("workId", CommonSugar.getTypedDefault(getWorkId(), 0L));
		jsonObject.put("workName", CommonSugar.getTypedDefault(getWorkName(), ""));
//		jsonObject.put("workDescription", CommonSugar.getTypedDefault(getWorkDescription(), ""));
//		jsonObject.put("systemManager", isSystemManager());
//		jsonObject.put("enabled", isEnabled());

		return jsonObject;
	}

	public SystemWork() {
	}

	public SystemWork(Long workId ,String workName) {
		this.workId = workId;
		this.workName = workName;

	}

	@Id
	@GeneratedValue(generator = "WMS_SYSTEM_WORK_GEN")
	@Column(name = "work_id")
	public Long getWorkId() {
		return workId;
	}

	@Override
	@Transient
	public Long getId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	@Column(name = "work_name")
	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

//	@Column(name = "work_description")
//	public String getWorkDescription() {
//		return workDescription;
//	}

//	public void setRoleDescription(String roleDescription) {
//		this.roleDescription = roleDescription;
//	}

//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "wms_system_role_2_authority", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
//	public Set<SystemAuthority> getAuthoritySet() {
//		return authoritySet;
//	}

//	public void setAuthoritySet(Set<SystemAuthority> authoritySet) {
//		this.authoritySet = authoritySet;
//	}

//	@Column(name = "system_manager")
//	public boolean isSystemManager() {
//		return systemManager;
//	}

//	public void setSystemManager(boolean systemManager) {
//		this.systemManager = systemManager;
//	}

//	@Column(name = "enabled")
//	public boolean isEnabled() {
//		return enabled;
//	}

//	public void setEnabled(boolean enabled) {
//		this.enabled = enabled;
//	}

//	public void addAuthority(SystemAuthority systemAuthority) {
//		this.authoritySet.add(systemAuthority);
//	}

//	public void removeAuthority(SystemAuthority systemAuthority) {
//		this.authoritySet.remove(systemAuthority);
//	}
}
