package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 入库子订单类
 * 
 * @author Administrator
 * 
 */
public class InputSeed implements java.io.Serializable {

	private static final long serialVersionUID = 3786946626188831580L;
	private String iseedId;// 入库子订单编号
	private Goods goods;// 对应的货物类
	private PaymentFashion paymentFashion;// 支付方式，对应的支付类
	private Input input;// 入库总订单编号，对应的入库总订单类
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
	private Set<Object> inputOperates = new HashSet<Object>(0);// 对应的入库操作类
	private String zongId;// 总订单编号的id，为了查询app中今日订单用到，
	private String time;// 时间app中用到

	public InputSeed() {
	}

	public InputSeed(Goods goods, Double iseedRealityWeight) {
		this.iseedRealityWeight = iseedRealityWeight;
		this.goods = goods;
	}

	public InputSeed(Double iseedRealityWeight) {
		this.iseedRealityWeight = iseedRealityWeight;
	}

	public InputSeed(Goods goods, PaymentFashion paymentFashion, Input input,
			Double iseedShouldWeight, Double iseedRealityWeight,
			Double iseedShouldNumber, Double iseedRealityNumber,
			Double iseedShouldCost, Double iseedRealityCost,
			String iseedClientAccounts, String iseedOrderStatus,
			String iseedDefinedOne, String iseedDefinedTwo, String iseedRemark,
			Set<Object> inputOperates) {
		this.goods = goods;
		this.paymentFashion = paymentFashion;
		this.input = input;
		this.iseedShouldWeight = iseedShouldWeight;
		this.iseedRealityWeight = iseedRealityWeight;
		this.iseedShouldNumber = iseedShouldNumber;
		this.iseedRealityNumber = iseedRealityNumber;
		this.iseedShouldCost = iseedShouldCost;
		this.iseedRealityCost = iseedRealityCost;
		this.iseedClientAccounts = iseedClientAccounts;
		this.iseedOrderStatus = iseedOrderStatus;
		this.iseedDefinedOne = iseedDefinedOne;
		this.iseedDefinedTwo = iseedDefinedTwo;
		this.iseedRemark = iseedRemark;
		this.inputOperates = inputOperates;
	}

	public String getIseedId() {
		return this.iseedId;
	}

	public String getZongId() {
		return zongId;
	}

	public void setZongId(String zongId) {
		this.zongId = zongId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setIseedId(String iseedId) {
		this.iseedId = iseedId;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public PaymentFashion getPaymentFashion() {
		return this.paymentFashion;
	}

	public void setPaymentFashion(PaymentFashion paymentFashion) {
		this.paymentFashion = paymentFashion;
	}

	public Input getInput() {
		return this.input;
	}

	public void setInput(Input input) {
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

	public Set<Object> getInputOperates() {
		return this.inputOperates;
	}

	public void setInputOperates(Set<Object> inputOperates) {
		this.inputOperates = inputOperates;
	}

}