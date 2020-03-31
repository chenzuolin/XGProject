package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 入库总订单表单
 * 
 * @author Administrator
 * 
 */
public class InputForm extends ActionForm {

	private static final long serialVersionUID = 3020697332918716617L;
	private String inputId;// 入库总订单编号
	private Integer client;// 客户，对应的客户类
	private String inputClientNumber;// 客户订单号，由客户填写
	private String inputCreateTime;// 订单生成时间
	private String inputCarryType;// 运输方式（火车/汽车）
	private String inputPlateNumber;// 车号
	private String inputDriverName;// 司机姓名
	private String inputDriverTel;// 司机电话
	private String inputOrderStatus;// 订单状态
	private String inputCancel;// 订单是否作废
	private String inputDefinedOne;// 货物有效期
	private String inputDefinedTwo;// 预留字段二
	private String inputRemark;// 备注

	//入库子订单
	private String iseedId;// 入库子订单编号
	private String iseedInputId;//入库总订单编号	
	private Integer goods;// 对应的货物类
	private Integer paymentFashion;// 支付方式，对应的支付类
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
	
	//货物数组，用于保存多次入库的货物
	private Integer[] goodss;
	//货物重量数组，用于保存多次入库的货物重量
	private Double[] iseedShouldWeights;
	//货物备注数组，用于保存多次入库的货物备注
	private String[] iseedRemarks;
	//入库子订单数组
	private Integer[] inputSeeds;
	
