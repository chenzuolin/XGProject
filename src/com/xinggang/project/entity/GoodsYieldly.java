package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * GoodsYieldly entity. @author MyEclipse Persistence Tools
 * 货物产地类
 */

public class GoodsYieldly implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7656030643562575967L;
	private Integer goodsYieldlyId;//货物产地编号
	private String goodsYieldlyName;//货物产地名称
	private String goodsYieldlyDefinedOne;//是否显示，如果0不显示
	private String goodsYieldlyDefinedTwo;//自定义字段2
	private String goodsYieldlyRemark;//备注
	private Set<Object> goodses = new HashSet<Object>(0);//对应货物明细类

	// Constructors

	/** default constructor */
	public GoodsYieldly() {
	}

	/** full constructor */
	public GoodsYieldly(String goodsYieldlyName, String goodsYieldlyDefinedOne,
			String goodsYieldlyDefinedTwo, String goodsYieldlyRemark,
			Set<Object> goodses) {
		this.goodsYieldlyName = goodsYieldlyName;
		this.goodsYieldlyDefinedOne = goodsYieldlyDefinedOne;
		this.goodsYieldlyDefinedTwo = goodsYieldlyDefinedTwo;
		this.goodsYieldlyRemark = goodsYieldlyRemark;
		this.goodses = goodses;
	}

	// Property accessors

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

	public Set<Object> getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set<Object> goodses) {
		this.goodses = goodses;
	}

}