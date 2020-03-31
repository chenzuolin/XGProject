package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * 客户表单
 * 
 * @author Administrator
 * 
 */
public class ClientForm extends ActionForm {

	private static final long serialVersionUID = -6210843962742232438L;
	private Integer clientId;// 客户编号
	private String clientLoginName;// 客户登录名，不可重复，并且和内部人员的登录名也不可重复
	private String clientPassword;// 登录密码
	private String clientCreateTime;// 注册时间
	private String clientTel;// 联系电话
	private String clientHuman;// 负责人
	private String clientEmail;// 负责人邮箱
	private FormFile clientStatusImage;// 身份证照片
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
	private FormFile clientDefinedOne;// 合同图片
	private String clientDefinedTwo;// 预留字段二
	private String clientRemark;// 备注
	private Double clientCredit;//对应客户的信用系数，用于估算货物的价值
	private String code;//验证码
	
	private String oldPwd;//旧密码
	private String okPwd;//确认密码
	

	public Double getClientCredit() {
		return clientCredit;
	}

	public void setClientCredit(Double clientCredit) {
		this.clientCredit = clientCredit;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getOkPwd() {
		return okPwd;
	}

	public void setOkPwd(String okPwd) {
		this.okPwd = okPwd;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getClientId() {
		return this.clientId;
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

	public FormFile getClientStatusImage() {
		return clientStatusImage;
	}

	public void setClientStatusImage(FormFile clientStatusImage) {
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


	public FormFile getClientDefinedOne() {
		return clientDefinedOne;
	}

	public void setClientDefinedOne(FormFile clientDefinedOne) {
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

}