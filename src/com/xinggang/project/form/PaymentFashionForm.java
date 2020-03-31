package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 支付方式表单
 * 
 * @author Administrator
 * 
 */
public class PaymentFashionForm extends ActionForm {

	private static final long serialVersionUID = -3895232041272341467L;

	private Integer pfashionId;// 支付方式编号
	private String pfashionName;// 支付方式名称，转账/支票/现金
	private String pfashionReceipt;// 发票类型，发票/收据
	private String pfashionDefinedOne;// 预留字段一
	private String pfashionDefinedTwo;// 预留字段二
	private String pfashionRemark;// 备注

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

}