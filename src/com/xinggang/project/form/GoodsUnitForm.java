package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class GoodsUnitForm extends ActionForm {
	
	 private Integer goodsUnitId;//计量单位id
     private String goodsUnitName;//计量单位名称
     private String goodsUnitDefinedOne;
     private String goodsUnitDefinedTwo;
     private String goodsUnitRemark;//备注
     
	public Integer getGoodsUnitId() {
		return goodsUnitId;
	}
	public void setGoodsUnitId(Integer goodsUnitId) {
		this.goodsUnitId = goodsUnitId;
	}
	public String getGoodsUnitName() {
		return goodsUnitName;
	}
	public void setGoodsUnitName(String goodsUnitName) {
		this.goodsUnitName = goodsUnitName;
	}
	public String getGoodsUnitDefinedOne() {
		return goodsUnitDefinedOne;
	}
	public void setGoodsUnitDefinedOne(String goodsUnitDefinedOne) {
		this.goodsUnitDefinedOne = goodsUnitDefinedOne;
	}
	public String getGoodsUnitDefinedTwo() {
		return goodsUnitDefinedTwo;
	}
	public void setGoodsUnitDefinedTwo(String goodsUnitDefinedTwo) {
		this.goodsUnitDefinedTwo = goodsUnitDefinedTwo;
	}
	public String getGoodsUnitRemark() {
		return goodsUnitRemark;
	}
	public void setGoodsUnitRemark(String goodsUnitRemark) {
		this.goodsUnitRemark = goodsUnitRemark;
	}
     
     
}
