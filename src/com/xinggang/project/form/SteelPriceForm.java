package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/*
 * 对应的今日钢价form
 * */
public class SteelPriceForm extends ActionForm {

	private static final long serialVersionUID = -6778417026718045840L;
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

	// 通过时间范围的查询
	private String begin;// 起始时间
	private String finish;// 结束时间

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public String getSpid() {
		return spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	public String getSpgoodsName() {
		return spgoodsName;
	}

	public void setSpgoodsName(String spgoodsName) {
		this.spgoodsName = spgoodsName;
	}

	public String getSpgoodsStandard() {
		return spgoodsStandard;
	}

	public void setSpgoodsStandard(String spgoodsStandard) {
		this.spgoodsStandard = spgoodsStandard;
	}

	public String getSpgoodsQuality() {
		return spgoodsQuality;
	}

	public void setSpgoodsQuality(String spgoodsQuality) {
		this.spgoodsQuality = spgoodsQuality;
	}

	public String getSpgoodsProperty() {
		return spgoodsProperty;
	}

	public void setSpgoodsProperty(String spgoodsProperty) {
		this.spgoodsProperty = spgoodsProperty;
	}

	public String getSpgoodsYieldly() {
		return spgoodsYieldly;
	}

	public void setSpgoodsYieldly(String spgoodsYieldly) {
		this.spgoodsYieldly = spgoodsYieldly;
	}

	public Double getSpgoodsPrice() {
		return spgoodsPrice;
	}

	public void setSpgoodsPrice(Double spgoodsPrice) {
		this.spgoodsPrice = spgoodsPrice;
	}

	public String getSpoperator() {
		return spoperator;
	}

	public void setSpoperator(String spoperator) {
		this.spoperator = spoperator;
	}

	public String getSpoperatorTime() {
		return spoperatorTime;
	}

	public void setSpoperatorTime(String spoperatorTime) {
		this.spoperatorTime = spoperatorTime;
	}

	public String getSpremark() {
		return spremark;
	}

	public void setSpremark(String spremark) {
		this.spremark = spremark;
	}

	public String getSpdefinedOne() {
		return spdefinedOne;
	}

	public void setSpdefinedOne(String spdefinedOne) {
		this.spdefinedOne = spdefinedOne;
	}

	public String getSpdefinedTwo() {
		return spdefinedTwo;
	}

	public void setSpdefinedTwo(String spdefinedTwo) {
		this.spdefinedTwo = spdefinedTwo;
	}

	public String getSpdefinedThree() {
		return spdefinedThree;
	}

	public void setSpdefinedThree(String spdefinedThree) {
		this.spdefinedThree = spdefinedThree;
	}

	public String getSpdefinedFour() {
		return spdefinedFour;
	}

	public void setSpdefinedFour(String spdefinedFour) {
		this.spdefinedFour = spdefinedFour;
	}

	public String getSpdefinedFive() {
		return spdefinedFive;
	}

	public void setSpdefinedFive(String spdefinedFive) {
		this.spdefinedFive = spdefinedFive;
	}

	public String getSpdefinedSix() {
		return spdefinedSix;
	}

	public void setSpdefinedSix(String spdefinedSix) {
		this.spdefinedSix = spdefinedSix;
	}

}
