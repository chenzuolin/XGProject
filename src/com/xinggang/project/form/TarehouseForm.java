package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 库位表单
 */
public class TarehouseForm extends ActionForm {

	private static final long serialVersionUID = -4808830432996378728L;
	private Integer tarehouseId;// 库位编号
	private Integer bursary;// 对应的库房类
	private String tarehouseName;// 库位名称
	private Double tarehouseMaxWeight;// 库位最大重量
	private Double tarehouseMaxNumber;// 库位最大件数
	private String tarehouseDefinedOne;// 预留字段一
	private String tarehouseDefinedTwo;// 预留字段二
	private String tarehouseRemark;// 备注

	public Integer getTarehouseId() {
		return this.tarehouseId;
	}

	public void setTarehouseId(Integer tarehouseId) {
		this.tarehouseId = tarehouseId;
	}

	public Integer getBursary() {
		return this.bursary;
	}

	public void setBursary(Integer bursary) {
		this.bursary = bursary;
	}

	public String getTarehouseName() {
		return this.tarehouseName;
	}

	public void setTarehouseName(String tarehouseName) {
		this.tarehouseName = tarehouseName;
	}

	public Double getTarehouseMaxWeight() {
		return this.tarehouseMaxWeight;
	}

	public void setTarehouseMaxWeight(Double tarehouseMaxWeight) {
		this.tarehouseMaxWeight = tarehouseMaxWeight;
	}

	public Double getTarehouseMaxNumber() {
		return this.tarehouseMaxNumber;
	}

	public void setTarehouseMaxNumber(Double tarehouseMaxNumber) {
		this.tarehouseMaxNumber = tarehouseMaxNumber;
	}

	public String getTarehouseDefinedOne() {
		return this.tarehouseDefinedOne;
	}

	public void setTarehouseDefinedOne(String tarehouseDefinedOne) {
		this.tarehouseDefinedOne = tarehouseDefinedOne;
	}

	public String getTarehouseDefinedTwo() {
		return this.tarehouseDefinedTwo;
	}

	public void setTarehouseDefinedTwo(String tarehouseDefinedTwo) {
		this.tarehouseDefinedTwo = tarehouseDefinedTwo;
	}

	public String getTarehouseRemark() {
		return this.tarehouseRemark;
	}

	public void setTarehouseRemark(String tarehouseRemark) {
		this.tarehouseRemark = tarehouseRemark;
	}

}