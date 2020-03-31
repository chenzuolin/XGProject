package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 班组类
 * 
 * @author Administrator
 * 
 */
public class ClassT implements java.io.Serializable {

	private static final long serialVersionUID = -3954559230791471402L;
	private Integer classId;// 班组编号
	private Section section;// 部门，对应部门类
	private String className;// 班组名称
	private String classHuman;// 负责人
	private String classDefinedOne;// 预留字段一
	private String classDefinedTwo;// 预留字段二
	private String classRemark;// 备注
	private Set<Object> interiorUsers = new HashSet<Object>(0);// 对应内部人员类
	private Set<Object> working = new HashSet<Object>(0);// 对应的作业人员

	public Set<Object> getWorking() {
		return working;
	}

	public void setWorking(Set<Object> working) {
		this.working = working;
	}

	public ClassT() {
	}

	public ClassT(Section section, String className, String classHuman,
			String classDefinedOne, String classDefinedTwo, String classRemark,
			Set<Object> interiorUsers) {
		this.section = section;
		this.className = className;
		this.classHuman = classHuman;
		this.classDefinedOne = classDefinedOne;
		this.classDefinedTwo = classDefinedTwo;
		this.classRemark = classRemark;
		this.interiorUsers = interiorUsers;
	}

	// Property accessors

	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassHuman() {
		return this.classHuman;
	}

	public void setClassHuman(String classHuman) {
		this.classHuman = classHuman;
	}

	public String getClassDefinedOne() {
		return this.classDefinedOne;
	}

	public void setClassDefinedOne(String classDefinedOne) {
		this.classDefinedOne = classDefinedOne;
	}

	public String getClassDefinedTwo() {
		return this.classDefinedTwo;
	}

	public void setClassDefinedTwo(String classDefinedTwo) {
		this.classDefinedTwo = classDefinedTwo;
	}

	public String getClassRemark() {
		return this.classRemark;
	}

	public void setClassRemark(String classRemark) {
		this.classRemark = classRemark;
	}

	public Set<Object> getInteriorUsers() {
		return this.interiorUsers;
	}

	public void setInteriorUsers(Set<Object> interiorUsers) {
		this.interiorUsers = interiorUsers;
	}

}