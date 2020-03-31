package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 客户货物库存表单
 * 
 * @author Administrator
 * 
 */
public class ClientGoodsForm extends ActionForm {

	private static final long serialVersionUID = -7761903875629130856L;
	private String cgoodsId;// 客户货物库存编号
	private Integer client;// 对应的客户
	private Integer goods;// 对应的货物
	private Double cgoodsWeight;// 货物重量
	private Double cgoodsNumber;// 货物件数
	private String cgoodsFreeze;// 是否冻结
	private Double cgoodsFreezeWeight;// 冻结重量
	private Double cgoodsFreezeNumber;// 冻结件数
	private String cgoodsImpawn;// 是否质押
	private Double cgoodsImpawnWeight;// 质押重量
	private Double cgoodsImpawnNumber;// 质押件数
	private String cgoodsDefinedOne;// 预留字段一
	private String cgoodsDefinedTwo;// 预留字段二
	private String cgoodsRemark;// 备注

	private String danwei;// 通过单位名称查询货物
	private String jiancheng;// 通过简称查询货物库存，模糊查询
	private String zhujifu;// 通过助记符模糊查询货物库存
	private String goodsName;// 通过货物名称模糊查询
	private String goodsSign;// 通过货物助记符模糊查询
	private String goodsStandard;// 通过货物规格模糊查询
	private String goodsQuality;// 通过货物材质模糊查询
	private String goodsProperty;// 通过货物属性模糊查询

	public String getDanwei() {
		return danwei;
	}

	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}

	public String getJiancheng() {
		return jiancheng;
	}

	public void setJiancheng(String jiancheng) {
		this.jiancheng = jiancheng;
	}

	public String getZhujifu() {
		return zhujifu;
	}

	public void setZhujifu(String zhujifu) {
		this.zhujifu = zhujifu;
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

	public String getCgoodsId() {
		return this.cgoodsId;
	}

	public void setCgoodsId(String cgoodsId) {
		this.cgoodsId = cgoodsId;
	}

	public Integer getClient() {
		return this.client;
	}

	public void setClient(Integer client) {
		this.client = client;
	}

	public Integer getGoods() {
		return this.goods;
	}

	public void setGoods(Integer goods) {
		this.goods = goods;
	}

	public Double getCgoodsWeight() {
		return this.cgoodsWeight;
	}

	public void setCgoodsWeight(Double cgoodsWeight) {
		this.cgoodsWeight = cgoodsWeight;
	}

	public Double getCgoodsNumber() {
		return this.cgoodsNumber;
	}

	public void setCgoodsNumber(Double cgoodsNumber) {
		this.cgoodsNumber = cgoodsNumber;
	}

	public String getCgoodsFreeze() {
		return this.cgoodsFreeze;
	}

	public void setCgoodsFreeze(String cgoodsFreeze) {
		this.cgoodsFreeze = cgoodsFreeze;
	}

	public Double getCgoodsFreezeWeight() {
		return this.cgoodsFreezeWeight;
	}

	public void setCgoodsFreezeWeight(Double cgoodsFreezeWeight) {
		this.cgoodsFreezeWeight = cgoodsFreezeWeight;
	}

	public Double getCgoodsFreezeNumber() {
		return this.cgoodsFreezeNumber;
	}

	public void setCgoodsFreezeNumber(Double cgoodsFreezeNumber) {
		this.cgoodsFreezeNumber = cgoodsFreezeNumber;
	}

	public String getCgoodsImpawn() {
		return this.cgoodsImpawn;
	}

	public void setCgoodsImpawn(String cgoodsImpawn) {
		this.cgoodsImpawn = cgoodsImpawn;
	}

	public Double getCgoodsImpawnWeight() {
		return this.cgoodsImpawnWeight;
	}

	public void setCgoodsImpawnWeight(Double cgoodsImpawnWeight) {
		this.cgoodsImpawnWeight = cgoodsImpawnWeight;
	}

	public Double getCgoodsImpawnNumber() {
		return this.cgoodsImpawnNumber;
	}

	public void setCgoodsImpawnNumber(Double cgoodsImpawnNumber) {
		this.cgoodsImpawnNumber = cgoodsImpawnNumber;
	}

	public String getCgoodsDefinedOne() {
		return this.cgoodsDefinedOne;
	}

	public void setCgoodsDefinedOne(String cgoodsDefinedOne) {
		this.cgoodsDefinedOne = cgoodsDefinedOne;
	}

	public String getCgoodsDefinedTwo() {
		return this.cgoodsDefinedTwo;
	}

	public void setCgoodsDefinedTwo(String cgoodsDefinedTwo) {
		this.cgoodsDefinedTwo = cgoodsDefinedTwo;
	}

	public String getCgoodsRemark() {
		return this.cgoodsRemark;
	}

	public void setCgoodsRemark(String cgoodsRemark) {
		this.cgoodsRemark = cgoodsRemark;
	}

}