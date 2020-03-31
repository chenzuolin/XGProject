package com.xinggang.project.entity;

/**
 * 过户子订单类
 * 
 * @author Administrator
 * 
 */
public class ShiftStockSeed implements java.io.Serializable {

	private static final long serialVersionUID = 120814507514822607L;
	private String ssseedId;// 子订单编号
	private InteriorUser interiorUserBySsseedAuditing;// 对应的审核人员
	private InteriorUser interiorUserBySsseedCollectMan;// 对应的收费人员
	private ShiftStock shiftStock;// 对应的总订单
	private PaymentFashion paymentFashion;// 对应的收费方式类
	private Goods goods;// 对应的货物
	private String ssseedAuditingTime;// 审核时间
	private String ssseedAuditingTrue;// 审核是否通过
	private Double ssseedShouldCost;// 应收费用
	private Double ssseedRealityCost;// 实收费用
	private String ssseedCollectTime;// 收费时间
	private Double ssseedWeight;// 过户重量
	private Double ssseedNumber;// 过户件数
	private String ssseedClientAccounts;// 结算方式
	private String ssseedOrderStatus;// 订单状态
	private String ssseedDefinedOne;// 预留字段一
	private String ssseedDefinedTwo;// 预留字段二
	private String ssseedRemark;// 备注

	public ShiftStockSeed() {
	}

	public ShiftStockSeed(Double ssseedWeight) {
		this.ssseedWeight = ssseedWeight;
	}

	public ShiftStockSeed(InteriorUser interiorUserBySsseedAuditing,
			InteriorUser interiorUserBySsseedCollectMan, ShiftStock shiftStock,
			PaymentFashion paymentFashion, Goods goods,
			String ssseedAuditingTime, String ssseedAuditingTrue,
			Double ssseedShouldCost, Double ssseedRealityCost,
			String ssseedCollectTime, Double ssseedWeight, Double ssseedNumber,
			String ssseedClientAccounts, String ssseedOrderStatus,
			String ssseedDefinedOne, String ssseedDefinedTwo,
			String ssseedRemark) {
		this.interiorUserBySsseedAuditing = interiorUserBySsseedAuditing;
		this.interiorUserBySsseedCollectMan = interiorUserBySsseedCollectMan;
		this.shiftStock = shiftStock;
		this.paymentFashion = paymentFashion;
		this.goods = goods;
		this.ssseedAuditingTime = ssseedAuditingTime;
		this.ssseedAuditingTrue = ssseedAuditingTrue;
		this.ssseedShouldCost = ssseedShouldCost;
		this.ssseedRealityCost = ssseedRealityCost;
		this.ssseedCollectTime = ssseedCollectTime;
		this.ssseedWeight = ssseedWeight;
		this.ssseedNumber = ssseedNumber;
		this.ssseedClientAccounts = ssseedClientAccounts;
		this.ssseedOrderStatus = ssseedOrderStatus;
		this.ssseedDefinedOne = ssseedDefinedOne;
		this.ssseedDefinedTwo = ssseedDefinedTwo;
		this.ssseedRemark = ssseedRemark;
	}

	// 过户子订单编号，审核人员，收费人员，过户总订单编号,支付方式,货物,审核时间，审核是否通过，应收费用，实收费用，收费时间,重量，件数，结算方式，订单状态，预留字段一，预留字段二，备注
	public ShiftStockSeed(String ssseedId,
			InteriorUser interiorUserBySsseedAuditing,
			InteriorUser interiorUserBySsseedCollectMan, ShiftStock shiftStock,
			PaymentFashion paymentFashion, Goods goods,
			String ssseedAuditingTime, String ssseedAuditingTrue,
			Double ssseedShouldCost, Double ssseedRealityCost,
			String ssseedCollectTime, Double ssseedWeight, Double ssseedNumber,
			String ssseedClientAccounts, String ssseedOrderStatus,
			String ssseedDefinedOne, String ssseedDefinedTwo,
			String ssseedRemark) {
		this.ssseedId = ssseedId;
		this.interiorUserBySsseedAuditing = interiorUserBySsseedAuditing;
		this.interiorUserBySsseedCollectMan = interiorUserBySsseedCollectMan;
		this.shiftStock = shiftStock;
		this.paymentFashion = paymentFashion;
		this.goods = goods;
		this.ssseedAuditingTime = ssseedAuditingTime;
		this.ssseedAuditingTrue = ssseedAuditingTrue;
		this.ssseedShouldCost = ssseedShouldCost;
		this.ssseedRealityCost = ssseedRealityCost;
		this.ssseedCollectTime = ssseedCollectTime;
		this.ssseedWeight = ssseedWeight;
		this.ssseedNumber = ssseedNumber;
		this.ssseedClientAccounts = ssseedClientAccounts;
		this.ssseedOrderStatus = ssseedOrderStatus;
		this.ssseedDefinedOne = ssseedDefinedOne;
		this.ssseedDefinedTwo = ssseedDefinedTwo;
		this.ssseedRemark = ssseedRemark;
	}

