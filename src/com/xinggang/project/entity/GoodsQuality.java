package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 材质
 * GoodsQuality entity. @author MyEclipse Persistence Tools 货物材质
 */

@SuppressWarnings("serial")
public class GoodsQuality implements java.io.Serializable {

	// Fields

	private Integer goodsQualityId;// 货物材质
	private String goodsQualityName;// 货物材质名称
	private String goodsQualityDefinedOne;// 自定义字段1
	private String goodsQualityDefinedTwo;// 自定义字段2
	private String goodsQualityRemark;// 货物材质备注
	private Set<Object> goodses = new HashSet<Object>(0);

	// Constructors

	/** default constructor */
	public GoodsQuality() {
	}

	/** full constructor */
	public GoodsQuality(String goodsQualityName, String goodsQualityDefinedOne,
			String goodsQualityDefinedTwo, String goodsQualityRemark,
			Set<Object> goodses) {
		this.goodsQualityName = goodsQualityName;
		this.goodsQualityDefinedOne = goodsQualityDefinedOne;
		this.goodsQualityDefinedTwo = goodsQualityDefinedTwo;
		this.goodsQualityRemark = goodsQualityRemark;
		this.goodses = goodses;
	}

	// Property accessors

	public Integer getGoodsQualityId() {
		return this.goodsQualityId;
	}

	public void setGoodsQualityId(Integer goodsQualityId) {
		this.goodsQualityId = goodsQualityId;
	}

	public String getGoodsQualityName() {
		return this.goodsQualityName;
	}

	public void setGoodsQualityName(String goodsQualityName) {
		this.goodsQualityName = goodsQualityName;
	}

	public String getGoodsQualityDefinedOne() {
		return this.goodsQualityDefinedOne;
	}

	public void setGoodsQualityDefinedOne(String goodsQualityDefinedOne) {
		this.goodsQualityDefinedOne = goodsQualityDefinedOne;
	}

	public String getGoodsQualityDefinedTwo() {
		return this.goodsQualityDefinedTwo;
	}

	public void setGoodsQualityDefinedTwo(String goodsQualityDefinedTwo) {
		this.goodsQualityDefinedTwo = goodsQualityDefinedTwo;
	}

	public String getGoodsQualityRemark() {
		return this.goodsQualityRemark;
	}

	public void setGoodsQualityRemark(String goodsQualityRemark) {
		this.goodsQualityRemark = goodsQualityRemark;
	}

	public Set<Object> getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set<Object> goodses) {
		this.goodses = goodses;
	}

}