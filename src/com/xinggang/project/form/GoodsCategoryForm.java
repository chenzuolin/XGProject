package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 货物品类表单
 */

public class GoodsCategoryForm extends ActionForm {

	private static final long serialVersionUID = 85878590281073631L;
	private Integer goodsCategoryId;// 货物品类编号
	private String goodsCategoryName;// 货物品类名称
	private Integer goodsCategoryPovalidity;// 货物有效期
	private String goodsCategoryDefinedOne;// 是否停用，默认为1
	private String goodsCategoryDefinedTwo;// 描述
	private String goodsCategoryRemark;// 备注

	public Integer getGoodsCategoryId() {
		return this.goodsCategoryId;
	}

	public void setGoodsCategoryId(Integer goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	public String getGoodsCategoryName() {
		return this.goodsCategoryName;
	}

	public void setGoodsCategoryName(String goodsCategoryName) {
		this.goodsCategoryName = goodsCategoryName;
	}

	public Integer getGoodsCategoryPovalidity() {
		return this.goodsCategoryPovalidity;
	}

	public void setGoodsCategoryPovalidity(Integer goodsCategoryPovalidity) {
		this.goodsCategoryPovalidity = goodsCategoryPovalidity;
	}

	public String getGoodsCategoryDefinedOne() {
		return this.goodsCategoryDefinedOne;
	}

	public void setGoodsCategoryDefinedOne(String goodsCategoryDefinedOne) {
		this.goodsCategoryDefinedOne = goodsCategoryDefinedOne;
	}

	public String getGoodsCategoryDefinedTwo() {
		return this.goodsCategoryDefinedTwo;
	}

	public void setGoodsCategoryDefinedTwo(String goodsCategoryDefinedTwo) {
		this.goodsCategoryDefinedTwo = goodsCategoryDefinedTwo;
	}

	public String getGoodsCategoryRemark() {
		return this.goodsCategoryRemark;
	}

	public void setGoodsCategoryRemark(String goodsCategoryRemark) {
		this.goodsCategoryRemark = goodsCategoryRemark;
	}

}