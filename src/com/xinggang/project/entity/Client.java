package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 客户类
 * 
 * @author Administrator
 * 
 */
public class Client implements java.io.Serializable {

	private static final long serialVersionUID = -6210843962742232438L;
	private Integer clientId;// 客户编号
	private String clientLoginName;// 客户登录名，不可重复，并且和内部人员的登录名也不可重复
	private String clientPassword;// 登录密码
	private String clientCreateTime;// 注册时间
	private String clientTel;// 联系电话
	private String clientHuman;// 负责人
	private String clientEmail;// 负责人邮箱
	private String clientStatusImage;// 身份证照片
	private String clientStatusNumber;// 身份证号码
	private String clientFirmName;// 单位名称（全屏）
	private String clientAbbreviation;// 简称
	private String clientSign;// 名称助记符
	private String clientAddress;// 单位地址
	private String clientContract;// 合同号
	private String clientStartTime;// 合同起始日期
	private String clientFinishTime;// 合同结束日期
	private Integer clientCease;// 是否停用（1否，0是）
	private String clientAccounts;// 结算方式（现/月）
	private Double clientAgio;// 折扣
	private String clientDefinedOne;// 合同图片
	private String clientDefinedTwo;// 是否在线
	private String clientRemark;// 备注
	private Double clientCredit;//客户信用系数，用于估算货物的价值
	private Set<Object> shiftStocksForSstockClientId = new HashSet<Object>(0);// 货主，对应的过户总订单类
	private Set<Object> tarehouseDetailOperatesForTdoperateShiftToFirm = new HashSet<Object>(0);// 对应的货物批次操作类
	private Set<Object> clientGoodses = new HashSet<Object>(0);// 对应的客户货物库存类
	private Set<Object> shiftStocksForSstockShiftToFirm = new HashSet<Object>(0);// 转入单位，对应的过户总订单类
	private Set<Object> exports = new HashSet<Object>(0);// 对应的出库总订单类
	private Set<Object> accountses = new HashSet<Object>(0);// 对应的滞纳金类
	private Set<Object> tarehouseDetailOperatesForTdoperateClientId = new HashSet<Object>(0);// 货主,对应的货物批次操作类
	private Set<Object> inputs = new HashSet<Object>(0);// 对应的出库类
	private Set<Object> client = new HashSet<Object>(0);// 对应的盘点客户货物库存类

	public Client() {
	}

	public Client(String clientLoginName, String clientPassword,
			String clientCreateTime, String clientTel, String clientHuman,
			String clientEmail, String clientStatusImage,
			String clientStatusNumber, String clientFirmName,
			String clientAbbreviation, String clientSign, String clientAddress,
			String clientContract, String clientStartTime,
			String clientFinishTime, Integer clientCease,
			String clientAccounts, Double clientAgio, String clientDefinedOne,
			String clientDefinedTwo, String clientRemark,
			Set<Object> shiftStocksForSstockClientId,
			Set<Object> tarehouseDetailOperatesForTdoperateShiftToFirm,
			Set<Object> clientGoodses,
			Set<Object> shiftStocksForSstockShiftToFirm, Set<Object> exports,
			Set<Object> accountses,
			Set<Object> tarehouseDetailOperatesForTdoperateClientId,
			Set<Object> inputs, Set<Object> client) {
		this.client = client;
		this.clientLoginName = clientLoginName;
		this.clientPassword = clientPassword;
		this.clientCreateTime = clientCreateTime;
		this.clientTel = clientTel;
		this.clientHuman = clientHuman;
		this.clientEmail = clientEmail;
		this.clientStatusImage = clientStatusImage;
		this.clientStatusNumber = clientStatusNumber;
		this.clientFirmName = clientFirmName;
		this.clientAbbreviation = clientAbbreviation;
		this.clientSign = clientSign;
		this.clientAddress = clientAddress;
		this.clientContract = clientContract;
		this.clientStartTime = clientStartTime;
		this.clientFinishTime = clientFinishTime;
		this.clientCease = clientCease;
		this.clientAccounts = clientAccounts;
		this.clientAgio = clientAgio;
		this.clientDefinedOne = clientDefinedOne;
		this.clientDefinedTwo = clientDefinedTwo;
		this.clientRemark = clientRemark;
		this.shiftStocksForSstockClientId = shiftStocksForSstockClientId;
		this.tarehouseDetailOperatesForTdoperateShiftToFirm = tarehouseDetailOperatesForTdoperateShiftToFirm;
		this.clientGoodses = clientGoodses;
		this.shiftStocksForSstockShiftToFirm = shiftStocksForSstockShiftToFirm;
		this.exports = exports;
		this.accountses = accountses;
		this.tarehouseDetailOperatesForTdoperateClientId = tarehouseDetailOperatesForTdoperateClientId;
		this.inputs = inputs;
	}

	public Double getClientCredit() {
		return clientCredit;
	}

	public void setClientCredit(Double clientCredit) {
		this.clientCredit = clientCredit;
	}

	public Integer getClientId() {
		return this.clientId;
	}

	public Set<Object> getClient() {
		return client;
	}

