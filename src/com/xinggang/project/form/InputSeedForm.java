package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 入库子订单表单
 * 
 * @author Administrator
 * 
 */
public class InputSeedForm extends ActionForm {

	private static final long serialVersionUID = 3786946626188831580L;
	private String iseedId;// 入库子订单编号
	private Integer goods;// 对应的货物类
	private PaymentFashionForm paymentFashion;// 支付方式，对应的支付类
	private String input;// 入库总订单编号，对应的入库总订单类
	private Double iseedShouldWeight;// 应入重量
	private Double iseedRealityWeight;// 实入重量
	private Double iseedShouldNumber;// 应入件数
	private Double iseedRealityNumber;// 实入件数
	private Double iseedShouldCost;// 应收费用
	private Double iseedRealityCost;// 实收费用
	private String iseedClientAccounts;// 结算方式
	private String iseedOrderStatus;// 订单状态
	private String iseedDefinedOne;// 预留字段一
	private String iseedDefinedTwo;// 预留字段二
	private String iseedRemark;// 备注

	private String begin;// 订单查询时的起始日期
	private String finish;// 订单查询中的结束日期
	private String clientName;// 订单查询中的客户名称
	private String clientNumber;// 订单查询中的客户单号
	private String goodsName;// 订单查询中的货物名称
	// app中查询对应的客户的日结的费用
	private Integer clientId;

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
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

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getIseedId() {
		return this.iseedId;
	}

	public void setIseedId(String iseedId) {
		this.iseedId = iseedId;
	}

	public Integer getGoods() {
		return this.goods;
	}

	public void setGoods(Integer goods) {
		this.goods = goods;
	}

	public PaymentFashionForm getPaymentFashion() {
		return this.paymentFashion;
	}

	public void setPaymentFashion(PaymentFashionForm paymentFashion) {
		this.paymentFashion = paymentFashion;
	}

	public String getInput() {
		return this.input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public Double getIseedShouldWeight() {
		return this.iseedShouldWeight;
	}

	public void setIseedShouldWeight(Double iseedShouldWeight) {
		this.iseedShouldWeight = iseedShouldWeight;
	}

	public Double getIseedRealityWeight() {
		return this.iseedRealityWeight;
	}

	public void setIseedRealityWeight(Double iseedRealityWeight) {
		this.iseedRealityWeight = iseedRealityWeight;
	}

	public Double getIseedShouldNumber() {
		return this.iseedShouldNumber;
	}

	public void setIseedShouldNumber(Double iseedShouldNumber) {
		this.iseedShouldNumber = iseedShouldNumber;
	}

	public Double getIseedRealityNumber() {
		return this.iseedRealityNumber;
	}

	public void setIseedRealityNumber(Double iseedRealityNumber) {
		this.iseedRealityNumber = iseedRealityNumber;
	}

	public Double getIseedShouldCost() {
		return this.iseedShouldCost;
	}

	public void setIseedShouldCost(Double iseedShouldCost) {
		this.iseedShouldCost = iseedShouldCost;
	}

	public Double getIseedRealityCost() {
		return this.iseedRealityCost;
	}

	public void setIseedRealityCost(Double iseedRealityCost) {
		this.iseedRealityCost = iseedRealityCost;
	}

	public String getIseedClientAccounts() {
		return this.iseedClientAccounts;
	}

	public void setIseedClientAccounts(String iseedClientAccounts) {
		this.iseedClientAccounts = iseedClientAccounts;
	}

	public String getIseedOrderStatus() {
		return this.iseedOrderStatus;
	}

	public void setIseedOrderStatus(String iseedOrderStatus) {
		this.iseedOrderStatus = iseedOrderStatus;
	}

	public String getIseedDefinedOne() {
		return this.iseedDefinedOne;
	}

	public void setIseedDefinedOne(String iseedDefinedOne) {
		this.iseedDefinedOne = iseedDefinedOne;
	}

	public String getIseedDefinedTwo() {
		return this.iseedDefinedTwo;
	}

	public void setIseedDefinedTwo(String iseedDefinedTwo) {
		this.iseedDefinedTwo = iseedDefinedTwo;
	}

	public String getIseedRemark() {
		return this.iseedRemark;
	}

	public void setIseedRemark(String iseedRemark) {
		this.iseedRemark = iseedRemark;
	}

}