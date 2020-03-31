package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 
 * 货物产地表单
 */

public class GoodsYieldlyForm extends ActionForm {

	private static final long serialVersionUID = -7656030643562575967L;
	private Integer goodsYieldlyId;// 货物产地编号
	private String goodsYieldlyName;// 货物产地名称
	private String goodsYieldlyDefinedOne;// 是否停用
	private String goodsYieldlyDefinedTwo;// 描述
	private String goodsYieldlyRemark;// 备注

	public Integer getGoodsYieldlyId() {
		return this.goodsYieldlyId;
	}

	public void setGoodsYieldlyId(Integer goodsYieldlyId) {
		this.goodsYieldlyId = goodsYieldlyId;
	}

	public String getGoodsYieldlyName() {
		return this.goodsYieldlyName;
	}

	public void setGoodsYieldlyName(String goodsYieldlyName) {
		this.goodsYieldlyName = goodsYieldlyName;
	}

	public String getGoodsYieldlyDefinedOne() {
		return this.goodsYieldlyDefinedOne;
	}

	public void setGoodsYieldlyDefinedOne(String goodsYieldlyDefinedOne) {
		this.goodsYieldlyDefinedOne = goodsYieldlyDefinedOne;
	}

	public String getGoodsYieldlyDefinedTwo() {
		return this.goodsYieldlyDefinedTwo;
	}

	public void setGoodsYieldlyDefinedTwo(String goodsYieldlyDefinedTwo) {
		this.goodsYieldlyDefinedTwo = goodsYieldlyDefinedTwo;
	}

	public String getGoodsYieldlyRemark() {
		return this.goodsYieldlyRemark;
	}

	public void setGoodsYieldlyRemark(String goodsYieldlyRemark) {
		this.goodsYieldlyRemark = goodsYieldlyRemark;
	}

}