package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 货物批次表单
 * 
 * @author Administrator
 * 
 */
public class TarehouseDetailForm extends ActionForm {

	private static final long serialVersionUID = 5564119531067481846L;
	private String tdetailId;// 货物批次编号
	private Integer goods;// 对应的货物类
	private String tdetailIputTime;// 入库日期
	private Double tdetailWeight;// 入库重量
	private Double tdetailEweight;// 已出重量
	private Double tdetailNumber;// 入库件数
	private Double tdetailEnumber;// 已出件数
	private Integer tdetailRemind;// 是否提醒，提醒5次，每次加一，直到5时不再提醒
	private String tdetailDefinedOne;// 预留字段一
	private String tdetailDefinedTwo;// 预留字段二
	private String tdetailRemark;// 备注

	private String goodsName;// 货物的名称，用到模糊查询
	private String goodsSign;// 货物的助记符，用到的模糊查询
	private String goodsStandard;// 货物的规格，用到的模糊查询
	private String goodsQuality;// 货物材质，用到的模糊查询
	private String goodsProperty;// 货物的属性，用到的模糊查询

	private String begin;// 起始日期
	private String finish;// 结束日期

	private String kuname;// 库位名称

	private Integer kuwei;// 库位对应的编号

	public Integer getKuwei() {
		return kuwei;
	}

	public void setKuwei(Integer kuwei) {
		this.kuwei = kuwei;
	}

	public String getKuname() {
		return kuname;
	}

	public void setKuname(String kuname) {
		this.kuname = kuname;
	}

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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsSign() {
		return goodsSign;
	}

	public void setGoodsSign(String goodsSign) {
		this.goodsSign = goodsSign;
	}

	public String getGoodsStandard() {
		return goodsStandard;
	}

	public void setGoodsStandard(String goodsStandard) {
		this.goodsStandard = goodsStandard;
	}

	public String getGoodsQuality() {
		return goodsQuality;
	}

	public void setGoodsQuality(String goodsQuality) {
		this.goodsQuality = goodsQuality;
	}

	public String getGoodsProperty() {
		return goodsProperty;
	}

	public void setGoodsProperty(String goodsProperty) {
		this.goodsProperty = goodsProperty;
	}

	public String getTdetailId() {
		return this.tdetailId;
	}

	public void setTdetailId(String tdetailId) {
		this.tdetailId = tdetailId;
	}

	public Integer getGoods() {
		return this.goods;
	}

	public void setGoods(Integer goods) {
		this.goods = goods;
	}

	public String getTdetailIputTime() {
		return this.tdetailIputTime;
	}

	public void setTdetailIputTime(String tdetailIputTime) {
		this.tdetailIputTime = tdetailIputTime;
	}

	public Double getTdetailWeight() {
		return this.tdetailWeight;
	}

	public void setTdetailWeight(Double tdetailWeight) {
		this.tdetailWeight = tdetailWeight;
	}

	public Double getTdetailEweight() {
		return this.tdetailEweight;
	}

	public void setTdetailEweight(Double tdetailEweight) {
		this.tdetailEweight = tdetailEweight;
	}

	public Double getTdetailNumber() {
		return this.tdetailNumber;
	}

	public void setTdetailNumber(Double tdetailNumber) {
		this.tdetailNumber = tdetailNumber;
	}

	public Double getTdetailEnumber() {
		return this.tdetailEnumber;
	}

	public void setTdetailEnumber(Double tdetailEnumber) {
		this.tdetailEnumber = tdetailEnumber;
	}

	public Integer getTdetailRemind() {
		return this.tdetailRemind;
	}

	public void setTdetailRemind(Integer tdetailRemind) {
		this.tdetailRemind = tdetailRemind;
	}

	public String getTdetailDefinedOne() {
		return this.tdetailDefinedOne;
	}

	public void setTdetailDefinedOne(String tdetailDefinedOne) {
		this.tdetailDefinedOne = tdetailDefinedOne;
	}

	public String getTdetailDefinedTwo() {
		return this.tdetailDefinedTwo;
	}

	public void setTdetailDefinedTwo(String tdetailDefinedTwo) {
		this.tdetailDefinedTwo = tdetailDefinedTwo;
	}

	public String getTdetailRemark() {
		return this.tdetailRemark;
	}

	public void setTdetailRemark(String tdetailRemark) {
		this.tdetailRemark = tdetailRemark;
	}

}