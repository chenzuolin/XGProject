package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 货物库存类
 * 
 * @author Administrator
 * 
 */
public class TarehouseGoods implements java.io.Serializable {

	private static final long serialVersionUID = 6380880589930972308L;
	private Integer tgoodsId;// 货物库存编号
	private Goods goods;// 对应的货物类
	private Tarehouse tarehouse;// 对应的库位类
	private Double tgoodsWeight;// 库存重量
	private Double tgoodsNumber;// 库存件数
	private String tgoodsDefinedTwo;// 预留字段一
	private String tgoodsDefinedOne;// 预留字段二
	private String tgoodsRemark;// 备注
	private Set<Object> checkses = new HashSet<Object>(0);// 对应的盘点类

	public TarehouseGoods() {
	}

	// 编号，货物，库位，重量，件数，预留字段一，预留字段二，备注
	public TarehouseGoods(Integer tgoodsId, Goods goods, Tarehouse tarehouse,
			Double tgoodsWeight, Double tgoodsNumber, String tgoodsDefinedTwo,
			String tgoodsDefinedOne, String tgoodsRemark) {
		this.tgoodsId = tgoodsId;
		this.goods = goods;
		this.tarehouse = tarehouse;
		this.tgoodsWeight = tgoodsWeight;
		this.tgoodsNumber = tgoodsNumber;
		this.tgoodsDefinedTwo = tgoodsDefinedTwo;
		this.tgoodsDefinedOne = tgoodsDefinedOne;
		this.tgoodsRemark = tgoodsRemark;
	}

	public TarehouseGoods(Goods goods, Tarehouse tarehouse,
			Double tgoodsWeight, Double tgoodsNumber, String tgoodsDefinedTwo,
			String tgoodsDefinedOne, String tgoodsRemark, Set<Object> checkses) {
		this.goods = goods;
		this.tarehouse = tarehouse;
		this.tgoodsWeight = tgoodsWeight;
		this.tgoodsNumber = tgoodsNumber;
		this.tgoodsDefinedTwo = tgoodsDefinedTwo;
		this.tgoodsDefinedOne = tgoodsDefinedOne;
		this.tgoodsRemark = tgoodsRemark;
		this.checkses = checkses;
	}

	public Integer getTgoodsId() {
		return this.tgoodsId;
	}

	public void setTgoodsId(Integer tgoodsId) {
		this.tgoodsId = tgoodsId;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Tarehouse getTarehouse() {
		return this.tarehouse;
	}

	public void setTarehouse(Tarehouse tarehouse) {
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

	public Set<Object> getCheckses() {
		return this.checkses;
	}

	public void setCheckses(Set<Object> checkses) {
		this.checkses = checkses;
	}

}