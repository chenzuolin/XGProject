package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 
 * 权限表单
 */

public class PowersForm extends ActionForm {

	private static final long serialVersionUID = -2812108276170816672L;
	private Integer powerId;// 权限编号
	private Integer interiorUserDuty;// 对应的职责类
	private Integer functions;// 对应的功能类
	private String powerDefinedOne;// 自定义字段1
	private String powerDefinedTwo;// 自定义字段2
	private String powerRemark;// 备注
	
	private Integer[] functionId;
	
	

	public Integer[] getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Integer[] functionId) {
		this.functionId = functionId;
	}

	public Integer getPowerId() {
		return this.powerId;
	}

	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}

	public Integer getInteriorUserDuty() {
		return this.interiorUserDuty;
	}

	public void setInteriorUserDuty(Integer interiorUserDuty) {
		this.interiorUserDuty = interiorUserDuty;
	}

	public Integer getFunctions() {
		return this.functions;
	}

	public void setFunctions(Integer functions) {
		this.functions = functions;
	}

	public String getPowerDefinedOne() {
		return this.powerDefinedOne;
	}

	public void setPowerDefinedOne(String powerDefinedOne) {
		this.powerDefinedOne = powerDefinedOne;
	}

	public String getPowerDefinedTwo() {
		return this.powerDefinedTwo;
	}

	public void setPowerDefinedTwo(String powerDefinedTwo) {
		this.powerDefinedTwo = powerDefinedTwo;
	}

	public String getPowerRemark() {
		return this.powerRemark;
	}

	public void setPowerRemark(String powerRemark) {
		this.powerRemark = powerRemark;
	}

}