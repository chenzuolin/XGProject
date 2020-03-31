package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 过户总订单表单
 * 
 * @author Administrator
 * 
 */
public class ShiftStockForm extends ActionForm {

	private static final long serialVersionUID = -1753698147354403983L;

	private String sstockId;// 过户订单编号
	private Integer clientBySstockClientId;// 对应的货主
	private Integer clientBySstockShiftToFirm;// 对应的转入单位
	private String sstockClientNumber;// 客户的过户单号（由客户填入的单号）
	private String sstockReateTime;// 过户日期
	private String sstockTel;// 转入单位联系电话
	private String sstockOrderStatus;// 订单状态（计划过户，审核通过，审核未通过，正在收费，收费完成）
	private String sstockDefinedOne;// 预留字段一
	private String sstockDefinedTwo;// 预留字段二
	private String sstockRemark;// 备注

	private Integer[] goodss;// 对应的货物，以数组的形式实现
	private double[] weight;// 对应的重量，以数组的形式实现
	private String[] remark; // 对应的备注，以数组的形式实现
	private String firm;//手机app转入单位
	
	
	
	//app手机版所需字段
	private Integer goodsPropertyId;// 货物属性(调用属性ID)
	private Integer goodsCategoryId;// 货物品类(调用品类ID)
	private Integer goodsStandardId;//规格
	private Integer goodsQualityId;// 材质
	private Integer goodsYieldlyId;// 货物产地(调用产地ID)
	private String goodsName;// 货物名称
	private Double shiftShouldWeights;//应入重量
		
	
	public Double getShiftShouldWeights() {
		return shiftShouldWeights;
	}

	public void setShiftShouldWeights(Double shiftShouldWeights) {
		this.shiftShouldWeights = shiftShouldWeights;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getGoodsPropertyId() {
		return goodsPropertyId;
	}

	public void setGoodsPropertyId(Integer goodsPropertyId) {
		this.goodsPropertyId = goodsPropertyId;
	}

	public Integer getGoodsCategoryId() {
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(Integer goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	public Integer getGoodsStandardId() {
		return goodsStandardId;
	}

	public void setGoodsStandardId(Integer goodsStandardId) {
		this.goodsStandardId = goodsStandardId;
	}

	public Integer getGoodsQualityId() {
		return goodsQualityId;
	}

	public void setGoodsQualityId(Integer goodsQualityId) {
		this.goodsQualityId = goodsQualityId;
	}

	public Integer getGoodsYieldlyId() {
		return goodsYieldlyId;
	}

	public void setGoodsYieldlyId(Integer goodsYieldlyId) {
		this.goodsYieldlyId = goodsYieldlyId;
	}

	public String getFirm() {
		return firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}

	public String[] getRemark() {
		return remark;
	}

	public void setRemark(String[] remark) {
		this.remark = remark;
	}

	public Integer[] getGoodss() {
		return goodss;
	}

	public void setGoodss(Integer[] goodss) {
		this.goodss = goodss;
	}

	public double[] getWeight() {
		return weight;
	}

	public void setWeight(double[] weight) {
		this.weight = weight;
	}

	public String getSstockId() {
		return this.sstockId;
	}

	public void setSstockId(String sstockId) {
		this.sstockId = sstockId;
	}

	public Integer getClientBySstockClientId() {
		return this.clientBySstockClientId;
	}

	public void setClientBySstockClientId(Integer clientBySstockClientId) {
		this.clientBySstockClientId = clientBySstockClientId;
	}

	public Integer getClientBySstockShiftToFirm() {
		return this.clientBySstockShiftToFirm;
	}

	public void setClientBySstockShiftToFirm(Integer clientBySstockShiftToFirm) {
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

}