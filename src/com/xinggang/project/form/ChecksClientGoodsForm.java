package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 盘点客户库存的form
 * 
 * @author Administrator
 * 
 */
public class ChecksClientGoodsForm extends ActionForm {

	private static final long serialVersionUID = -1116836033166273233L;
	private String ccgoodsId; // 编号
	private Integer client;// 对应的客户
	private Integer bursary;// 对应的库房
	private Integer interiorUser;// 对应的盘点人，内部人员
	private Integer goods;// 对应的货物
	private Double ccgoodsBeforeWeight;// 对应的库存原有重量
	private Double ccgoodsSurplus;// 对应的盈库重量
	private Double ccgoodsLoss;// 对应的亏库重量
	private String ccgoodsChecksTime;// 对应的盘点时间
	private String ccgoodsDefinedOne;// 预留字段一
	private String ccgoodsDefinedTwo;// 预留字段二
	private String ccgoodsRemark;// 备注

	// 查询全部，模糊查询定义
	private String danwei;// 单位全拼
	private String jiancheng;// 单位简称
	private String danweizhujifu;// 单位名称助记符
	private String goodsName;// 货物名称
	private String goodsSign;// 货物名称助记符
	private String begin;// 起始日期
	private String finish;// 结束日期
	private String kufangname;// 库房名称

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

	public String getDanweizhujifu() {
		return danweizhujifu;
	}

	public void setDanweizhujifu(String danweizhujifu) {
		this.danweizhujifu = danweizhujifu;
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

	public String getKufangname() {
		return kufangname;
	}

	public void setKufangname(String kufangname) {
		this.kufangname = kufangname;
	}

	public String getCcgoodsId() {
		return this.ccgoodsId;
	}

	public void setCcgoodsId(String ccgoodsId) {
		this.ccgoodsId = ccgoodsId;
	}

	public Integer getClient() {
		return this.client;
	}

	public void setClient(Integer client) {
		this.client = client;
	}

	public Integer getBursary() {
		return this.bursary;
	}

	public void setBursary(Integer bursary) {
		this.bursary = bursary;
	}

	public Integer getInteriorUser() {
		return this.interiorUser;
	}

	public void setInteriorUser(Integer interiorUser) {
		this.interiorUser = interiorUser;
	}

	public Integer getGoods() {
		return this.goods;
	}

	public void setGoods(Integer goods) {
		this.goods = goods;
	}

	public Double getCcgoodsBeforeWeight() {
		return this.ccgoodsBeforeWeight;
	}

	public void setCcgoodsBeforeWeight(Double ccgoodsBeforeWeight) {
		this.ccgoodsBeforeWeight = ccgoodsBeforeWeight;
	}

	public Double getCcgoodsSurplus() {
		return this.ccgoodsSurplus;
	}

	public void setCcgoodsSurplus(Double ccgoodsSurplus) {
		this.ccgoodsSurplus = ccgoodsSurplus;
	}

	public Double getCcgoodsLoss() {
		return this.ccgoodsLoss;
	}

	public void setCcgoodsLoss(Double ccgoodsLoss) {
		this.ccgoodsLoss = ccgoodsLoss;
	}

	public String getCcgoodsChecksTime() {
		return this.ccgoodsChecksTime;
	}

	public void setCcgoodsChecksTime(String ccgoodsChecksTime) {
		this.ccgoodsChecksTime = ccgoodsChecksTime;
	}

	public String getCcgoodsDefinedOne() {
		return this.ccgoodsDefinedOne;
	}

	public void setCcgoodsDefinedOne(String ccgoodsDefinedOne) {
		this.ccgoodsDefinedOne = ccgoodsDefinedOne;
	}

	public String getCcgoodsDefinedTwo() {
		return this.ccgoodsDefinedTwo;
	}

	public void setCcgoodsDefinedTwo(String ccgoodsDefinedTwo) {
		this.ccgoodsDefinedTwo = ccgoodsDefinedTwo;
	}

	public String getCcgoodsRemark() {
		return this.ccgoodsRemark;
	}

	public void setCcgoodsRemark(String ccgoodsRemark) {
		this.ccgoodsRemark = ccgoodsRemark;
	}

}
