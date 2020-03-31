package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 
 * 使用系统的公司内部人员职责表单
 */

public class InteriorUserDutyForm extends ActionForm {

	private static final long serialVersionUID = -7948945439761011627L;
	private Integer interiorUserDutyId;// 职责编号
	private Integer section;// 对应类部门
	private String interiorUserDutyName;// 职责名称
	private String interiorUserDutyRemark;//备注
	private String interiorUserDutyDefinedOne;//自定义字段1
	private String interiorUserDutyDefinedTwo;//自定义字段2
	
	//权限
	private Integer powerId;// 权限编号
	private Integer interiorUserDuty;// 对应的职责类
	private Integer functions;// 对应的功能类
	private String powerRemark;// 备注
	private Integer[] functionId;
	
	public Integer getPowerId() {
		return powerId;
	}

	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}

	public Integer getInteriorUserDuty() {
		return interiorUserDuty;
	}

	public void setInteriorUserDuty(Integer interiorUserDuty) {
		this.interiorUserDuty = interiorUserDuty;
	}

	public Integer getFunctions() {
		return functions;
	}

	public void setFunctions(Integer functions) {
		this.functions = functions;
	}

	public String getPowerRemark() {
		return powerRemark;
	}

	public void setPowerRemark(String powerRemark) {
		this.powerRemark = powerRemark;
	}

	public Integer[] getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Integer[] functionId) {
		this.functionId = functionId;
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

	public Integer getInteriorUserDutyId() {
		return this.interiorUserDutyId;
	}

	public void setInteriorUserDutyId(Integer interiorUserDutyId) {
		this.interiorUserDutyId = interiorUserDutyId;
	}
	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
	}

	public String getInteriorUserDutyName() {
		return this.interiorUserDutyName;
	}

	public void setInteriorUserDutyName(String interiorUserDutyName) {
		this.interiorUserDutyName = interiorUserDutyName;
	}

}