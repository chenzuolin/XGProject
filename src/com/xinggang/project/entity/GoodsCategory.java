package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * GoodsCategory entity. @author MyEclipse Persistence Tools
 * 货物品类
 */

public class GoodsCategory implements java.io.Serializable {

	private static final long serialVersionUID = 85878590281073631L;
	private Integer goodsCategoryId;//货物品类编号
	private String goodsCategoryName;//货物品类名称
	private Integer goodsCategoryPovalidity;//货物有效期
	private String goodsCategoryDefinedOne;//是否显示，如果0不显示
	private String goodsCategoryDefinedTwo;//描述
	private String goodsCategoryRemark;//备注
	private Set<Object> goodses = new HashSet<Object>(0);//对应货物明细表

	// Constructors

	/** default constructor */
	public GoodsCategory() {
	}

	/** full constructor */
	public GoodsCategory(String goodsCategoryName,
			Integer goodsCategoryPovalidity, String goodsCategoryDefinedOne,
			String goodsCategoryDefinedTwo, String goodsCategoryRemark,
			Set<Object> goodses) {
		this.goodsCategoryName = goodsCategoryName;
		this.goodsCategoryPovalidity = goodsCategoryPovalidity;
		this.goodsCategoryDefinedOne = goodsCategoryDefinedOne;
		this.goodsCategoryDefinedTwo = goodsCategoryDefinedTwo;
		this.goodsCategoryRemark = goodsCategoryRemark;
		this.goodses = goodses;
	}

	// Property accessors

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

	public Set<Object> getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set<Object> goodses) {
		this.goodses = goodses;
	}

}