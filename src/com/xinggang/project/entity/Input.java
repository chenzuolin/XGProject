package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 入库总订单类
 * 
 * @author Administrator
 * 
 */
public class Input implements java.io.Serializable {

	private static final long serialVersionUID = 3020697332918716617L;
	private String inputId;// 入库总订单编号
	private Client client;// 客户，对应的客户类
	private String inputClientNumber;// 客户订单号，由客户填写
	private String inputCreateTime;// 订单生成时间
	private String inputCarryType;// 运输方式（火车/汽车）
	private String inputPlateNumber;// 车号
	private String inputDriverName;// 司机姓名
	private String inputDriverTel;// 司机电话
	private String inputOrderStatus;// 订单状态
	private String inputCancel;// 订单是否作废//---------------------不用
	private String inputDefinedOne;// 有效期
	private String inputDefinedTwo;// 预留字段二
	private String inputRemark;// 备注
	private String inputFaQiRen;//入库发起人
	private Set<Object> inputSeeds = new HashSet<Object>(0);// 对应的入库子订单类

	// Constructors

	/** default constructor */
	public Input() {
	}

	/** full constructor */
	public Input(Client client, String inputClientNumber,
			String inputCreateTime, String inputCarryType,
			String inputPlateNumber, String inputDriverName,
			String inputDriverTel, String inputOrderStatus, String inputCancel,
			String inputDefinedOne, String inputDefinedTwo, String inputRemark,
			Set<Object> inputSeeds) {
		this.client = client;
		this.inputClientNumber = inputClientNumber;
		this.inputCreateTime = inputCreateTime;
		this.inputCarryType = inputCarryType;
		this.inputPlateNumber = inputPlateNumber;
		this.inputDriverName = inputDriverName;
		this.inputDriverTel = inputDriverTel;
		this.inputOrderStatus = inputOrderStatus;
		this.inputCancel = inputCancel;
		this.inputDefinedOne = inputDefinedOne;
		this.inputDefinedTwo = inputDefinedTwo;
		this.inputRemark = inputRemark;
		this.inputSeeds = inputSeeds;
	}

	// Property accessors

	public String getInputId() {
		return this.inputId;
	}


	public String getInputFaQiRen() {
		return inputFaQiRen;
	}

	public void setInputFaQiRen(String inputFaQiRen) {
		this.inputFaQiRen = inputFaQiRen;
	}

	public void setInputId(String inputId) {
		this.inputId = inputId;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
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

	public Set<Object> getInputSeeds() {
		return this.inputSeeds;
	}

	public void setInputSeeds(Set<Object> inputSeeds) {
		this.inputSeeds = inputSeeds;
	}

}