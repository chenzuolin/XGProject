package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * InteriorUserDuty entity. @author MyEclipse Persistence Tools
 * 使用系统的公司内部人员职责类
 */

public class InteriorUserDuty implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7948945439761011627L;
	private Integer interiorUserDutyId;//职责编号
	private Section section;//对应类部门
	private String interiorUserDutyName;//职责名称
	private String interiorUserDutyRemark;//备注
	private String interiorUserDutyDefinedOne;//自定义字段1
	private String interiorUserDutyDefinedTwo;//自定义字段2
	
	private Set<Object> interiorUsers = new HashSet<Object>(0);//对应类使用系统的公司内部人员
	private Set<Object> powerses = new HashSet<Object>(0);//对应类权限

	// Constructors

	/** default constructor */
	public InteriorUserDuty() {
	}

	/** full constructor */
	public InteriorUserDuty(Section section, String interiorUserDutyName,
			Set<Object> interiorUsers, Set<Object> powerses) {
		this.section = section;
		this.interiorUserDutyName = interiorUserDutyName;
		this.interiorUsers = interiorUsers;
		this.powerses = powerses;
	}

	// Property accessors

	
	
	public Integer getInteriorUserDutyId() {
		return this.interiorUserDutyId;
	}

	public String getInteriorUserDutyRemark() {
		return interiorUserDutyRemark;
	}

	public void setInteriorUserDutyRemark(String interiorUserDutyRemark) {
		this.interiorUserDutyRemark = interiorUserDutyRemark;
	}

	public String getInteriorUserDutyDefinedOne() {
		return interiorUserDutyDefinedOne;
	}

	public void setInteriorUserDutyDefinedOne(String interiorUserDutyDefinedOne) {
		this.interiorUserDutyDefinedOne = interiorUserDutyDefinedOne;
	}

	public String getInteriorUserDutyDefinedTwo() {
		return interiorUserDutyDefinedTwo;
	}

	public void setInteriorUserDutyDefinedTwo(String interiorUserDutyDefinedTwo) {
		this.interiorUserDutyDefinedTwo = interiorUserDutyDefinedTwo;
	}

	public void setInteriorUserDutyId(Integer interiorUserDutyId) {
		this.interiorUserDutyId = interiorUserDutyId;
	}

	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public String getInteriorUserDutyName() {
		return this.interiorUserDutyName;
	}

	public void setInteriorUserDutyName(String interiorUserDutyName) {
		this.interiorUserDutyName = interiorUserDutyName;
	}

	public Set<Object> getInteriorUsers() {
		return this.interiorUsers;
	}

	public void setInteriorUsers(Set<Object> interiorUsers) {
		this.interiorUsers = interiorUsers;
	}

	public Set<Object> getPowerses() {
		return this.powerses;
	}

	public void setPowerses(Set<Object> powerses) {
		this.powerses = powerses;
	}

}