	public String getSsseedId() {
		return this.ssseedId;
	}

	public void setSsseedId(String ssseedId) {
		this.ssseedId = ssseedId;
	}

	public InteriorUser getInteriorUserBySsseedAuditing() {
		return this.interiorUserBySsseedAuditing;
	}

	public void setInteriorUserBySsseedAuditing(
			InteriorUser interiorUserBySsseedAuditing) {
		this.interiorUserBySsseedAuditing = interiorUserBySsseedAuditing;
	}

	public InteriorUser getInteriorUserBySsseedCollectMan() {
		return this.interiorUserBySsseedCollectMan;
	}

	public void setInteriorUserBySsseedCollectMan(
			InteriorUser interiorUserBySsseedCollectMan) {
		this.interiorUserBySsseedCollectMan = interiorUserBySsseedCollectMan;
	}

	public ShiftStock getShiftStock() {
		return this.shiftStock;
	}

	public void setShiftStock(ShiftStock shiftStock) {
		this.shiftStock = shiftStock;
	}

	public PaymentFashion getPaymentFashion() {
		return this.paymentFashion;
	}

	public void setPaymentFashion(PaymentFashion paymentFashion) {
		this.paymentFashion = paymentFashion;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getSsseedAuditingTime() {
		return this.ssseedAuditingTime;
	}

	public void setSsseedAuditingTime(String ssseedAuditingTime) {
		this.ssseedAuditingTime = ssseedAuditingTime;
	}

	public String getSsseedAuditingTrue() {
		return this.ssseedAuditingTrue;
	}

	public void setSsseedAuditingTrue(String ssseedAuditingTrue) {
		this.ssseedAuditingTrue = ssseedAuditingTrue;
	}

	public Double getSsseedShouldCost() {
		return this.ssseedShouldCost;
	}

	public void setSsseedShouldCost(Double ssseedShouldCost) {
		this.ssseedShouldCost = ssseedShouldCost;
	}

	public Double getSsseedRealityCost() {
		return this.ssseedRealityCost;
	}

	public void setSsseedRealityCost(Double ssseedRealityCost) {
		this.ssseedRealityCost = ssseedRealityCost;
	}

	public String getSsseedCollectTime() {
		return this.ssseedCollectTime;
	}

	public void setSsseedCollectTime(String ssseedCollectTime) {
		this.ssseedCollectTime = ssseedCollectTime;
	}

	public Double getSsseedWeight() {
		return this.ssseedWeight;
	}

	public void setSsseedWeight(Double ssseedWeight) {
		this.ssseedWeight = ssseedWeight;
	}

	public Double getSsseedNumber() {
		return this.ssseedNumber;
	}

	public void setSsseedNumber(Double ssseedNumber) {
		this.ssseedNumber = ssseedNumber;
	}

	public String getSsseedClientAccounts() {
		return this.ssseedClientAccounts;
	}

	public void setSsseedClientAccounts(String ssseedClientAccounts) {
		this.ssseedClientAccounts = ssseedClientAccounts;
	}

	public String getSsseedOrderStatus() {
		return this.ssseedOrderStatus;
	}

	public void setSsseedOrderStatus(String ssseedOrderStatus) {
		this.ssseedOrderStatus = ssseedOrderStatus;
	}

	public String getSsseedDefinedOne() {
		return this.ssseedDefinedOne;
	}

	public void setSsseedDefinedOne(String ssseedDefinedOne) {
		this.ssseedDefinedOne = ssseedDefinedOne;
	}

	public String getSsseedDefinedTwo() {
		return this.ssseedDefinedTwo;
	}

	public void setSsseedDefinedTwo(String ssseedDefinedTwo) {
		this.ssseedDefinedTwo = ssseedDefinedTwo;
	}

	public String getSsseedRemark() {
		return this.ssseedRemark;
	}

	public void setSsseedRemark(String ssseedRemark) {
		this.ssseedRemark = ssseedRemark;
	}

}