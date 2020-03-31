package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class GoodsPropertyForm extends ActionForm {

	private Integer goodsPropertyId;//货物属性id
	private String goodsPropertyName;//属性名称
	private String goodsPropertyDefinedOne;//自定义字段1
	private String goodsPropertyDefinedTwo;//自定义字段2
	private String goodsPropertyRemark;//备注
	
	public String getGoodsPropertyDefinedOne() {
		return goodsPropertyDefinedOne;
	}
	public void setGoodsPropertyDefinedOne(String goodsPropertyDefinedOne) {
		this.goodsPropertyDefinedOne = goodsPropertyDefinedOne;
	}
	public Integer getGoodsPropertyId() {
		return goodsPropertyId;
	}
	public void setGoodsPropertyId(Integer goodsPropertyId) {
		this.goodsPropertyId = goodsPropertyId;
	}
	public String getGoodsPropertyName() {
		return goodsPropertyName;
	}
	public void setGoodsPropertyName(String goodsPropertyName) {
		this.goodsPropertyName = goodsPropertyName;
	}
	public String getGoodsPropertyDefinedTwo() {
		return goodsPropertyDefinedTwo;
	}
	public void setGoodsPropertyDefinedTwo(String goodsPropertyDefinedTwo) {
		this.goodsPropertyDefinedTwo = goodsPropertyDefinedTwo;
	}
	public String getGoodsPropertyRemark() {
		return goodsPropertyRemark;
	}
	public void setGoodsPropertyRemark(String goodsPropertyRemark) {
		this.goodsPropertyRemark = goodsPropertyRemark;
	}
	
	
}
