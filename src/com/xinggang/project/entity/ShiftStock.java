package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 过户总订单类
 * 
 * @author Administrator
 * 
 */
public class ShiftStock implements java.io.Serializable {

	private static final long serialVersionUID = -1753698147354403983L;

	private String sstockId;// 过户订单编号
	private Client clientBySstockClientId;// 对应的货主
	private Client clientBySstockShiftToFirm;// 对应的转入单位
	private String sstockClientNumber;// 客户的过户单号（由客户填入的单号）
	private String sstockReateTime;// 过户日期
	private String sstockTel;// 转入单位联系电话
	private String sstockOrderStatus;// 订单状态（计划过户，审核通过，审核未通过，正在收费，收费完成）
	private String sstockDefinedOne;// 转库类型
	private String sstockDefinedTwo;// 预留字段二
	private String sstockRemark;// 备注
	private Set<Object> shiftStockSeeds = new HashSet<Object>(0);// 对应的过户子订单类
	private String sstockFaQiRen;//过户发起人

	public ShiftStock() {
	}

	public ShiftStock(Client clientBySstockClientId,
			Client clientBySstockShiftToFirm, String sstockClientNumber,
			String sstockReateTime, String sstockTel, String sstockOrderStatus,
			String sstockDefinedOne, String sstockDefinedTwo,
			String sstockRemark, Set<Object> shiftStockSeeds) {
		this.clientBySstockClientId = clientBySstockClientId;
		this.clientBySstockShiftToFirm = clientBySstockShiftToFirm;
		this.sstockClientNumber = sstockClientNumber;
		this.sstockReateTime = sstockReateTime;
		this.sstockTel = sstockTel;
		this.sstockOrderStatus = sstockOrderStatus;
		this.sstockDefinedOne = sstockDefinedOne;
		this.sstockDefinedTwo = sstockDefinedTwo;
		this.sstockRemark = sstockRemark;
		this.shiftStockSeeds = shiftStockSeeds;
	}


	public String getSstockFaQiRen() {
		return sstockFaQiRen;
	}

	public void setSstockFaQiRen(String sstockFaQiRen) {
		this.sstockFaQiRen = sstockFaQiRen;
	}

	public String getSstockId() {
		return this.sstockId;
	}

	public void setSstockId(String sstockId) {
		this.sstockId = sstockId;
	}

	public Client getClientBySstockClientId() {
		return this.clientBySstockClientId;
	}

	public void setClientBySstockClientId(Client clientBySstockClientId) {
		this.clientBySstockClientId = clientBySstockClientId;
	}

	public Client getClientBySstockShiftToFirm() {
		return this.clientBySstockShiftToFirm;
	}

	public void setClientBySstockShiftToFirm(Client clientBySstockShiftToFirm) {
		this.clientBySstockShiftToFirm = clientBySstockShiftToFirm;
	}

	public String getSstockClientNumber() {
		return this.sstockClientNumber;
	}

	public void setSstockClientNumber(String sstockClientNumber) {
		this.sstockClientNumber = sstockClientNumber;
	}

	public String getSstockReateTime() {
		return this.sstockReateTime;
	}

	public void setSstockReateTime(String sstockReateTime) {
		this.sstockReateTime = sstockReateTime;
	}

	public String getSstockTel() {
		return this.sstockTel;
	}

	public void setSstockTel(String sstockTel) {
		this.sstockTel = sstockTel;
	}

	public String getSstockOrderStatus() {
		return this.sstockOrderStatus;
	}

	public void setSstockOrderStatus(String sstockOrderStatus) {
		this.sstockOrderStatus = sstockOrderStatus;
	}

	public String getSstockDefinedOne() {
		return this.sstockDefinedOne;
	}

	public void setSstockDefinedOne(String sstockDefinedOne) {
		this.sstockDefinedOne = sstockDefinedOne;
	}

	public String getSstockDefinedTwo() {
		return this.sstockDefinedTwo;
	}

	public void setSstockDefinedTwo(String sstockDefinedTwo) {
		this.sstockDefinedTwo = sstockDefinedTwo;
	}

	public String getSstockRemark() {
		return this.sstockRemark;
	}

	public void setSstockRemark(String sstockRemark) {
		this.sstockRemark = sstockRemark;
	}

	public Set<Object> getShiftStockSeeds() {
		return this.shiftStockSeeds;
	}

	public void setShiftStockSeeds(Set<Object> shiftStockSeeds) {
		this.shiftStockSeeds = shiftStockSeeds;
	}

}