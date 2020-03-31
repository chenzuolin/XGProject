package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * GoodsProperty entity. @author MyEclipse Persistence Tools
 * 货物属性
 */

@SuppressWarnings("serial")
public class GoodsProperty implements java.io.Serializable {

	// Fields

	private Integer goodsPropertyId;//货物属性id
	private String goodsPropertyName;//属性名称
	private String goodsPropertyDefinedOne;//自定义字段1
	private String goodsPropertyDefinedTwo;//自定义字段2
	private String goodsPropertyRemark;//备注
	private Set<Object> goodses = new HashSet<Object>(0);

	// Constructors

	/** default constructor */
	public GoodsProperty() {
	}

	/** full constructor */
	public GoodsProperty(String goodsPropertyName,
			String goodsPropertyDefinedOne, String goodsPropertyDefinedTwo,
			String goodsPropertyRemark, Set<Object> goodses) {
		this.goodsPropertyName = goodsPropertyName;
		this.goodsPropertyDefinedOne = goodsPropertyDefinedOne;
		this.goodsPropertyDefinedTwo = goodsPropertyDefinedTwo;
		this.goodsPropertyRemark = goodsPropertyRemark;
		this.goodses = goodses;
	}

	// Property accessors

	public Integer getGoodsPropertyId() {
		return this.goodsPropertyId;
	}

	public void setGoodsPropertyId(Integer goodsPropertyId) {
		this.goodsPropertyId = goodsPropertyId;
	}

	public String getGoodsPropertyName() {
		return this.goodsPropertyName;
	}

	public void setGoodsPropertyName(String goodsPropertyName) {
		this.goodsPropertyName = goodsPropertyName;
	}

	public String getGoodsPropertyDefinedOne() {
		return this.goodsPropertyDefinedOne;
	}

	public void setGoodsPropertyDefinedOne(String goodsPropertyDefinedOne) {
		this.goodsPropertyDefinedOne = goodsPropertyDefinedOne;
	}

	public String getGoodsPropertyDefinedTwo() {
		return this.goodsPropertyDefinedTwo;
	}

	public void setGoodsPropertyDefinedTwo(String goodsPropertyDefinedTwo) {
		this.goodsPropertyDefinedTwo = goodsPropertyDefinedTwo;
	}

	public String getGoodsPropertyRemark() {
		return this.goodsPropertyRemark;
	}

	public void setGoodsPropertyRemark(String goodsPropertyRemark) {
		this.goodsPropertyRemark = goodsPropertyRemark;
	}

	public Set<Object> getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set<Object> goodses) {
		this.goodses = goodses;
	}

}