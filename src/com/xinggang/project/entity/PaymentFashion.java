package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 支付方式类
 * 
 * @author Administrator
 * 
 */
public class PaymentFashion implements java.io.Serializable {

	private static final long serialVersionUID = -3895232041272341467L;

	private Integer pfashionId;// 支付方式编号
	private String pfashionName;// 支付方式名称，转账/支票/现金
	private String pfashionReceipt;// 发票类型，发票/收据
	private String pfashionDefinedOne;// 是否停用，1否、0是
	private String pfashionDefinedTwo;// 预留字段二
	private String pfashionRemark;// 备注
	private Set<Object> inputSeeds = new HashSet<Object>(0);// 对应的入库子订单类
	private Set<Object> exportSeeds = new HashSet<Object>(0);// 对应的出库子订单类
	private Set<Object> shiftStockSeeds = new HashSet<Object>(0);// 对应的过户子订单类
	private Set<Object> accountsDefinedTwo = new HashSet<Object>(0);// 对应的过户子订单类
	private Set<Object> eoperatePaymentFashion = new HashSet<Object>(0);// 出库操作订单类

	private Set<Object> ioperatePaymentFashion = new HashSet<Object>(0);// 入库订单操作类

	public Set<Object> getAccountsDefinedTwo() {
		return accountsDefinedTwo;
	}

	public void setAccountsDefinedTwo(Set<Object> accountsDefinedTwo) {
		this.accountsDefinedTwo = accountsDefinedTwo;
	}

	public PaymentFashion() {
	}

	public PaymentFashion(String pfashionName, String pfashionReceipt,
			String pfashionDefinedOne, String pfashionDefinedTwo,
			String pfashionRemark, Set<Object> inputSeeds,
			Set<Object> exportSeeds, Set<Object> shiftStockSeeds,
			Set<Object> eoperatePaymentFashion,
			Set<Object> ioperatePaymentFashion) {
		this.ioperatePaymentFashion = ioperatePaymentFashion;
		this.pfashionName = pfashionName;
		this.pfashionReceipt = pfashionReceipt;
		this.pfashionDefinedOne = pfashionDefinedOne;
		this.pfashionDefinedTwo = pfashionDefinedTwo;
		this.pfashionRemark = pfashionRemark;
		this.inputSeeds = inputSeeds;
		this.exportSeeds = exportSeeds;
		this.shiftStockSeeds = shiftStockSeeds;
		this.eoperatePaymentFashion = eoperatePaymentFashion;
	}

	public Set<Object> getIoperatePaymentFashion() {
		return ioperatePaymentFashion;
	}

	public void setIoperatePaymentFashion(Set<Object> ioperatePaymentFashion) {
		this.ioperatePaymentFashion = ioperatePaymentFashion;
	}

	public Set<Object> getEoperatePaymentFashion() {
		return eoperatePaymentFashion;
	}

	public void setEoperatePaymentFashion(Set<Object> eoperatePaymentFashion) {
		this.eoperatePaymentFashion = eoperatePaymentFashion;
	}

	public Integer getPfashionId() {
		return this.pfashionId;
	}

	public void setPfashionId(Integer pfashionId) {
		this.pfashionId = pfashionId;
	}

	public String getPfashionName() {
		return this.pfashionName;
	}

	public void setPfashionName(String pfashionName) {
		this.pfashionName = pfashionName;
	}

	public String getPfashionReceipt() {
		return this.pfashionReceipt;
	}

	public void setPfashionReceipt(String pfashionReceipt) {
		this.pfashionReceipt = pfashionReceipt;
	}

	public String getPfashionDefinedOne() {
		return this.pfashionDefinedOne;
	}

	public void setPfashionDefinedOne(String pfashionDefinedOne) {
		this.pfashionDefinedOne = pfashionDefinedOne;
	}

	public String getPfashionDefinedTwo() {
		return this.pfashionDefinedTwo;
	}

	public void setPfashionDefinedTwo(String pfashionDefinedTwo) {
		this.pfashionDefinedTwo = pfashionDefinedTwo;
	}

	public String getPfashionRemark() {
		return this.pfashionRemark;
	}

	public void setPfashionRemark(String pfashionRemark) {
		this.pfashionRemark = pfashionRemark;
	}

	public Set<Object> getInputSeeds() {
		return this.inputSeeds;
	}

	public void setInputSeeds(Set<Object> inputSeeds) {
		this.inputSeeds = inputSeeds;
	}

	public Set<Object> getExportSeeds() {
		return this.exportSeeds;
	}

	public void setExportSeeds(Set<Object> exportSeeds) {
		this.exportSeeds = exportSeeds;
	}

	public Set<Object> getShiftStockSeeds() {
		return this.shiftStockSeeds;
	}

	public void setShiftStockSeeds(Set<Object> shiftStockSeeds) {
		this.shiftStockSeeds = shiftStockSeeds;
	}

}