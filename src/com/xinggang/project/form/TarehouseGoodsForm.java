package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 货物库存表单
 * 
 * @author Administrator
 * 
 */
public class TarehouseGoodsForm extends ActionForm {

	private static final long serialVersionUID = 6380880589930972308L;
	private Integer tgoodsId;// 货物库存编号
	private Integer goods;// 对应的货物类
	private Integer tarehouse;// 对应的库位类
	private Double tgoodsWeight;// 库存重量
	private Double tgoodsNumber;// 库存件数
	private String tgoodsDefinedTwo;// 预留字段一
	private String tgoodsDefinedOne;// 预留字段二
	private String tgoodsRemark;// 备注

	private String goodsName;// 要模糊查询的货物名称
	private String goodsSign;// 要模糊查询的货物助记符
	private String goodsStandard;// 要模糊查询的规格
	private String goodsQuality;// 要模糊查询的材质
	private String goodsProperty;// 要模糊查询的属性

	private String kuname;// 通过库位名称的模糊查询

	private String pinlei;// 品类
	private String chandi;// 产地

	public String getPinlei() {
		return pinlei;
	}

	public void setPinlei(String pinlei) {
		this.pinlei = pinlei;
	}

	public String getChandi() {
		return chandi;
	}

	public void setChandi(String chandi) {
		this.chandi = chandi;
	}

	public String getKuname() {
		return kuname;
	}

	public void setKuname(String kuname) {
		this.kuname = kuname;
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

	public Integer getTgoodsId() {
		return this.tgoodsId;
	}

	public void setTgoodsId(Integer tgoodsId) {
		this.tgoodsId = tgoodsId;
	}

	public Integer getGoods() {
		return this.goods;
	}

	public void setGoods(Integer goods) {
		this.goods = goods;
	}

	public Integer getTarehouse() {
		return this.tarehouse;
	}

	public void setTarehouse(Integer tarehouse) {
		this.tarehouse = tarehouse;
	}

	public Double getTgoodsWeight() {
		return this.tgoodsWeight;
	}

	public void setTgoodsWeight(Double tgoodsWeight) {
		this.tgoodsWeight = tgoodsWeight;
	}

	public Double getTgoodsNumber() {
		return this.tgoodsNumber;
	}

	public void setTgoodsNumber(Double tgoodsNumber) {
		this.tgoodsNumber = tgoodsNumber;
	}

	public String getTgoodsDefinedTwo() {
		return this.tgoodsDefinedTwo;
	}

	public void setTgoodsDefinedTwo(String tgoodsDefinedTwo) {
		this.tgoodsDefinedTwo = tgoodsDefinedTwo;
	}

	public String getTgoodsDefinedOne() {
		return this.tgoodsDefinedOne;
	}

	public void setTgoodsDefinedOne(String tgoodsDefinedOne) {
		this.tgoodsDefinedOne = tgoodsDefinedOne;
	}

	public String getTgoodsRemark() {
		return this.tgoodsRemark;
	}

	public void setTgoodsRemark(String tgoodsRemark) {
		this.tgoodsRemark = tgoodsRemark;
	}

}