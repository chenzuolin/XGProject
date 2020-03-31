package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 
 * 使用系统公司内部人员表单
 */

public class InteriorUserForm extends ActionForm {

	private static final long serialVersionUID = -4477304512297105927L;
	private Integer iuserId;// 内部人员编号
	private Integer section;// 对应类部门类
	private Integer interiorUserDuty;// 对应类使用系统的公司内部人员职责
	private Integer classT;// 对应类班组类
	private String iuserLoginName;// 用户登录名
	private String iuserPassword;// 用户登录名密码
	private String iuserCreateTime;// 注册时间
	private String iuserTel;// 联系电话
	private String iuserName;// 用户姓名
	private String iuserSex;// 用户性别
	private Integer iuserCease;// 用户是否停用(1否0是)
	private Integer iuserOnline;// 是否在线(1否0是)
	private Integer iuserWork;// 是否作业(1否0是)
	private String iuserDefinedOne;// 自定义字段1
	private String iuserDefinedTwo;// 自定义字段2
	private String iuserRemark;// 备注

	private String bumen;// 部门
	private String banzu;// 班组

	public String getBumen() {
		return bumen;
	}

	public void setBumen(String bumen) {
		this.bumen = bumen;
	}

	public String getBanzu() {
		return banzu;
	}

	public void setBanzu(String banzu) {
		this.banzu = banzu;
	}

	public Integer getIuserId() {
		return this.iuserId;
	}

	public void setIuserId(Integer iuserId) {
		this.iuserId = iuserId;
	}

	public Integer getSection() {
		return this.section;
	}

	public void setSection(Integer section) {
		this.section = section;
	}

	public Integer getInteriorUserDuty() {
		return this.interiorUserDuty;
	}

	public void setInteriorUserDuty(Integer interiorUserDuty) {
		this.interiorUserDuty = interiorUserDuty;
	}

	public Integer getClassT() {
		return this.classT;
	}

	public void setClassT(Integer classT) {
		this.classT = classT;
	}

	public String getIuserLoginName() {
		return this.iuserLoginName;
	}

	public void setIuserLoginName(String iuserLoginName) {
		this.iuserLoginName = iuserLoginName;
	}

	public String getIuserPassword() {
		return this.iuserPassword;
	}

	public void setIuserPassword(String iuserPassword) {
		this.iuserPassword = iuserPassword;
	}

	public String getIuserCreateTime() {
		return this.iuserCreateTime;
	}

	public void setIuserCreateTime(String iuserCreateTime) {
		this.iuserCreateTime = iuserCreateTime;
	}

	public String getIuserTel() {
		return this.iuserTel;
	}

	public void setIuserTel(String iuserTel) {
		this.iuserTel = iuserTel;
	}

	public String getIuserName() {
		return this.iuserName;
	}

	public void setIuserName(String iuserName) {
		this.iuserName = iuserName;
	}

	public String getIuserSex() {
		return this.iuserSex;
	}

	public void setIuserSex(String iuserSex) {
		this.iuserSex = iuserSex;
	}

	public Integer getIuserCease() {
		return this.iuserCease;
	}

	public void setIuserCease(Integer iuserCease) {
		this.iuserCease = iuserCease;
	}

	public Integer getIuserOnline() {
		return this.iuserOnline;
	}

	public void setIuserOnline(Integer iuserOnline) {
		this.iuserOnline = iuserOnline;
	}

	public Integer getIuserWork() {
		return this.iuserWork;
	}

	public void setIuserWork(Integer iuserWork) {
		this.iuserWork = iuserWork;
	}

	public String getIuserDefinedOne() {
		return this.iuserDefinedOne;
	}

	public void setIuserDefinedOne(String iuserDefinedOne) {
		this.iuserDefinedOne = iuserDefinedOne;
	}

	public String getIuserDefinedTwo() {
		return this.iuserDefinedTwo;
	}

	public void setIuserDefinedTwo(String iuserDefinedTwo) {
		this.iuserDefinedTwo = iuserDefinedTwo;
	}

	public String getIuserRemark() {
		return this.iuserRemark;
	}

	public void setIuserRemark(String iuserRemark) {
		this.iuserRemark = iuserRemark;
	}

}