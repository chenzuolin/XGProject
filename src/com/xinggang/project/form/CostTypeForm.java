package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 费用类型form
 * 
 * @author Administrator
 * 
 */
public class CostTypeForm extends ActionForm {
	private static final long serialVersionUID = 6319722363334311119L;
	private Integer ctypeId; // 费用类型编号
	private String ctypeName;// 费用类型名称
	private Double ctypeCost;// 费用
	private String ctypeDefinedOne;// 预留字段一
	private String ctypeDefinedTwo;// 预留字段二
	private String ctypeDefinedThree;// 预留字段三
	private String ctypeDefinedFour;// 预留字段四
	private String ctypeRemark;// 备注

	public Integer getCtypeId() {
		return this.ctypeId;
	}

	public void setCtypeId(Integer ctypeId) {
		this.ctypeId = ctypeId;
	}

	public String getCtypeName() {
		return this.ctypeName;
	}

	public void setCtypeName(String ctypeName) {
		this.ctypeName = ctypeName;
	}

	public Double getCtypeCost() {
		return this.ctypeCost;
	}

	public void setCtypeCost(Double ctypeCost) {
		this.ctypeCost = ctypeCost;
	}

	public String getCtypeDefinedOne() {
		return this.ctypeDefinedOne;
	}

	public void setCtypeDefinedOne(String ctypeDefinedOne) {
		this.ctypeDefinedOne = ctypeDefinedOne;
	}

	public String getCtypeDefinedTwo() {
		return this.ctypeDefinedTwo;
	}

	public void setCtypeDefinedTwo(String ctypeDefinedTwo) {
		this.ctypeDefinedTwo = ctypeDefinedTwo;
	}

	public String getCtypeDefinedThree() {
		return this.ctypeDefinedThree;
	}

	public void setCtypeDefinedThree(String ctypeDefinedThree) {
		this.ctypeDefinedThree = ctypeDefinedThree;
	}

	public String getCtypeDefinedFour() {
		return this.ctypeDefinedFour;
	}

	public void setCtypeDefinedFour(String ctypeDefinedFour) {
		this.ctypeDefinedFour = ctypeDefinedFour;
	}

	public String getCtypeRemark() {
		return this.ctypeRemark;
	}

	public void setCtypeRemark(String ctypeRemark) {
		this.ctypeRemark = ctypeRemark;
	}

}
