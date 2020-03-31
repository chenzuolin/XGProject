package com.xinggang.project.entity;

import java.text.DecimalFormat;

/**
 * InitClientGoods entity. @author MyEclipse Persistence Tools+ 初始化客户库存统计
 */

public class InitClientGoods implements java.io.Serializable {

	private static final long serialVersionUID = 6941636480731058787L;
	private Integer icgId;// 编号 /
	private String icgClient;// 客户
	private String icgName;// 货物名称
	private String icgGuige;// 规格
	private String icgCaizhi;// 材质
	private String icgShuxing;// 属性
	private String icgChandi;// 产地
	private Double icgWeight;// 重量
	private String icgDefinedone;// 匀溜一
	private String icgDefinedTwo;// 预留二
	private String icgDefinedThree;// 预留三

	private Integer clientId;// 对应客户的id
	private Integer goodsId;// 对应货物的id

	public InitClientGoods() {
	}

	public InitClientGoods(String icgClient, String icgName, String icgGuige,
			String icgCaizhi, String icgShuxing, String icgChandi,
			Double icgWeight, String icgDefinedone, String icgDefinedTwo,
			String icgDefinedThree) {
		this.icgClient = icgClient;
		this.icgName = icgName;
		this.icgGuige = icgGuige;
		this.icgCaizhi = icgCaizhi;
		this.icgShuxing = icgShuxing;
		this.icgChandi = icgChandi;
		this.icgWeight = icgWeight;
		this.icgDefinedone = icgDefinedone;
		this.icgDefinedTwo = icgDefinedTwo;
		this.icgDefinedThree = icgDefinedThree;
	}

	DecimalFormat df = new DecimalFormat("###############0.000");

	public InitClientGoods(String icgClient, String icgName, String icgGuige,
			String icgCaizhi, String icgShuxing, String icgChandi,
			Double icgWeight) {
		this.icgClient = icgClient;
		this.icgName = icgName;
		this.icgGuige = icgGuige;
		this.icgCaizhi = icgCaizhi;
		this.icgShuxing = icgShuxing;
		this.icgChandi = icgChandi;
		this.icgWeight = icgWeight;
	}

	public InitClientGoods(String icgName, String icgGuige, String icgCaizhi,
			String icgShuxing, String icgChandi, Double icgWeight) {
		this.icgName = icgName;
		this.icgGuige = icgGuige;
		this.icgCaizhi = icgCaizhi;
		this.icgShuxing = icgShuxing;
		this.icgChandi = icgChandi;
		this.icgWeight = icgWeight;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getIcgId() {
		return this.icgId;
	}

	public void setIcgId(Integer icgId) {
		this.icgId = icgId;
	}

	public String getIcgClient() {
		return this.icgClient;
	}

	public void setIcgClient(String icgClient) {
		this.icgClient = icgClient;
	}

	public String getIcgName() {
		return this.icgName;
	}

	public void setIcgName(String icgName) {
		this.icgName = icgName;
	}

	public String getIcgGuige() {
		return this.icgGuige;
	}

	public void setIcgGuige(String icgGuige) {
		this.icgGuige = icgGuige;
	}

	public String getIcgCaizhi() {
		return this.icgCaizhi;
	}

	public void setIcgCaizhi(String icgCaizhi) {
		this.icgCaizhi = icgCaizhi;
	}

	public String getIcgShuxing() {
		return this.icgShuxing;
	}

	public void setIcgShuxing(String icgShuxing) {
		this.icgShuxing = icgShuxing;
	}

	public String getIcgChandi() {
		return this.icgChandi;
	}

	public void setIcgChandi(String icgChandi) {
		this.icgChandi = icgChandi;
	}

	public Double getIcgWeight() {
		return this.icgWeight;
	}

	public void setIcgWeight(Double icgWeight) {
		this.icgWeight = icgWeight;
	}

	public String getIcgDefinedone() {
		return this.icgDefinedone;
	}

	public void setIcgDefinedone(String icgDefinedone) {
		this.icgDefinedone = icgDefinedone;
	}

	public String getIcgDefinedTwo() {
		return this.icgDefinedTwo;
	}

	public void setIcgDefinedTwo(String icgDefinedTwo) {
		this.icgDefinedTwo = icgDefinedTwo;
	}

	public String getIcgDefinedThree() {
		return this.icgDefinedThree;
	}

	public void setIcgDefinedThree(String icgDefinedThree) {
		this.icgDefinedThree = icgDefinedThree;
	}

}