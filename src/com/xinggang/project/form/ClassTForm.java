package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 班组表单
 * 
 * @author Administrator
 * 
 */
public class ClassTForm extends ActionForm {

	private static final long serialVersionUID = -3954559230791471402L;
	private Integer classId;// 班组编号
	private Integer section;// 部门，对应部门类
	private String className;// 班组名称
	private String classHuman;// 负责人
	private String classDefinedOne;// 预留字段一
	private String classDefinedTwo;// 预留字段二
	private String classRemark;// 备注

	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getSection() {
		return this.section;
	}

	public void setSection(Integer section) {
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

}