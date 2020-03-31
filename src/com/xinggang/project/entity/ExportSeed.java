package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 出库子订单类
 * 
 * @author Administrator
 * 
 */
public class ExportSeed implements java.io.Serializable {

	private static final long serialVersionUID = -608563319160455036L;
	private String eseedId; // 出库子订单编号
	private PaymentFashion paymentFashion;// 支付方式，对应的支付方式类
	private Export export;// 出库总订单编号，对应的出库总订单类
	private Goods goods;// 出库的货物，对应的货物类
	private Double eseedShouldWeight;// 应出重量
	private Double eseedRealityWeight;// 实出重量
	private Double eseedShouldNumber;// 应出件数
	private Double eseedRealityNumber;// 实出件数
	private Double eseedShouldCost;// 应收费用
	private Double eseedRealityCost;// 实收费用
	private Double eseedSecondWork;// 二次作业重量
	private Double eseedSwshouldCost;// 二次作业重量应收费用
	private Double eseedSwrealityCost;// 二次作业重量实收费用
	private String eseedClientAccounts;// 结算方式(现/月)
	private String eseedOrderStatus;// 订单状态（计划出库，准备出库，出库完成，正在审核，审核通过，审核未通过，正在收费，收费完成）
	private String eseedDefinedOne;// 联系电话
	private String eseedDefinedTwo;// 预留字段二
	private String eseedRemark;// 备注
	private Set<Object> exportOperates = new HashSet<Object>(0); // 对应的子订单操作类
	private String shoufeiren;// 结算中的收费人

	private Integer goodsId;// 统计转发存时货物的id

	public ExportSeed() {
	}

	public ExportSeed(Goods goods, Double eseedRealityWeight) {
		this.goods = goods;
		this.eseedRealityWeight = eseedRealityWeight;
	}

	public ExportSeed(Double eseedRealityWeight) {
		this.eseedRealityWeight = eseedRealityWeight;
	}

	// 编号，支付方式，出库总订单，货物，应出重量，实出重量,应出件数，实出件数，应收费用，实收费用，二次作业重量，二次作业应收费用，二次作业实收费用，结算方式，订单状态，预留字段一，预留字段二，备注
	public ExportSeed(String eseedId, PaymentFashion paymentFashion,
			Export export, Goods goods, Double eseedShouldWeight,
			Double eseedRealityWeight, Double eseedShouldNumber,
			Double eseedRealityNumber, Double eseedShouldCost,
			Double eseedRealityCost, Double eseedSecondWork,
			Double eseedSwshouldCost, Double eseedSwrealityCost,
			String eseedClientAccounts, String eseedOrderStatus,
			String eseedDefinedOne, String eseedDefinedTwo, String eseedRemark) {
		this.eseedId = eseedId;
		this.paymentFashion = paymentFashion;
		this.export = export;
		this.goods = goods;
		this.eseedShouldWeight = eseedShouldWeight;
		this.eseedRealityWeight = eseedRealityWeight;
		this.eseedShouldNumber = eseedShouldNumber;
		this.eseedRealityNumber = eseedRealityNumber;
		this.eseedShouldCost = eseedShouldCost;
		this.eseedRealityCost = eseedRealityCost;
		this.eseedSecondWork = eseedSecondWork;
		this.eseedSwshouldCost = eseedSwshouldCost;
		this.eseedSwrealityCost = eseedSwrealityCost;
		this.eseedClientAccounts = eseedClientAccounts;
		this.eseedOrderStatus = eseedOrderStatus;
		this.eseedDefinedOne = eseedDefinedOne;
		this.eseedDefinedTwo = eseedDefinedTwo;
		this.eseedRemark = eseedRemark;
	}

	public ExportSeed(PaymentFashion paymentFashion, Export export,
			Goods goods, Double eseedShouldWeight, Double eseedRealityWeight,
			Double eseedShouldNumber, Double eseedRealityNumber,
			Double eseedShouldCost, Double eseedRealityCost,
			Double eseedSecondWork, Double eseedSwshouldCost,
			Double eseedSwrealityCost, String eseedClientAccounts,
			String eseedOrderStatus, String eseedDefinedOne,
			String eseedDefinedTwo, String eseedRemark,
			Set<Object> exportOperates) {
		this.paymentFashion = paymentFashion;
		this.export = export;
		this.goods = goods;
		this.eseedShouldWeight = eseedShouldWeight;
		this.eseedRealityWeight = eseedRealityWeight;
		this.eseedShouldNumber = eseedShouldNumber;
		this.eseedRealityNumber = eseedRealityNumber;
		this.eseedShouldCost = eseedShouldCost;
		this.eseedRealityCost = eseedRealityCost;
		this.eseedSecondWork = eseedSecondWork;
		this.eseedSwshouldCost = eseedSwshouldCost;
		this.eseedSwrealityCost = eseedSwrealityCost;
		this.eseedClientAccounts = eseedClientAccounts;
		this.eseedOrderStatus = eseedOrderStatus;
		this.eseedDefinedOne = eseedDefinedOne;
		this.eseedDefinedTwo = eseedDefinedTwo;
		this.eseedRemark = eseedRemark;
		this.exportOperates = exportOperates;
	}

