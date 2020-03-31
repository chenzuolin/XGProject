package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

public class GoodsQualityForm extends ActionForm {

	private static final long serialVersionUID = 7522197571209705936L;
	private Integer goodsQualityId;//货物材质
	private String goodsQualityName;//货物材质名称
	private String goodsQualityDefinedOne;//自定义字段1
	private String goodsQualityDefinedTwo;//自定义字段2
	private String goodsQualityRemark;//货物材质备注
	
	public Integer getGoodsQualityId() {
		return goodsQualityId;
	}
	public void setGoodsQualityId(Integer goodsQualityId) {
		this.goodsQualityId = goodsQualityId;
	}
	public String getGoodsQualityName() {
		return goodsQualityName;
	}
	public void setGoodsQualityName(String goodsQualityName) {
		this.goodsQualityName = goodsQualityName;
	}
	public String getGoodsQualityDefinedOne() {
		return goodsQualityDefinedOne;
	}
	public void setGoodsQualityDefinedOne(String goodsQualityDefinedOne) {
		this.goodsQualityDefinedOne = goodsQualityDefinedOne;
	}
	public String getGoodsQualityDefinedTwo() {
		return goodsQualityDefinedTwo;
	}
	public void setGoodsQualityDefinedTwo(String goodsQualityDefinedTwo) {
		this.goodsQualityDefinedTwo = goodsQualityDefinedTwo;
	}
	public String getGoodsQualityRemark() {
		return goodsQualityRemark;
	}
	public void setGoodsQualityRemark(String goodsQualityRemark) {
		this.goodsQualityRemark = goodsQualityRemark;
	}
	
	
}