	public void setClient(Set<Object> client) {
		this.client = client;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getClientLoginName() {
		return this.clientLoginName;
	}

	public void setClientLoginName(String clientLoginName) {
		this.clientLoginName = clientLoginName;
	}

	public String getClientPassword() {
		return this.clientPassword;
	}

	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public String getClientCreateTime() {
		return this.clientCreateTime;
	}

	public void setClientCreateTime(String clientCreateTime) {
		this.clientCreateTime = clientCreateTime;
	}

	public String getClientTel() {
		return this.clientTel;
	}

	public void setClientTel(String clientTel) {
		this.clientTel = clientTel;
	}

	public String getClientHuman() {
		return this.clientHuman;
	}

	public void setClientHuman(String clientHuman) {
		this.clientHuman = clientHuman;
	}

	public String getClientEmail() {
		return this.clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getClientStatusImage() {
		return this.clientStatusImage;
	}

	public void setClientStatusImage(String clientStatusImage) {
		this.clientStatusImage = clientStatusImage;
	}

	public String getClientStatusNumber() {
		return this.clientStatusNumber;
	}

	public void setClientStatusNumber(String clientStatusNumber) {
		this.clientStatusNumber = clientStatusNumber;
	}

	public String getClientFirmName() {
		return this.clientFirmName;
	}

	public void setClientFirmName(String clientFirmName) {
		this.clientFirmName = clientFirmName;
	}

	public String getClientAbbreviation() {
		return this.clientAbbreviation;
	}

	public void setClientAbbreviation(String clientAbbreviation) {
		this.clientAbbreviation = clientAbbreviation;
	}

	public String getClientSign() {
		return this.clientSign;
	}

	public void setClientSign(String clientSign) {
		this.clientSign = clientSign;
	}

	public String getClientAddress() {
		return this.clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getClientContract() {
		return this.clientContract;
	}

	public void setClientContract(String clientContract) {
		this.clientContract = clientContract;
	}

	public String getClientStartTime() {
		return this.clientStartTime;
	}

	public void setClientStartTime(String clientStartTime) {
		this.clientStartTime = clientStartTime;
	}

	public String getClientFinishTime() {
		return this.clientFinishTime;
	}

	public void setClientFinishTime(String clientFinishTime) {
		this.clientFinishTime = clientFinishTime;
	}

	public Integer getClientCease() {
		return this.clientCease;
	}

	public void setClientCease(Integer clientCease) {
		this.clientCease = clientCease;
	}

	public String getClientAccounts() {
		return this.clientAccounts;
	}

	public void setClientAccounts(String clientAccounts) {
		this.clientAccounts = clientAccounts;
	}

	public Double getClientAgio() {
		return this.clientAgio;
	}

	public void setClientAgio(Double clientAgio) {
		this.clientAgio = clientAgio;
	}

	public String getClientDefinedOne() {
		return this.clientDefinedOne;
	}

	public void setClientDefinedOne(String clientDefinedOne) {
		this.clientDefinedOne = clientDefinedOne;
	}

	public String getClientDefinedTwo() {
		return this.clientDefinedTwo;
	}

	public void setClientDefinedTwo(String clientDefinedTwo) {
		this.clientDefinedTwo = clientDefinedTwo;
	}

	public String getClientRemark() {
		return this.clientRemark;
	}

	public void setClientRemark(String clientRemark) {
		this.clientRemark = clientRemark;
	}

	public Set<Object> getShiftStocksForSstockClientId() {
		return this.shiftStocksForSstockClientId;
	}

	public void setShiftStocksForSstockClientId(
			Set<Object> shiftStocksForSstockClientId) {
		this.shiftStocksForSstockClientId = shiftStocksForSstockClientId;
	}

	public Set<Object> getTarehouseDetailOperatesForTdoperateShiftToFirm() {
		return this.tarehouseDetailOperatesForTdoperateShiftToFirm;
	}

	public void setTarehouseDetailOperatesForTdoperateShiftToFirm(
			Set<Object> tarehouseDetailOperatesForTdoperateShiftToFirm) {
		this.tarehouseDetailOperatesForTdoperateShiftToFirm = tarehouseDetailOperatesForTdoperateShiftToFirm;
	}

	public Set<Object> getClientGoodses() {
		return this.clientGoodses;
	}

	public void setClientGoodses(Set<Object> clientGoodses) {
		this.clientGoodses = clientGoodses;
	}

	public Set<Object> getShiftStocksForSstockShiftToFirm() {
		return this.shiftStocksForSstockShiftToFirm;
	}

	public void setShiftStocksForSstockShiftToFirm(
			Set<Object> shiftStocksForSstockShiftToFirm) {
		this.shiftStocksForSstockShiftToFirm = shiftStocksForSstockShiftToFirm;
	}

	public Set<Object> getExports() {
		return this.exports;
	}

	public void setExports(Set<Object> exports) {
		this.exports = exports;
	}

	public Set<Object> getAccountses() {
		return this.accountses;
	}

	public void setAccountses(Set<Object> accountses) {
		this.accountses = accountses;
	}

	public Set<Object> getTarehouseDetailOperatesForTdoperateClientId() {
		return this.tarehouseDetailOperatesForTdoperateClientId;
	}

	public void setTarehouseDetailOperatesForTdoperateClientId(
			Set<Object> tarehouseDetailOperatesForTdoperateClientId) {
		this.tarehouseDetailOperatesForTdoperateClientId = tarehouseDetailOperatesForTdoperateClientId;
	}

	public Set<Object> getInputs() {
		return this.inputs;
	}

	public void setInputs(Set<Object> inputs) {
		this.inputs = inputs;
	}

}