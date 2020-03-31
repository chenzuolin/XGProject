package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 货物批次操作表单
 * 
 * @author Administrator
 * 
 */
public class TarehouseDetailOperateForm extends ActionForm {

	private static final long serialVersionUID = 7547577278338702251L;
	private String tdoperateId;// 操作编号
	private Integer clientByTdoperateClientId;// 对应的货主，客户类
	private Integer clientByTdoperateShiftToFirm;// 对应的转入单位,客户类
	private String tarehouseDetail;// 对应的批次类编号
	private String tdoperateType;// 操作类型（出/转）
	private String tdoperateTime;// 日期
	private Double tdoperateWeight;// 重量
	private Double tdoperateEweight;// 已出重量
	private String tdoperateDefinedOne;// 预留字段一
	private String tdoperateDefinedTwo;// 预留字段二
	private String tdoperateRemark;// 备注

	public String getTdoperateId() {
		return this.tdoperateId;
	}

	public void setTdoperateId(String tdoperateId) {
		this.tdoperateId = tdoperateId;
	}

	public Integer getClientByTdoperateClientId() {
		return this.clientByTdoperateClientId;
	}

	public void setClientByTdoperateClientId(Integer clientByTdoperateClientId) {
		this.clientByTdoperateClientId = clientByTdoperateClientId;
	}

	public Integer getClientByTdoperateShiftToFirm() {
		return this.clientByTdoperateShiftToFirm;
	}

	public void setClientByTdoperateShiftToFirm(
			Integer clientByTdoperateShiftToFirm) {
		this.clientByTdoperateShiftToFirm = clientByTdoperateShiftToFirm;
	}

	public String getTarehouseDetail() {
		return this.tarehouseDetail;
	}

	public void setTarehouseDetail(String tarehouseDetail) {
		this.tarehouseDetail = tarehouseDetail;
	}

	public String getTdoperateType() {
		return this.tdoperateType;
	}

	public void setTdoperateType(String tdoperateType) {
		this.tdoperateType = tdoperateType;
	}

	public String getTdoperateTime() {
		return this.tdoperateTime;
	}

	public void setTdoperateTime(String tdoperateTime) {
		this.tdoperateTime = tdoperateTime;
	}

	public Double getTdoperateWeight() {
		return this.tdoperateWeight;
	}

	public void setTdoperateWeight(Double tdoperateWeight) {
		this.tdoperateWeight = tdoperateWeight;
	}

	public Double getTdoperateEweight() {
		return this.tdoperateEweight;
	}

	public void setTdoperateEweight(Double tdoperateEweight) {
		this.tdoperateEweight = tdoperateEweight;
	}

	public String getTdoperateDefinedOne() {
		return this.tdoperateDefinedOne;
	}

	public void setTdoperateDefinedOne(String tdoperateDefinedOne) {
		this.tdoperateDefinedOne = tdoperateDefinedOne;
	}

	public String getTdoperateDefinedTwo() {
		return this.tdoperateDefinedTwo;
	}

	public void setTdoperateDefinedTwo(String tdoperateDefinedTwo) {
		this.tdoperateDefinedTwo = tdoperateDefinedTwo;
	}

	public String getTdoperateRemark() {
		return this.tdoperateRemark;
	}

	public void setTdoperateRemark(String tdoperateRemark) {
		this.tdoperateRemark = tdoperateRemark;
	}

}