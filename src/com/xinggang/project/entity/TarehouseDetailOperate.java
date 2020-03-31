package com.xinggang.project.entity;

/**
 * 货物批次操作类
 * 
 * @author Administrator
 * 
 */
public class TarehouseDetailOperate implements java.io.Serializable {

	private static final long serialVersionUID = 7547577278338702251L;
	private String tdoperateId;// 操作编号
	private Client clientByTdoperateClientId;// 对应的货主，客户类
	private Client clientByTdoperateShiftToFirm;// 对应的转入单位,客户类
	private TarehouseDetail tarehouseDetail;// 对应的批次类编号
	private String tdoperateType;// 操作类型（出/转）
	private String tdoperateTime;// 日期
	private Double tdoperateWeight;// 重量
	private Double tdoperateEweight;// 已出重量
	private String tdoperateDefinedOne;// 预留字段一
	private String tdoperateDefinedTwo;// 预留字段二
	private String tdoperateRemark;// 备注

	public TarehouseDetailOperate() {
	}

	public TarehouseDetailOperate(Client clientByTdoperateClientId,
			Client clientByTdoperateShiftToFirm,
			TarehouseDetail tarehouseDetail, String tdoperateType,
			String tdoperateTime, Double tdoperateWeight,
			Double tdoperateEweight, String tdoperateDefinedOne,
			String tdoperateDefinedTwo, String tdoperateRemark) {
		this.clientByTdoperateClientId = clientByTdoperateClientId;
		this.clientByTdoperateShiftToFirm = clientByTdoperateShiftToFirm;
		this.tarehouseDetail = tarehouseDetail;
		this.tdoperateType = tdoperateType;
		this.tdoperateTime = tdoperateTime;
		this.tdoperateWeight = tdoperateWeight;
		this.tdoperateEweight = tdoperateEweight;
		this.tdoperateDefinedOne = tdoperateDefinedOne;
		this.tdoperateDefinedTwo = tdoperateDefinedTwo;
		this.tdoperateRemark = tdoperateRemark;
	}

	public String getTdoperateId() {
		return this.tdoperateId;
	}

	public void setTdoperateId(String tdoperateId) {
		this.tdoperateId = tdoperateId;
	}

	public Client getClientByTdoperateClientId() {
		return this.clientByTdoperateClientId;
	}

	public void setClientByTdoperateClientId(Client clientByTdoperateClientId) {
		this.clientByTdoperateClientId = clientByTdoperateClientId;
	}

	public Client getClientByTdoperateShiftToFirm() {
		return this.clientByTdoperateShiftToFirm;
	}

	public void setClientByTdoperateShiftToFirm(
			Client clientByTdoperateShiftToFirm) {
		this.clientByTdoperateShiftToFirm = clientByTdoperateShiftToFirm;
	}

	public TarehouseDetail getTarehouseDetail() {
		return this.tarehouseDetail;
	}

	public void setTarehouseDetail(TarehouseDetail tarehouseDetail) {
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