	// 结算构造函数
	// 编号，支付方式，出库总订单，货物，应出重量，实出重量,应出件数，实出件数，应收费用，实收费用，二次作业重量，二次作业应收费用，二次作业实收费用，结算方式，订单状态，预留字段一，预留字段二，备注
	public ExportSeed(String eseedId, PaymentFashion paymentFashion,
			Export export, Goods goods, Double eseedShouldWeight,
			Double eseedRealityWeight, Double eseedShouldNumber,
			Double eseedRealityNumber, Double eseedShouldCost,
			Double eseedRealityCost, Double eseedSecondWork,
			Double eseedSwshouldCost, Double eseedSwrealityCost,
			String eseedClientAccounts, String eseedOrderStatus,
			String eseedDefinedOne, String eseedDefinedTwo, String eseedRemark,
			String shoufeiren) {
		this.shoufeiren = shoufeiren;
		this.eseedId = eseedId;
		this.paymentFashion = paymentFashion;
		this.export = export;
		this.goods = goods;
		this.eseedShouldWeight = eseedShouldWeight;
		this.eseedRealityWeight = eseedRealityWeight;
		this.eseedShouldNumber = eseedShouldNumber;
		this.eseedRealityNumber = eseedRealityNumber;
		this.eseedShouldCost = eseedShouldCost;
		this.eseedRealityCost = eseedRealityCost;
		this.eseedSecondWork = eseedSecondWork;
		this.eseedSwshouldCost = eseedSwshouldCost;
		this.eseedSwrealityCost = eseedSwrealityCost;
		this.eseedClientAccounts = eseedClientAccounts;
		this.eseedOrderStatus = eseedOrderStatus;
		this.eseedDefinedOne = eseedDefinedOne;
		this.eseedDefinedTwo = eseedDefinedTwo;
		this.eseedRemark = eseedRemark;
	}

	public String getShoufeiren() {
		return shoufeiren;
	}

	public void setShoufeiren(String shoufeiren) {
		this.shoufeiren = shoufeiren;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getEseedId() {
		return this.eseedId;
	}

	public void setEseedId(String eseedId) {
		this.eseedId = eseedId;
	}

	public PaymentFashion getPaymentFashion() {
		return this.paymentFashion;
	}

	public void setPaymentFashion(PaymentFashion paymentFashion) {
		this.paymentFashion = paymentFashion;
	}

	public Export getExport() {
		return this.export;
	}

	public void setExport(Export export) {
		this.export = export;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Double getEseedShouldWeight() {
		return this.eseedShouldWeight;
	}

	public void setEseedShouldWeight(Double eseedShouldWeight) {
		this.eseedShouldWeight = eseedShouldWeight;
	}

	public Double getEseedRealityWeight() {
		return this.eseedRealityWeight;
	}

	public void setEseedRealityWeight(Double eseedRealityWeight) {
		this.eseedRealityWeight = eseedRealityWeight;
	}

	public Double getEseedShouldNumber() {
		return this.eseedShouldNumber;
	}

	public void setEseedShouldNumber(Double eseedShouldNumber) {
		this.eseedShouldNumber = eseedShouldNumber;
	}

	public Double getEseedRealityNumber() {
		return this.eseedRealityNumber;
	}

	public void setEseedRealityNumber(Double eseedRealityNumber) {
		this.eseedRealityNumber = eseedRealityNumber;
	}

	public Double getEseedShouldCost() {
		return this.eseedShouldCost;
	}

	public void setEseedShouldCost(Double eseedShouldCost) {
		this.eseedShouldCost = eseedShouldCost;
	}

	public Double getEseedRealityCost() {
		return this.eseedRealityCost;
	}

	public void setEseedRealityCost(Double eseedRealityCost) {
		this.eseedRealityCost = eseedRealityCost;
	}

	public Double getEseedSecondWork() {
		return this.eseedSecondWork;
	}

	public void setEseedSecondWork(Double eseedSecondWork) {
		this.eseedSecondWork = eseedSecondWork;
	}

	public Double getEseedSwshouldCost() {
		return this.eseedSwshouldCost;
	}

	public void setEseedSwshouldCost(Double eseedSwshouldCost) {
		this.eseedSwshouldCost = eseedSwshouldCost;
	}

	public Double getEseedSwrealityCost() {
		return this.eseedSwrealityCost;
	}

	public void setEseedSwrealityCost(Double eseedSwrealityCost) {
		this.eseedSwrealityCost = eseedSwrealityCost;
	}

	public String getEseedClientAccounts() {
		return this.eseedClientAccounts;
	}

	public void setEseedClientAccounts(String eseedClientAccounts) {
		this.eseedClientAccounts = eseedClientAccounts;
	}

	public String getEseedOrderStatus() {
		return this.eseedOrderStatus;
	}

	public void setEseedOrderStatus(String eseedOrderStatus) {
		this.eseedOrderStatus = eseedOrderStatus;
	}

	public String getEseedDefinedOne() {
		return this.eseedDefinedOne;
	}

	public void setEseedDefinedOne(String eseedDefinedOne) {
		this.eseedDefinedOne = eseedDefinedOne;
	}

	public String getEseedDefinedTwo() {
		return this.eseedDefinedTwo;
	}

	public void setEseedDefinedTwo(String eseedDefinedTwo) {
		this.eseedDefinedTwo = eseedDefinedTwo;
	}

	public String getEseedRemark() {
		return this.eseedRemark;
	}

	public void setEseedRemark(String eseedRemark) {
		this.eseedRemark = eseedRemark;
	}

	public Set<Object> getExportOperates() {
		return this.exportOperates;
	}

	public void setExportOperates(Set<Object> exportOperates) {
		this.exportOperates = exportOperates;
	}

}