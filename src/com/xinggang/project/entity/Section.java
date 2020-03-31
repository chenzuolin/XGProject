package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 部门类
 * 
 * @author Administrator
 * 
 */
public class Section implements java.io.Serializable {

	private static final long serialVersionUID = 7314078524862592869L;
	private Integer sectionId;// 部门编号
	private String sectionName;// 部门名称
	private String sectionHuman;// 部门负责人
	private String sectionDefinedOne;// 预留字段一
	private String sectionDefinedTwo;// 预留字段二
	private String sectionRemark;// 备注
	private Set<Object> classTs = new HashSet<Object>(0);// 对应的班组类
	private Set<Object> interiorUsers = new HashSet<Object>(0);// 对应的内部人员类
	private Set<Object> interiorUserDuties = new HashSet<Object>(0);// 对应的内部人员职责类

	public Section() {
	}

	/** full constructor */
	public Section(String sectionName, String sectionHuman,
			String sectionDefinedOne, String sectionDefinedTwo,
			String sectionRemark, Set<Object> classTs, Set<Object> interiorUsers,
			Set<Object> interiorUserDuties) {
		this.sectionName = sectionName;
		this.sectionHuman = sectionHuman;
		this.sectionDefinedOne = sectionDefinedOne;
		this.sectionDefinedTwo = sectionDefinedTwo;
		this.sectionRemark = sectionRemark;
		this.classTs = classTs;
		this.interiorUsers = interiorUsers;
		this.interiorUserDuties = interiorUserDuties;
	}

	// Property accessors

	public Integer getSectionId() {
		return this.sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return this.sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getSectionHuman() {
		return this.sectionHuman;
	}

	public void setSectionHuman(String sectionHuman) {
		this.sectionHuman = sectionHuman;
	}

	public String getSectionDefinedOne() {
		return this.sectionDefinedOne;
	}

	public void setSectionDefinedOne(String sectionDefinedOne) {
		this.sectionDefinedOne = sectionDefinedOne;
	}

	public String getSectionDefinedTwo() {
		return this.sectionDefinedTwo;
	}

	public void setSectionDefinedTwo(String sectionDefinedTwo) {
		this.sectionDefinedTwo = sectionDefinedTwo;
	}

	public String getSectionRemark() {
		return this.sectionRemark;
	}

	public void setSectionRemark(String sectionRemark) {
		this.sectionRemark = sectionRemark;
	}

	public Set<Object> getClassTs() {
		return this.classTs;
	}

	public void setClassTs(Set<Object> classTs) {
		this.classTs = classTs;
	}

	public Set<Object> getInteriorUsers() {
		return this.interiorUsers;
	}

	public void setInteriorUsers(Set<Object> interiorUsers) {
		this.interiorUsers = interiorUsers;
	}

	public Set<Object> getInteriorUserDuties() {
		return this.interiorUserDuties;
	}

	public void setInteriorUserDuties(Set<Object> interiorUserDuties) {
		this.interiorUserDuties = interiorUserDuties;
	}

}