package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 部门表单
 * 
 * @author Administrator
 * 
 */
public class SectionForm extends ActionForm {

	private static final long serialVersionUID = 7314078524862592869L;
	private Integer sectionId;// 部门编号
	private String sectionName;// 部门名称
	private String sectionHuman;// 部门负责人
	private String sectionDefinedOne;// 预留字段一
	private String sectionDefinedTwo;// 预留字段二
	private String sectionRemark;// 备注

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

}