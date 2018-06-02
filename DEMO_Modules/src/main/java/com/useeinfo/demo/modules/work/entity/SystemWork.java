package com.useeinfo.demo.modules.work.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.useeinfo.demo.modules.security.entity.SystemAuthority;
import com.useeinfo.framework.extend.entity.DataEntity;
import com.useeinfo.framework.sugar.tools.CommonSugar;
import net.sf.json.JSONObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import java.text.SimpleDateFormat;
import 	java.sql.Timestamp;

import javax.persistence.*;
import java.util.Date;
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
	private String antiskidMode; 			 //防滑参数
	private String drainageMode;			//防水参数
	private String antiThermalRadiation;	//防热辐射
	private String bottomWaterCurtain;		//底部水幕
	private String breathingEquipment;		//呼吸装置

	private Double bucketRatedLoad;			//额定荷载
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;			//创建时间


//	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//	java.util.Date date=new java.util.Date();
//	String str=sdf.format(createTime);

//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	String dateStr = sdf.format(createTime);


	@Override
	public JSONObject toJSONObject() {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("workId", CommonSugar.getTypedDefault(getWorkId(), 0L));
		jsonObject.put("workName", CommonSugar.getTypedDefault(getWorkName(), ""));
		jsonObject.put("antiskidMode", CommonSugar.getTypedDefault(getAntiskidMode(),""));
		jsonObject.put("drainageMode",CommonSugar.getTypedDefault(getDrainageMode(),""));
		jsonObject.put("antiThermalRadiation",CommonSugar.getTypedDefault(getAntiThermalRadiation(),""));
		jsonObject.put("bottomWaterCurtain",CommonSugar.getTypedDefault(getBottomWaterCurtain(),""));
		jsonObject.put("breathingEquipment",CommonSugar.getTypedDefault(getBreathingEquipment(),""));

		jsonObject.put("bucketRatedLoad",CommonSugar.getTypedDefault(getBucketRatedLoad(),null));  				/****************这个地方还有问题*****************/
		jsonObject.put("createTime",CommonSugar.getTypedDefault(getCreateTime(),null));							 /****************这个地方还有问题*****************/
//		jsonObject.put("workDescription", CommonSugar.getTypedDefault(getWorkDescription(), ""));
//		jsonObject.put("systemManager", isSystemManager());
//		jsonObject.put("enabled", isEnabled());

		return jsonObject;
	}

	public SystemWork() {
	}
//
	public SystemWork(Long workId ,String workName,String antiskidMode,  String drainageMode, String antiThermalRadiation,String bottomWaterCurtain,
					  String breathingEquipment,Double bucketRatedLoad,Timestamp createTime) {
		this.workId = workId;
		this.workName = workName;

		this.antiskidMode = antiskidMode;
		this.drainageMode = drainageMode;
		this.antiThermalRadiation =antiThermalRadiation;
		this.bottomWaterCurtain = bottomWaterCurtain;
		this.breathingEquipment = breathingEquipment;
		this.bucketRatedLoad = bucketRatedLoad;
		this.createTime = createTime;

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


	/****自动生成**/
	public String getAntiskidMode() {
		return antiskidMode;
	}

	public void setAntiskidMode(String antiskidMode) {
		this.antiskidMode = antiskidMode;
	}

	public String getDrainageMode() {
		return drainageMode;
	}

	public void setDrainageMode(String drainageMode) {
		this.drainageMode = drainageMode;
	}

	public String getAntiThermalRadiation() {
		return antiThermalRadiation;
	}

	public void setAntiThermalRadiation(String antiThermalRadiation) {
		this.antiThermalRadiation = antiThermalRadiation;
	}

	public String getBottomWaterCurtain() {
		return bottomWaterCurtain;
	}

	public void setBottomWaterCurtain(String bottomWaterCurtain) {
		this.bottomWaterCurtain = bottomWaterCurtain;
	}

	public String getBreathingEquipment() {
		return breathingEquipment;
	}

	public void setBreathingEquipment(String breathingEquipment) {
		this.breathingEquipment = breathingEquipment;
	}

	public Double getBucketRatedLoad() {
		return bucketRatedLoad;
	}

	public void setBucketRatedLoad(Double bucketRatedLoad) {
		this.bucketRatedLoad = bucketRatedLoad;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
