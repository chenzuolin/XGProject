package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 规格类
 * GoodsStandard entity. @author MyEclipse Persistence Tools 货物规格
 */

public class GoodsStandard implements java.io.Serializable {

	private static final long serialVersionUID = 3604910312935917321L;
	private Integer goodsStandardId;// 规格id
	private String goodsStandardName;// 规格名称
	private String goodsStandardDefinedOne;// 自定义字段1
	private String goodsStandardDefinedTwo;// 自定义字段2
	private String goodsStandardRemark;// 备注
	private Set<Object> goodses = new HashSet<Object>(0);

	public GoodsStandard() {
	}

	/** full constructor */
	public GoodsStandard(String goodsStandardName,
			String goodsStandardDefinedOne, String goodsStandardDefinedTwo,
			String goodsStandardRemark, Set<Object> goodses) {
		this.goodsStandardName = goodsStandardName;
		this.goodsStandardDefinedOne = goodsStandardDefinedOne;
		this.goodsStandardDefinedTwo = goodsStandardDefinedTwo;
		this.goodsStandardRemark = goodsStandardRemark;
		this.goodses = goodses;
	}

	// Property accessors

	public Integer getGoodsStandardId() {
		return this.goodsStandardId;
	}

	public void setGoodsStandardId(Integer goodsStandardId) {
		this.goodsStandardId = goodsStandardId;
	}

	public String getGoodsStandardName() {
		return this.goodsStandardName;
	}

	public void setGoodsStandardName(String goodsStandardName) {
		this.goodsStandardName = goodsStandardName;
	}

	public String getGoodsStandardDefinedOne() {
		return this.goodsStandardDefinedOne;
	}

	public void setGoodsStandardDefinedOne(String goodsStandardDefinedOne) {
		this.goodsStandardDefinedOne = goodsStandardDefinedOne;
	}

	public String getGoodsStandardDefinedTwo() {
		return this.goodsStandardDefinedTwo;
	}

	public void setGoodsStandardDefinedTwo(String goodsStandardDefinedTwo) {
		this.goodsStandardDefinedTwo = goodsStandardDefinedTwo;
	}

	public String getGoodsStandardRemark() {
		return this.goodsStandardRemark;
	}

	public void setGoodsStandardRemark(String goodsStandardRemark) {
		this.goodsStandardRemark = goodsStandardRemark;
	}

	public Set<Object> getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set<Object> goodses) {
		this.goodses = goodses;
	}

}