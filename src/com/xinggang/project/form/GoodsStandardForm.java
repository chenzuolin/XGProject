package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

public class GoodsStandardForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7440923748480596178L;
	private Integer goodsStandardId;//规格id
	private String goodsStandardName;//规格名称
	private String goodsStandardDefinedOne;//自定义字段1
	private String goodsStandardDefinedTwo;//自定义字段2
	private String goodsStandardRemark;//备注
	
	public Integer getGoodsStandardId() {
		return goodsStandardId;
	}
	public void setGoodsStandardId(Integer goodsStandardId) {
		this.goodsStandardId = goodsStandardId;
	}
	public String getGoodsStandardName() {
		return goodsStandardName;
	}
	public void setGoodsStandardName(String goodsStandardName) {
		this.goodsStandardName = goodsStandardName;
	}
	public String getGoodsStandardDefinedOne() {
		return goodsStandardDefinedOne;
	}
	public void setGoodsStandardDefinedOne(String goodsStandardDefinedOne) {
		this.goodsStandardDefinedOne = goodsStandardDefinedOne;
	}
	public String getGoodsStandardDefinedTwo() {
		return goodsStandardDefinedTwo;
	}
	public void setGoodsStandardDefinedTwo(String goodsStandardDefinedTwo) {
		this.goodsStandardDefinedTwo = goodsStandardDefinedTwo;
	}
	public String getGoodsStandardRemark() {
		return goodsStandardRemark;
	}
	public void setGoodsStandardRemark(String goodsStandardRemark) {
		this.goodsStandardRemark = goodsStandardRemark;
	}
	
	
}
