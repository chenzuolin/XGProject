package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 库房表单
 * 
 * @author Administrator
 * 
 */
public class BursaryForm extends ActionForm {

	private static final long serialVersionUID = -3570027573397315902L;
	private Integer bursaryId;// 库房编号
	private String bursaryName;// 库房名称
	private Double bursaryMaxWeight;// 库房最大重量
	private String bursaryDefinedOne;// 预留字段一
	private String bursaryDefinedTwo;// 预留字段二
	private String bursaryRemark;// 备注

	public Integer getBursaryId() {
		return this.bursaryId;
	}

	public void setBursaryId(Integer bursaryId) {
		this.bursaryId = bursaryId;
	}

	public String getBursaryName() {
		return this.bursaryName;
	}

	public void setBursaryName(String bursaryName) {
		this.bursaryName = bursaryName;
	}

	public Double getBursaryMaxWeight() {
		return this.bursaryMaxWeight;
	}

	public void setBursaryMaxWeight(Double bursaryMaxWeight) {
		this.bursaryMaxWeight = bursaryMaxWeight;
	}

	public String getBursaryDefinedOne() {
		return this.bursaryDefinedOne;
	}

	public void setBursaryDefinedOne(String bursaryDefinedOne) {
		this.bursaryDefinedOne = bursaryDefinedOne;
	}

	public String getBursaryDefinedTwo() {
		return this.bursaryDefinedTwo;
	}

	public void setBursaryDefinedTwo(String bursaryDefinedTwo) {
		this.bursaryDefinedTwo = bursaryDefinedTwo;
	}

	public String getBursaryRemark() {
		return this.bursaryRemark;
	}

	public void setBursaryRemark(String bursaryRemark) {
		this.bursaryRemark = bursaryRemark;
	}

}