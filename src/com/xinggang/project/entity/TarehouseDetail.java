package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 货物批次类
 * 
 * @author Administrator
 * 
 */
public class TarehouseDetail implements java.io.Serializable {

	private static final long serialVersionUID = 5564119531067481846L;
	private String tdetailId;// 货物批次编号
	private Goods goods;// 对应的货物类
	private String tdetailIputTime;// 入库日期
	private Double tdetailWeight;// 入库重量
	private Double tdetailEweight;// 已出重量
	private Double tdetailNumber;// 入库件数
	private Double tdetailEnumber;// 已出件数
	private Integer tdetailRemind;// 是否提醒，提醒5次，每次加一，直到5时不再提醒
	private String tdetailDefinedOne;// 预留字段一
	private String tdetailDefinedTwo;// 预留字段二
	private String tdetailRemark;// 备注
	private Tarehouse tdetailTarehouse;// 库位，关联到库位类

	private Set<Object> tarehouseDetailOperates = new HashSet<Object>(0);// 对应批次操作类

	public TarehouseDetail() {
	}

	public TarehouseDetail(Goods goods, String tdetailIputTime,
			Double tdetailWeight, Double tdetailEweight, Double tdetailNumber,
			Double tdetailEnumber, Integer tdetailRemind,
			String tdetailDefinedOne, String tdetailDefinedTwo,
			String tdetailRemark, Set<Object> tarehouseDetailOperates) {
		this.goods = goods;
		this.tdetailIputTime = tdetailIputTime;
		this.tdetailWeight = tdetailWeight;
		this.tdetailEweight = tdetailEweight;
		this.tdetailNumber = tdetailNumber;
		this.tdetailEnumber = tdetailEnumber;
		this.tdetailRemind = tdetailRemind;
		this.tdetailDefinedOne = tdetailDefinedOne;
		this.tdetailDefinedTwo = tdetailDefinedTwo;
		this.tdetailRemark = tdetailRemark;
		this.tarehouseDetailOperates = tarehouseDetailOperates;
	}

	public Tarehouse getTdetailTarehouse() {
		return tdetailTarehouse;
	}

	public void setTdetailTarehouse(Tarehouse tdetailTarehouse) {
		this.tdetailTarehouse = tdetailTarehouse;
	}

	public String getTdetailId() {
		return this.tdetailId;
	}

	public void setTdetailId(String tdetailId) {
		this.tdetailId = tdetailId;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getTdetailIputTime() {
		return this.tdetailIputTime;
	}

	public void setTdetailIputTime(String tdetailIputTime) {
		this.tdetailIputTime = tdetailIputTime;
	}

	public Double getTdetailWeight() {
		return this.tdetailWeight;
	}

	public void setTdetailWeight(Double tdetailWeight) {
		this.tdetailWeight = tdetailWeight;
	}

	public Double getTdetailEweight() {
		return this.tdetailEweight;
	}

	public void setTdetailEweight(Double tdetailEweight) {
		this.tdetailEweight = tdetailEweight;
	}

	public Double getTdetailNumber() {
		return this.tdetailNumber;
	}

	public void setTdetailNumber(Double tdetailNumber) {
		this.tdetailNumber = tdetailNumber;
	}

	public Double getTdetailEnumber() {
		return this.tdetailEnumber;
	}

	public void setTdetailEnumber(Double tdetailEnumber) {
		this.tdetailEnumber = tdetailEnumber;
	}

	public Integer getTdetailRemind() {
		return this.tdetailRemind;
	}

	public void setTdetailRemind(Integer tdetailRemind) {
		this.tdetailRemind = tdetailRemind;
	}

	public String getTdetailDefinedOne() {
		return this.tdetailDefinedOne;
	}

	public void setTdetailDefinedOne(String tdetailDefinedOne) {
		this.tdetailDefinedOne = tdetailDefinedOne;
	}

	public String getTdetailDefinedTwo() {
		return this.tdetailDefinedTwo;
	}

	public void setTdetailDefinedTwo(String tdetailDefinedTwo) {
		this.tdetailDefinedTwo = tdetailDefinedTwo;
	}

	public String getTdetailRemark() {
		return this.tdetailRemark;
	}

	public void setTdetailRemark(String tdetailRemark) {
		this.tdetailRemark = tdetailRemark;
	}

	public Set<Object> getTarehouseDetailOperates() {
		return this.tarehouseDetailOperates;
	}

	public void setTarehouseDetailOperates(Set<Object> tarehouseDetailOperates) {
		this.tarehouseDetailOperates = tarehouseDetailOperates;
	}

}