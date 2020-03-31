package com.xinggang.project.entity;

/**
 * 盘点客户货物库存类
 * 
 * @author Administrator
 * 
 */
public class ChecksClientGoods implements java.io.Serializable {

	private static final long serialVersionUID = 6190776574223405422L;
	private String ccgoodsId; // 编号
	private Client client; // 对应的客户类
	private Bursary bursary; // 对应库房类
	private InteriorUser interiorUser; // 对应的内部人员类
	private Goods goods; // 对应的货物类
	private Double ccgoodsBeforeWeight; // 原有重量
	private Double ccgoodsSurplus; // 盈库
	private Double ccgoodsLoss; // 亏库
	private String ccgoodsChecksTime; // 盘点日期
	private String ccgoodsDefinedOne; // 预留字段一
	private String ccgoodsDefinedTwo; // 预留字段二
	private String ccgoodsRemark; // 备注

	public ChecksClientGoods() {
	}

	public ChecksClientGoods(Client client, Bursary bursary,
			InteriorUser interiorUser, Goods goods, Double ccgoodsBeforeWeight,
			Double ccgoodsSurplus, Double ccgoodsLoss,
			String ccgoodsChecksTime, String ccgoodsDefinedOne,
			String ccgoodsDefinedTwo, String ccgoodsRemark) {
		this.client = client;
		this.bursary = bursary;
		this.interiorUser = interiorUser;
		this.goods = goods;
		this.ccgoodsBeforeWeight = ccgoodsBeforeWeight;
		this.ccgoodsSurplus = ccgoodsSurplus;
		this.ccgoodsLoss = ccgoodsLoss;
		this.ccgoodsChecksTime = ccgoodsChecksTime;
		this.ccgoodsDefinedOne = ccgoodsDefinedOne;
		this.ccgoodsDefinedTwo = ccgoodsDefinedTwo;
		this.ccgoodsRemark = ccgoodsRemark;
	}

	// 编号，客户，库房，内部人员，货物，原有重量，盈库重量，亏库重量，盘点日期，预留字段一，预留字段二，备注
	public ChecksClientGoods(String ccgoodsId, Client client, Bursary bursary,
			InteriorUser interiorUser, Goods goods, Double ccgoodsBeforeWeight,
			Double ccgoodsSurplus, Double ccgoodsLoss,
			String ccgoodsChecksTime, String ccgoodsDefinedOne,
			String ccgoodsDefinedTwo, String ccgoodsRemark) {
		this.ccgoodsId = ccgoodsId;
		this.client = client;
		this.bursary = bursary;
		this.interiorUser = interiorUser;
		this.goods = goods;
		this.ccgoodsBeforeWeight = ccgoodsBeforeWeight;
		this.ccgoodsSurplus = ccgoodsSurplus;
		this.ccgoodsLoss = ccgoodsLoss;
		this.ccgoodsChecksTime = ccgoodsChecksTime;
		this.ccgoodsDefinedOne = ccgoodsDefinedOne;
		this.ccgoodsDefinedTwo = ccgoodsDefinedTwo;
		this.ccgoodsRemark = ccgoodsRemark;
	}

	public String getCcgoodsId() {
		return this.ccgoodsId;
	}

	public void setCcgoodsId(String ccgoodsId) {
		this.ccgoodsId = ccgoodsId;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Bursary getBursary() {
		return this.bursary;
	}

	public void setBursary(Bursary bursary) {
		this.bursary = bursary;
	}

	public InteriorUser getInteriorUser() {
		return this.interiorUser;
	}

	public void setInteriorUser(InteriorUser interiorUser) {
		this.interiorUser = interiorUser;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
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