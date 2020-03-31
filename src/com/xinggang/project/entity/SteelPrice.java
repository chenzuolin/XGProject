package com.xinggang.project.entity;

/**
 * SteelPrice entity. @author MyEclipse Persistence Tools 今日钢价实体类
 */

public class SteelPrice implements java.io.Serializable {

	private static final long serialVersionUID = -5649901132174757717L;
	private String spid; // 编号
	private String spgoodsName;// 货物名称
	private String spgoodsStandard;// 货物规格
	private String spgoodsQuality;// 货物材质
	private String spgoodsProperty;// 货物属性
	private String spgoodsYieldly;// 货物产地
	private Double spgoodsPrice;// 货物的单价
	private String spoperator;// 添加的操作人员
	private String spoperatorTime;// 添加时间
	private String spremark;// 备注
	private String spdefinedOne;// 自定义一
	private String spdefinedTwo;// 自定义二
	private String spdefinedThree;// 自定义三
	private String spdefinedFour;// 自定义四
	private String spdefinedFive;// 自定义五
	private String spdefinedSix;// 自定义六

	public SteelPrice() {
	}

	public SteelPrice(String spgoodsName, String spgoodsStandard,
			String spgoodsQuality, String spgoodsProperty,
			String spgoodsYieldly, Double spgoodsPrice, String spoperator,
			String spoperatorTime, String spremark, String spdefinedOne,
			String spdefinedTwo, String spdefinedThree, String spdefinedFour,
			String spdefinedFive, String spdefinedSix) {
		this.spgoodsName = spgoodsName;
		this.spgoodsStandard = spgoodsStandard;
		this.spgoodsQuality = spgoodsQuality;
		this.spgoodsProperty = spgoodsProperty;
		this.spgoodsYieldly = spgoodsYieldly;
		this.spgoodsPrice = spgoodsPrice;
		this.spoperator = spoperator;
		this.spoperatorTime = spoperatorTime;
		this.spremark = spremark;
		this.spdefinedOne = spdefinedOne;
		this.spdefinedTwo = spdefinedTwo;
		this.spdefinedThree = spdefinedThree;
		this.spdefinedFour = spdefinedFour;
		this.spdefinedFive = spdefinedFive;
		this.spdefinedSix = spdefinedSix;
	}

	public String getSpid() {
		return this.spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	public String getSpgoodsName() {
		return this.spgoodsName;
	}

	public void setSpgoodsName(String spgoodsName) {
		this.spgoodsName = spgoodsName;
	}

	public String getSpgoodsStandard() {
		return this.spgoodsStandard;
	}

	public void setSpgoodsStandard(String spgoodsStandard) {
		this.spgoodsStandard = spgoodsStandard;
	}

	public String getSpgoodsQuality() {
		return this.spgoodsQuality;
	}

	public void setSpgoodsQuality(String spgoodsQuality) {
		this.spgoodsQuality = spgoodsQuality;
	}

	public String getSpgoodsProperty() {
		return this.spgoodsProperty;
	}

	public void setSpgoodsProperty(String spgoodsProperty) {
		this.spgoodsProperty = spgoodsProperty;
	}

	public String getSpgoodsYieldly() {
		return this.spgoodsYieldly;
	}

	public void setSpgoodsYieldly(String spgoodsYieldly) {
		this.spgoodsYieldly = spgoodsYieldly;
	}

	public Double getSpgoodsPrice() {
		return this.spgoodsPrice;
	}

	public void setSpgoodsPrice(Double spgoodsPrice) {
		this.spgoodsPrice = spgoodsPrice;
	}

	public String getSpoperator() {
		return this.spoperator;
	}

	public void setSpoperator(String spoperator) {
		this.spoperator = spoperator;
	}

	public String getSpoperatorTime() {
		return this.spoperatorTime;
	}

	public void setSpoperatorTime(String spoperatorTime) {
		this.spoperatorTime = spoperatorTime;
	}

	public String getSpremark() {
		return this.spremark;
	}

	public void setSpremark(String spremark) {
		this.spremark = spremark;
	}

	public String getSpdefinedOne() {
		return this.spdefinedOne;
	}

	public void setSpdefinedOne(String spdefinedOne) {
		this.spdefinedOne = spdefinedOne;
	}

	public String getSpdefinedTwo() {
		return this.spdefinedTwo;
	}

	public void setSpdefinedTwo(String spdefinedTwo) {
		this.spdefinedTwo = spdefinedTwo;
	}

	public String getSpdefinedThree() {
		return this.spdefinedThree;
	}

	public void setSpdefinedThree(String spdefinedThree) {
		this.spdefinedThree = spdefinedThree;
	}

	public String getSpdefinedFour() {
		return this.spdefinedFour;
	}

	public void setSpdefinedFour(String spdefinedFour) {
		this.spdefinedFour = spdefinedFour;
	}

	public String getSpdefinedFive() {
		return this.spdefinedFive;
	}

	public void setSpdefinedFive(String spdefinedFive) {
		this.spdefinedFive = spdefinedFive;
	}

	public String getSpdefinedSix() {
		return this.spdefinedSix;
	}

	public void setSpdefinedSix(String spdefinedSix) {
		this.spdefinedSix = spdefinedSix;
	}

}