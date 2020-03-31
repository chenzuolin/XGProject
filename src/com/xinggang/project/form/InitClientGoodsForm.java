package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/*
 * 客户库存初始化form
 */
public class InitClientGoodsForm extends ActionForm {

	private static final long serialVersionUID = -7180586283144638207L;
	private Integer client;// 客户，对应的客户类
	// 货物数组，用于保存多次入库的货物
	private Integer[] goodss;
	// 货物重量数组，用于保存多次入库的货物重量
	private Double[] iseedShouldWeights;
	private Integer goods;// 对应的货物类
	private Double iseedShouldWeight;// 应入重量
	private Double iseedRealityWeight;// 实入重量
	private Double iseedShouldNumber;// 应入件数
	private Double iseedRealityNumber;// 实入件数

	// 对应的初始化库位库存时的件数
	private Double[] number;// 件数
	// 对应的库位
	private Integer Tarehouse;// 库位

	public Integer getTarehouse() {
		return Tarehouse;
	}

	public void setTarehouse(Integer tarehouse) {
		Tarehouse = tarehouse;
	}

	public Double[] getNumber() {
		return number;
	}

	public void setNumber(Double[] number) {
		this.number = number;
	}

	public Integer getClient() {
		return client;
	}

	public void setClient(Integer client) {
		this.client = client;
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

	public Integer getGoods() {
		return goods;
	}

	public void setGoods(Integer goods) {
		this.goods = goods;
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

}
