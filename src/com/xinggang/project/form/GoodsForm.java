package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 
 * 货物明细表单
 */

public class GoodsForm extends ActionForm {

	private static final long serialVersionUID = 8741102700249740030L;
	
	private Integer goodsId;// 货物名称编号
	private Integer goodsPropertyId;// 货物属性(调用属性ID)
	private Integer goodsCategoryId;// 货物品类(调用品类ID)
	private Integer goodsStandardId;//规格
	private Integer goodsQualityId;// 材质
	private Integer goodsYieldlyId;// 货物产地(调用产地ID)
	private String goodsName;// 货物名称
	private String goodsSign;// 货物名称助记符
	private Integer goodsUnitId;// 计量单位
	private Double goodsAdjustment;// 理算重量(数值，保留三位)
	private String goodsDefinedOne;// 自定义字段1
	private String goodsDefinedTwo;// 自定义字段2
	private String goodsRemark;// 备注
	
	private Integer[] propertyId;// 货物属性(调用属性ID)
	private Integer[] standardId;//规格
	private Integer[] qualityId;// 材质
	private Integer[] yieldlyId;// 产地
	
	
	
	
	public Integer[] getYieldlyId() {
		return yieldlyId;
	}
	public void setYieldlyId(Integer[] yieldlyId) {
		this.yieldlyId = yieldlyId;
	}
	public Integer[] getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(Integer[] propertyId) {
		this.propertyId = propertyId;
	}
	
	public Integer[] getStandardId() {
		return standardId;
	}
	public void setStandardId(Integer[] standardId) {
		this.standardId = standardId;
	}
	public Integer[] getQualityId() {
		return qualityId;
	}
	public void setQualityId(Integer[] qualityId) {
		this.qualityId = qualityId;
	}
	public Integer getGoodsUnitId() {
		return goodsUnitId;
	}
	public void setGoodsUnitId(Integer goodsUnitId) {
		this.goodsUnitId = goodsUnitId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
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
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsSign() {
		return goodsSign;
	}
	public void setGoodsSign(String goodsSign) {
		this.goodsSign = goodsSign;
	}
	public Double getGoodsAdjustment() {
		return goodsAdjustment;
	}
	public void setGoodsAdjustment(Double goodsAdjustment) {
		this.goodsAdjustment = goodsAdjustment;
	}
	public String getGoodsDefinedOne() {
		return goodsDefinedOne;
	}
	public void setGoodsDefinedOne(String goodsDefinedOne) {
		this.goodsDefinedOne = goodsDefinedOne;
	}
	public String getGoodsDefinedTwo() {
		return goodsDefinedTwo;
	}
	public void setGoodsDefinedTwo(String goodsDefinedTwo) {
		this.goodsDefinedTwo = goodsDefinedTwo;
	}
	public String getGoodsRemark() {
		return goodsRemark;
	}
	public void setGoodsRemark(String goodsRemark) {
		this.goodsRemark = goodsRemark;
	}	

}