	//app手机版所需字段
	private Integer goodsPropertyId;// 货物属性(调用属性ID)
	private Integer goodsCategoryId;// 货物品类(调用品类ID)
	private Integer goodsStandardId;//规格
	private Integer goodsQualityId;// 材质
	private Integer goodsYieldlyId;// 货物产地(调用产地ID)
	private String goodsName;// 货物名称
	
	
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getGoodsCategoryId() {
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(Integer goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	public Integer getGoodsPropertyId() {
		return goodsPropertyId;
	}

	public void setGoodsPropertyId(Integer goodsPropertyId) {
		this.goodsPropertyId = goodsPropertyId;
	}

	public Integer getGoodsStandardId() {
		return goodsStandardId;
	}

	public void setGoodsStandardId(Integer goodsStandardId) {
		this.goodsStandardId = goodsStandardId;
	}

	public Integer getGoodsQualityId() {
		return goodsQualityId;
	}

	public void setGoodsQualityId(Integer goodsQualityId) {
		this.goodsQualityId = goodsQualityId;
	}

	public Integer getGoodsYieldlyId() {
		return goodsYieldlyId;
	}

	public void setGoodsYieldlyId(Integer goodsYieldlyId) {
		this.goodsYieldlyId = goodsYieldlyId;
	}

	public Integer[] getInputSeeds() {
		return inputSeeds;
	}

	public void setInputSeeds(Integer[] inputSeeds) {
		this.inputSeeds = inputSeeds;
	}

	public Integer[] getGoodss() {
		return goodss;
	}

	public void setGoodss(Integer[] goodss) {
		this.goodss = goodss;
	}

	public Double[] getIseedShouldWeights() {
		return iseedShouldWeights;
	}

	public void setIseedShouldWeights(Double[] iseedShouldWeights) {
		this.iseedShouldWeights = iseedShouldWeights;
	}

	public String[] getIseedRemarks() {
		return iseedRemarks;
	}

	public void setIseedRemarks(String[] iseedRemarks) {
		this.iseedRemarks = iseedRemarks;
	}

	public String getIseedInputId() {
		return iseedInputId;
	}

	public void setIseedInputId(String iseedInputId) {
		this.iseedInputId = iseedInputId;
	}

	public String getInputId() {
		return this.inputId;
	}

	public void setInputId(String inputId) {
		this.inputId = inputId;
	}

	public Integer getClient() {
		return this.client;
	}

	public void setClient(Integer client) {
		this.client = client;
	}

	public String getInputClientNumber() {
		return this.inputClientNumber;
	}

	public void setInputClientNumber(String inputClientNumber) {
		this.inputClientNumber = inputClientNumber;
	}

	public String getInputCreateTime() {
		return this.inputCreateTime;
	}

	public void setInputCreateTime(String inputCreateTime) {
		this.inputCreateTime = inputCreateTime;
	}

	public String getInputCarryType() {
		return this.inputCarryType;
	}

	public void setInputCarryType(String inputCarryType) {
		this.inputCarryType = inputCarryType;
	}

	public String getInputPlateNumber() {
		return this.inputPlateNumber;
	}

	public void setInputPlateNumber(String inputPlateNumber) {
		this.inputPlateNumber = inputPlateNumber;
	}

	public String getInputDriverName() {
		return this.inputDriverName;
	}

	public void setInputDriverName(String inputDriverName) {
		this.inputDriverName = inputDriverName;
	}

	public String getInputDriverTel() {
		return this.inputDriverTel;
	}

	public void setInputDriverTel(String inputDriverTel) {
		this.inputDriverTel = inputDriverTel;
	}

	public String getInputOrderStatus() {
		return this.inputOrderStatus;
	}

	public void setInputOrderStatus(String inputOrderStatus) {
		this.inputOrderStatus = inputOrderStatus;
	}

	public String getInputCancel() {
		return this.inputCancel;
	}

	public void setInputCancel(String inputCancel) {
		this.inputCancel = inputCancel;
	}

	public String getInputDefinedOne() {
		return this.inputDefinedOne;
	}

	public void setInputDefinedOne(String inputDefinedOne) {
		this.inputDefinedOne = inputDefinedOne;
	}

	public String getInputDefinedTwo() {
		return this.inputDefinedTwo;
	}

	public void setInputDefinedTwo(String inputDefinedTwo) {
		this.inputDefinedTwo = inputDefinedTwo;
	}

	public String getInputRemark() {
		return this.inputRemark;
	}

	public void setInputRemark(String inputRemark) {
		this.inputRemark = inputRemark;
	}

	// 入库子订单
	public String getIseedId() {
		return iseedId;
	}

	public void setIseedId(String iseedId) {
		this.iseedId = iseedId;
	}

	public Integer getGoods() {
		return goods;
	}

	public void setGoods(Integer goods) {
		this.goods = goods;
	}	

	public Integer getPaymentFashion() {
		return paymentFashion;
	}

	public void setPaymentFashion(Integer paymentFashion) {
		this.paymentFashion = paymentFashion;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public Double getIseedShouldWeight() {
		return iseedShouldWeight;
	}

	public void setIseedShouldWeight(Double iseedShouldWeight) {
		this.iseedShouldWeight = iseedShouldWeight;
	}

	public Double getIseedRealityWeight() {
		return iseedRealityWeight;
	}

	public void setIseedRealityWeight(Double iseedRealityWeight) {
		this.iseedRealityWeight = iseedRealityWeight;
	}

	public Double getIseedShouldNumber() {
		return iseedShouldNumber;
	}

	public void setIseedShouldNumber(Double iseedShouldNumber) {
		this.iseedShouldNumber = iseedShouldNumber;
	}

	public Double getIseedRealityNumber() {
		return iseedRealityNumber;
	}

	public void setIseedRealityNumber(Double iseedRealityNumber) {
		this.iseedRealityNumber = iseedRealityNumber;
	}

	public Double getIseedShouldCost() {
		return iseedShouldCost;
	}

	public void setIseedShouldCost(Double iseedShouldCost) {
		this.iseedShouldCost = iseedShouldCost;
	}

	public Double getIseedRealityCost() {
		return iseedRealityCost;
	}

	public void setIseedRealityCost(Double iseedRealityCost) {
		this.iseedRealityCost = iseedRealityCost;
	}

	public String getIseedClientAccounts() {
		return iseedClientAccounts;
	}

	public void setIseedClientAccounts(String iseedClientAccounts) {
		this.iseedClientAccounts = iseedClientAccounts;
	}

	public String getIseedOrderStatus() {
		return iseedOrderStatus;
	}

	public void setIseedOrderStatus(String iseedOrderStatus) {
		this.iseedOrderStatus = iseedOrderStatus;
	}

	public String getIseedDefinedOne() {
		return iseedDefinedOne;
	}

	public void setIseedDefinedOne(String iseedDefinedOne) {
		this.iseedDefinedOne = iseedDefinedOne;
	}

	public String getIseedDefinedTwo() {
		return iseedDefinedTwo;
	}

	public void setIseedDefinedTwo(String iseedDefinedTwo) {
		this.iseedDefinedTwo = iseedDefinedTwo;
	}

	public String getIseedRemark() {
		return iseedRemark;
	}

	public void setIseedRemark(String iseedRemark) {
		this.iseedRemark = iseedRemark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}